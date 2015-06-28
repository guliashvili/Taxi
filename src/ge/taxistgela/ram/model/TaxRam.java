package ge.taxistgela.ram.model;

import ge.taxistgela.bean.Location;
import ge.taxistgela.bean.Order;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.dao.OrderDao;
import ge.taxistgela.dao.UserDao;
import ge.taxistgela.helper.GoogleMapUtils;
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

    private OrderDao orderDao;
    private UserDao userDao;
    private DriverDao driverDao;
    private DriverInfoDao driverInfoDao;
    private UserInfoDao userInfoDao;

    private ConcurrentHashMap<Integer, DriverInfo> drivers = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, UserInfo> users = new ConcurrentHashMap<>();

    public TaxRam(OrderDao orderDao, UserDao userDao, DriverDao driverDao) {
        this.driverDao = driverDao;
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.driverInfoDao = new DriverInfoDao(driverDao, drivers, users);
        this.userInfoDao = new UserInfoDao(userDao, drivers, users);
    }

    @Override
    public Location getDriverLocation(int driverID) {
        if(!drivers.containsKey(driverID)) return null;
        return  drivers.get(driverID).getLocation();
    }

    @Override
    public void updateDriverLocation(int driverID, Location location) {
        DriverInfo driverInfo = driverInfoDao.getDriverInfoByID(driverID);
        if(driverInfo == null) return;

        drivers.putIfAbsent(driverID,driverInfo);
        drivers.get(driverID).setLocation(location);

    }
    public double getPrice(DriverInfo driverInfo,UserInfo userInfo,OrderInfo orderInfo){
        return  (GoogleMapUtils.getRoad(driverInfo.getLocation(), orderInfo.getStart()).distance.inMeters +
                GoogleMapUtils.getRoad(orderInfo.getStart(),orderInfo.getEnd()).distance.inMeters)/1000.0 *
                driverInfo.getPreferences().getCoefficientPer();//TODO
    }


    public void addOrder(Order order){
        UserInfo userInfo = userInfoDao.getUserInfoByID(order.getUserID());
        if(userInfo == null) return;
        Long curMinute = TimeUnit.MILLISECONDS.toMinutes(new Date().getTime());

        OrderInfo orderInfo = new OrderInfo(curMinute,-1,order.getEndLocation(),
                order.getNumPassengers(),-1,order.getStartLocation(),order.getUserID(),
                TimeUnit.MILLISECONDS.toMinutes(order.getStartTime().getTime()),
                userInfo.getPreference().getTimeLimit(), -1, -1);


        List<DriverInfo> queue = driverInfoDao.getDriversByUserPreference(userInfo,orderInfo);


        for(DriverInfo driverInfo : queue) {
            orderInfo.setDistance((GoogleMapUtils.getRoad(driverInfo.getLocation(), orderInfo.getStart()).distance.inMeters +
                    GoogleMapUtils.getRoad(orderInfo.getStart(), orderInfo.getEnd()).distance.inMeters) / 1000.0);
            orderInfo.setPricePer(driverInfo.getPreferences().getCoefficientPer());
            orderInfo.setMaxPrice(orderInfo.getDistance() * orderInfo.getPricePer());
            orderInfo.setDriverID(driverInfo.getDriverID());

            driverInfo.waitingList.add(orderInfo);

            driverInfo.removeOldOrders();

            //TODO
        }









    }


}
