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
    OrderDao orderDao;
    UserDao userDao;
    DriverDao driverDao;
    DriverInfoDao driverInfoDao;
    UserInfoDao userInfoDao;

    private ConcurrentHashMap<Integer, DriverInfo> drivers = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, UserInfo> users = new ConcurrentHashMap<>();


    public TaxRam(OrderDao orderDao, UserDao userDao, DriverDao driverDao) {
        this.driverDao = driverDao;
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.driverInfoDao = new DriverInfoDao(driverDao, userDao, drivers, users);
        this.userInfoDao = new UserInfoDao(driverDao, userDao, drivers, users);
    }

    public void updateDriverLocation(int driverID, Location location) {
        DriverInfo driver = driverInfoDao.getDriverInfo(driverID);
        if (driver == null) {
            ExternalAlgorithms.debugPrint("updateDriverLocation wrong driverID");
        } else {
            drivers.putIfAbsent(driverID, driver);

            driver.setLocation(location);

        }
    }

    public Location getDriverLocation(int driverID) {
        if (!drivers.containsKey(driverID)) {
            ExternalAlgorithms.debugPrint("we have no information about the driver");
            return null;
        } else {
            return drivers.get(driverID).getLocation();
        }

    }

    public void driverPickedUser(int driverID, int userID) {
        if (!drivers.containsKey(driverID)) {
            ExternalAlgorithms.debugPrint("driverPickedUser wrong driverID");
        } else if (!users.containsKey(userID)) {
            ExternalAlgorithms.debugPrint("driverPickedUser wrong userID");
        } else {
//TODO


        }

    }

    public void driverLeftUser(int driverID, int userID) {
        if (!driverDao.checkDriverID(driverID)) {
            System.err.println("driverPickedUser wrong driverID");
        } else if (!userDao.checkUserID(userID)) {
            System.err.println("driverPickedUser wrong userID");
        } else {
            if (drivers.containsKey(driverID)) {
                drivers.get(driverID).timetable.remove(new Integer(userID));
                drivers.get(driverID).timetable.remove(new Integer(-userID));
                drivers.get(driverID).inTheCar.remove(new Integer(userID));
            } else {
                System.err.println("Driver does not exists but he left someone? Oo");
            }
        }

    }

    public List<Object> getUserChoices() {
        return null;
    }

    public void orderSetup(int userID) {
        UserInfo user = userInfoDao.getUserInfo(userID);
        if (user == null) {
            ExternalAlgorithms.debugPrint("orderSetup wrong userID");
        } else {
            users.putIfAbsent(userID, user);
            user.update(userInfoDao.getUserInfo(userID));


            user.firstRoundDrivers.clear();
            user.secondRoundDrivers.clear();

            user.firstRoundDrivers.addAll(driverInfoDao.getUserPreferences(userID));


        }

    }

}
