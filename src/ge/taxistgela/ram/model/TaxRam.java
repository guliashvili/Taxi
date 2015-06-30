package ge.taxistgela.ram.model;

import com.google.gson.Gson;
import ge.taxistgela.bean.Location;
import ge.taxistgela.bean.Order;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.dao.OrderDao;
import ge.taxistgela.dao.UserDao;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.helper.GoogleMapUtils;
import ge.taxistgela.model.SessionManager;
import ge.taxistgela.ram.bean.*;
import ge.taxistgela.ram.dao.DriverInfoDao;
import ge.taxistgela.ram.dao.UserInfoDao;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by GIO on 6/26/2015.
 */

public class TaxRam implements TaxRamAPI {

    SessionManager sessionManager;
    private OrderDao orderDao;
    private UserDao userDao;
    private DriverDao driverDao;
    private DriverInfoDao driverInfoDao;
    private UserInfoDao userInfoDao;
    private ConcurrentHashMap<Integer, DriverInfo> drivers = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, UserInfo> users = new ConcurrentHashMap<>();

    public TaxRam(OrderDao orderDao, UserDao userDao, DriverDao driverDao, SessionManager sessionManager) {
        this.driverDao = driverDao;
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.driverInfoDao = new DriverInfoDao(driverDao, drivers, users);
        this.userInfoDao = new UserInfoDao(userDao, drivers, users);
        this.sessionManager = sessionManager;
    }

    @Override
    public Location getDriverLocation(int driverID) {
        if (!drivers.containsKey(driverID)) {
            ExternalAlgorithms.debugPrint("wrong get location " + driverID);
            return null;
        } else {
            ExternalAlgorithms.debugPrint("get location " + driverID);
            return drivers.get(driverID).getLocation();

        }

    }

    @Override
    public void updateDriverLocation(int driverID, Location location) {
        ExternalAlgorithms.debugPrint("update location");
        if (!drivers.containsKey(driverID)) {
            DriverInfo driverInfo = driverInfoDao.getDriverInfoByID(driverID);
            if (driverInfo != null)
                drivers.putIfAbsent(driverID, driverInfo);
        }
        if (drivers.containsKey(driverID))
            drivers.get(driverID).setLocation(location);

    }


    @Override
    public synchronized double getPrice(DriverInfo driverInfo, UserInfo userInfo, OrderInfo orderInfo) {
        return  (GoogleMapUtils.getRoad(driverInfo.getLocation(), orderInfo.getStart()).distance.inMeters +
                GoogleMapUtils.getRoad(orderInfo.getStart(),orderInfo.getEnd()).distance.inMeters)/1000.0 *
                driverInfo.getPreferences().getCoefficientPer();//TODO
    }

    @Override
    public synchronized void addOrder(Order order) {
        ExternalAlgorithms.debugPrint("Order added " + order.getUserID() + " \n");


        if (!users.containsKey(order.getUserID()))
            users.putIfAbsent(order.getUserID(), userInfoDao.getUserInfoByID(order.getUserID()));
        UserInfo userInfo = users.get(order.getUserID());

        if(userInfo == null) return;

        Long curMinute = TimeUnit.MILLISECONDS.toMinutes(new Date().getTime());


        OrderInfo traki = new OrderInfo(curMinute, order.getEndLocation(),
                order.getNumPassengers(), -1, order.getStartLocation(),
                curMinute,
                -1, userInfo, null, null, order.getOrderID(), null);


        List<DriverInfo> queue = driverInfoDao.getDriversByUserPreference(userInfo, traki);

        BOOLEAN dealisDone = new BOOLEAN(false);
        for(DriverInfo driverInfo : queue) {
            OrderInfo orderInfo = new OrderInfo(curMinute, order.getEndLocation(),
                    order.getNumPassengers(), -1, order.getStartLocation(),
                    curMinute,
                    -1,
                    userInfo,
                    driverInfo, dealisDone, order.getOrderID(), driverInfo.getLocation());

            orderInfo.setDistance((GoogleMapUtils.getRoad(driverInfo.getLocation(), orderInfo.getStart()).distance.inMeters +
                    GoogleMapUtils.getRoad(orderInfo.getStart(), orderInfo.getEnd()).distance.inMeters) / 1000.0);

            orderInfo.setMaxPrice(orderInfo.getDistance() * orderInfo.getDriver().getPreferences().getCoefficientPer());


            driverInfo.waitingList.add(orderInfo);

            driverInfo.removeBadOrders();

            getRouteUser(userInfo.getUserID());
            getWaitingUsers(driverInfo.getDriverID());

            getWaitingDrivers(userInfo.getUserID());
            getRouteDriver(driverInfo.getDriverID());
        }

    }

