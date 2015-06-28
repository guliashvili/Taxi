package ge.taxistgela.ram.model;

import ge.taxistgela.bean.Location;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.dao.OrderDao;
import ge.taxistgela.dao.UserDao;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.ram.bean.DriverInfo;
import ge.taxistgela.ram.bean.UserInfo;
import ge.taxistgela.ram.dao.DriverInfoDao;
import ge.taxistgela.ram.dao.UserInfoDao;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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

}
