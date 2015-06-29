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
import ge.taxistgela.ram.bean.DriverInfo;
import ge.taxistgela.ram.bean.OrderInfo;
import ge.taxistgela.ram.bean.UserInfo;
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
        DriverInfo driverInfo = driverInfoDao.getDriverInfoByID(driverID);
        if (driverInfo == null) {
            ExternalAlgorithms.debugPrint("wrong update location");
        } else {
            ExternalAlgorithms.debugPrint("update location");

            drivers.putIfAbsent(driverID, driverInfo);
            drivers.get(driverID).setLocation(location);
        }
    }

    @Override
    public double getPrice(DriverInfo driverInfo,UserInfo userInfo,OrderInfo orderInfo){
        return  (GoogleMapUtils.getRoad(driverInfo.getLocation(), orderInfo.getStart()).distance.inMeters +
                GoogleMapUtils.getRoad(orderInfo.getStart(),orderInfo.getEnd()).distance.inMeters)/1000.0 *
                driverInfo.getPreferences().getCoefficientPer();//TODO
    }

    @Override
    public void addOrder(Order order){
        ExternalAlgorithms.debugPrint("Order added " + order.getUserID() + " \n");

        UserInfo userInfo = userInfoDao.getUserInfoByID(order.getUserID());
        if(userInfo == null) return;
        Long curMinute = TimeUnit.MILLISECONDS.toMinutes(new Date().getTime());


        OrderInfo traki = new OrderInfo(curMinute, order.getEndLocation(),
                order.getNumPassengers(), -1, order.getStartLocation(),
                curMinute,
                -1, userInfo, null);

        List<DriverInfo> queue = driverInfoDao.getDriversByUserPreference(userInfo, traki);


        for(DriverInfo driverInfo : queue) {
            OrderInfo orderInfo = new OrderInfo(curMinute, order.getEndLocation(),
                    order.getNumPassengers(), -1, order.getStartLocation(),
                    curMinute,
                    -1,
                    userInfo,
                    driverInfo);

            orderInfo.setDistance((GoogleMapUtils.getRoad(driverInfo.getLocation(), orderInfo.getStart()).distance.inMeters +
                    GoogleMapUtils.getRoad(orderInfo.getStart(), orderInfo.getEnd()).distance.inMeters) / 1000.0);

            orderInfo.setMaxPrice(orderInfo.getDistance() * orderInfo.getDriver().getPreferences().getCoefficientPer());


            driverInfo.waitingList.add(orderInfo);

            driverInfo.removeOldOrders();

            getWaitingUsers(driverInfo.getDriverID());
        }

    }

    @Override
    public List<OrderInfo> getWaitingUsers(int driverID) {
        DriverInfo driverInfo = drivers.get(driverID);
        if (driverInfo == null) return null;
        driverInfo.removeOldOrders();
        ExternalAlgorithms.debugPrint("getWaitingUsers sending users to " + driverID + " size of " + driverInfo.waitingList.size());

        String ret = new Gson().toJson(driverInfo.waitingList);

        sessionManager.sendMessage(SessionManager.DRIVER_SESSION, driverID, ret);

        return driverInfo.waitingList;//DON'T CHANGE daginebulia
    }

    @Override
    public List<OrderInfo> getWaitingDrivers(int userID) {
        UserInfo userInfo = users.get(userID);
        if (userInfo == null) return null;
        userInfo.removeOldOrders();
        ExternalAlgorithms.debugPrint("getWaitingDrivers sending drivers to " + userID + " size of " + userInfo.waitingList.size());

        String ret = new Gson().toJson(userInfo.waitingList);
        sessionManager.sendMessage(SessionManager.USER_SESSION, userID, ret);
        return userInfo.waitingList;
    }

    @Override
    public boolean driverChoice(int driverID, int userID, boolean accept) {
        DriverInfo driverInfo = drivers.get(driverID);
        UserInfo userInfo = users.get(userID);
        if (userInfo == null || driverInfo == null) return true;
        boolean ret = false;

        if (!accept) {
            ret |= !driverInfo.waitingList.removeIf(orderInfo1 -> orderInfo1.getUser().getUserID() == userID);

        } else {

            OrderInfo orderInfo = null;
            for (OrderInfo elem : driverInfo.waitingList)
                if (elem.getUser().getUserID() == userID)
                    orderInfo = elem;


            if (orderInfo != null) {
                ret |= !(driverInfo.waitingList.removeIf(orderInfo1 -> orderInfo1.getUser().getUserID() == userID));

                if (!ret) {
                    userInfo.waitingList.add(orderInfo);
                }
            } else
                ret = true;
        }

        getWaitingUsers(driverID);
        getWaitingDrivers(userID);

        return ret;
    }

    @Override
    public boolean userChoice(int driverID, int userID, boolean accept) {
        DriverInfo driverInfo = drivers.get(driverID);
        UserInfo userInfo = users.get(userID);
        if (userInfo == null || driverInfo == null) return true;

        boolean ret = false;
        if (!accept) {
            ret |= !(userInfo.waitingList.removeIf(orderInfo1 -> orderInfo1.getDriver().getDriverID() == driverID));
        } else {
            OrderInfo orderInfo = null;
            for (OrderInfo elem : driverInfo.waitingList) {
                if (elem.getUser().getUserID() == userID)
                    orderInfo = elem;

            }
            if (orderInfo != null) {
                ret |= !(userInfo.waitingList.removeIf(orderInfo1 -> orderInfo1.getDriver().getDriverID() == driverID));

                if (!ret) {
                    userInfo.waitingList.forEach(orderInfo1 -> {
                        DriverInfo tmp = drivers.get(orderInfo1.getDriver().getDriverID());
                        if (tmp != null)
                            tmp.waitingList.removeIf(orderInfo2 -> orderInfo2.getUser().getUserID() == userID);
                    });
                    userInfo.waitingList.clear();
                    driverInfo.timeTable.add(orderInfo);

                }
            } else
                ret = true;
        }
        getWaitingUsers(driverID);
        getWaitingDrivers(userID);
        return ret;

    }
}