    @Override
    public synchronized List<OrderInfo> getWaitingUsers(int driverID) {
        DriverInfo driverInfo = drivers.get(driverID);
        if (driverInfo == null) return null;
        driverInfo.removeBadOrders();

        driverInfo.waitingList.sort((o1, o2) -> {
            if (o1.getCreateTime() < o2.getCreateTime()) return 1;
            else if (o1.getCreateTime() == o2.getCreateTime()) return 0;
            else return -1;
        });

        ExternalAlgorithms.debugPrint("getWaitingUsers sending users to " + driverID + " size of " + driverInfo.waitingList.size());

        String ret = new Gson().toJson(driverInfo.waitingList);

        sessionManager.sendMessage(SessionManager.DRIVER_SESSION, driverID, ret);

        return driverInfo.waitingList;//DON'T CHANGE daginebulia
    }

    @Override
    public synchronized List<OrderInfo> getWaitingDrivers(int userID) {
        UserInfo userInfo = users.get(userID);
        if (userInfo == null) return null;
        userInfo.removeBadOrders();

        userInfo.waitingList.sort((o1, o2) -> {
            if (o1.getCreateTime() < o2.getCreateTime()) return 1;
            else if (o1.getCreateTime() == o2.getCreateTime()) return 0;
            else return -1;
        });

        ExternalAlgorithms.debugPrint("getWaitingDrivers sending drivers to " + userID + " size of " + userInfo.waitingList.size());

        String ret = new Gson().toJson(userInfo.waitingList);
        sessionManager.sendMessage(SessionManager.USER_SESSION, userID, ret);
        return userInfo.waitingList;
    }

    @Override
    public synchronized boolean driverChoice(int driverID, int userID, int orderID, boolean accept) {
        DriverInfo driverInfo = drivers.get(driverID);
        UserInfo userInfo = users.get(userID);
        if (userInfo == null || driverInfo == null) return true;
        boolean ret = false;

        userInfo.removeBadOrders();
        driverInfo.removeBadOrders();

        if (!accept) {
            ret |= !driverInfo.waitingList.removeIf(orderInfo1 ->
                    orderInfo1.getOrderID() == orderID);

        } else {

            OrderInfo orderInfo = null;
            for (OrderInfo elem : driverInfo.waitingList)
                if (elem.getOrderID() == orderID) {
                    orderInfo = elem;
                    break;
                }



            if (orderInfo != null) {

                ret |= !(driverInfo.waitingList.removeIf(orderInfo1 -> orderInfo1.getOrderID() == orderID));

                if (!ret) {
                    userInfo.waitingList.add(orderInfo);
                }
            } else
                ret = true;
        }

        getWaitingUsers(driverID);
        getRouteDriver(driverID);

        getWaitingDrivers(userID);
        getRouteUser(userID);

        return ret;
    }

