package ge.taxistgela.ram.dao;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.User;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.dao.UserDao;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.ram.bean.DriverInfo;
import ge.taxistgela.ram.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by GIO on 6/27/2015.
 */
public class DriverInfoDao {
    ConcurrentHashMap<Integer, DriverInfo> drivers;
    ConcurrentHashMap<Integer, UserInfo> users;
    private DriverDao driverDao;
    private UserDao userDao;

    public DriverInfoDao(DriverDao driverDao, UserDao userDao, ConcurrentHashMap<Integer, DriverInfo> drivers, ConcurrentHashMap<Integer, UserInfo> users) {
        this.driverDao = driverDao;
        this.userDao = userDao;
        this.drivers = drivers;
        this.users = users;
    }


    public DriverInfo getDriverInfo(int driverID) {
        Driver driver = driverDao.getDriverByID(driverID);
        if (driver == null) {
            ExternalAlgorithms.debugPrint(driverID + " does not exists");
            return null;
        } else if (!driver.isVerified()) {
            ExternalAlgorithms.debugPrint(driverID + " is not verified");
            return null;
        } else {
            DriverInfo driverInfo = new DriverInfo();

            driverInfo.setMaxNumOfPassengers(driver.getCar().getNumPassengers());
            driverInfo.setDriverID(driver.getDriverID());
            driverInfo.setMaxNumOfPassengers(0);
            driverInfo.setCostPerKM(driver.getPreferences().getCoefficientPer());


            return driverInfo;
        }
    }

    public List<DriverInfo> getUserPreferences(int userID) {
        User user = userDao.getUserByID(userID);
        List<DriverInfo> ret = new ArrayList<>();
        for (Driver elem : driverDao.getDriverByPreferences(user)) {
            DriverInfo driverInfo = drivers.get(elem.getDriverID());
            driverInfo.update(getDriverInfo(elem.getDriverID()));

            if (driverInfo != null) {

            }

        }


        return null;
    }



}