    @Override
    public synchronized boolean userChoice(int driverID, int userID, int orderID, boolean accept) {
        DriverInfo driverInfo = drivers.get(driverID);
        UserInfo userInfo = users.get(userID);
        if (userInfo == null || driverInfo == null) return true;

        boolean ret = false;

        userInfo.removeBadOrders();
        driverInfo.removeBadOrders();



        if (!accept) {
            ret |= !(userInfo.waitingList.removeIf(orderInfo1 ->
                    orderInfo1.getDriver().getDriverID() == driverID &&
                            orderInfo1.getOrderID() == orderID));
        } else {
            OrderInfo orderInfo = null;
            for (OrderInfo elem : userInfo.waitingList) {
                if (elem.getOrderID() == orderID && elem.getDriver().getDriverID() == driverID) {
                    orderInfo = elem;
                    break;
                }

            }

            if (orderInfo != null && !orderInfo.getSetTrueDealIsDone()) {
                userInfo.setOrderInfo(orderInfo);
                userInfo.setDriverInfo(driverInfo);
                ret |= !(userInfo.waitingList.removeIf(orderInfo1 ->
                        orderInfo1.getDriver().getDriverID() == driverID &&
                                orderInfo1.getOrderID() == orderID));

                if (!ret) {
                    userInfo.waitingList.removeIf(orderInfo1 -> orderInfo1.getOrderID() == orderID);
                    driverInfo.route.addOrder(orderInfo);
                }
            } else
                ret = true;
        }
        getWaitingUsers(driverID);
        getWaitingDrivers(userID);

        getRouteDriver(driverID);
        getRouteUser(userID);

        return ret;

    }

    public synchronized Route getRouteDriver(int driverID) {
        DriverInfo driverInfo = drivers.get(driverID);
        if (driverInfo == null) return null;
        Route route = driverInfo.route;
        if (route == null) return null;
        String message = ExternalAlgorithms.parseToJson(route);

        sessionManager.sendMessage(SessionManager.DRIVER_SESSION, driverID, message);

        return route;
    }

    public synchronized OrderInfo getRouteUser(int userID) {
        UserInfo userInfo = users.get(userID);
        if (userInfo == null) return null;
        DriverInfo driverInfo = userInfo.getDriverInfo();
        if (driverInfo == null) return null;

        Route route = driverInfo.route;
        if (route == null) return null;

        OrderInfo orderInfo = null;
        synchronized (driverInfo.route.inCar) {
            for (OrderInfo elem : driverInfo.route.inCar) {
                if (elem.getOrderID() == userID) {
                    orderInfo = elem;
                }
            }
            synchronized (route) {
                for (RouteElement elem : driverInfo.route.route) {
                    if (elem.getOrderInfo().getOrderID() == userID) {
                        orderInfo = elem.getOrderInfo();
                    }
                }
            }
        }

        String message = new Gson().toJson(orderInfo);

        sessionManager.sendMessage(SessionManager.USER_SESSION, userID, message);

        return orderInfo;
    }


    public synchronized boolean pickUser(int driverID, int orderID, int userID) {
        UserInfo userInfo = users.get(userID);
        DriverInfo driverInfo = drivers.get(driverID);
        if (userInfo == null || driverInfo == null) return true;
        boolean ret = false;

        driverInfo.route.pickUser(userInfo, orderID);

        getRouteUser(userID);
        getRouteDriver(driverID);

        getWaitingDrivers(userID);
        getWaitingUsers(driverID);

        return ret;
    }

    public synchronized double leaveUser(int driverID, int orderID, int userID) {
        double ret = 1;// revokeOrderUser(userID);
//TODO
        return ret;
    }

    public synchronized boolean revokeOrderUser(int userID) {
        UserInfo userInfo = users.get(userID);
        if (userInfo == null) return true;

        OrderInfo orderInfo = userInfo.getOrderInfo();
        DriverInfo driverInfo = userInfo.getDriverInfo();
        if (orderInfo == null || driverInfo == null)
            return true;

        orderInfo.setCreateTime(0);
        userInfo.setOrderInfo(null);
        userInfo.setOrderInfo(null);
        userInfo.waitingList.clear();
        driverInfo.route.leaveUser(userInfo, orderInfo.getOrderID());
        driverInfo.waitingList.removeIf(orderInfo1 -> orderInfo1.getOrderID() == orderInfo.getOrderID() && orderInfo1.getUser().getUserID() == userID);


        getRouteUser(userID);
        getRouteDriver(driverInfo.getDriverID());

        getWaitingDrivers(userID);
        getWaitingUsers(driverInfo.getDriverID());

        return false;
    }

    public synchronized boolean revokeOrderDriver(int userID) {
        return revokeOrderUser(userID);
    }


}
