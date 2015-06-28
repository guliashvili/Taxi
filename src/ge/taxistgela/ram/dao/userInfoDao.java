package ge.taxistgela.ram.dao;

import ge.taxistgela.bean.User;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.dao.UserDao;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.ram.bean.DriverInfo;
import ge.taxistgela.ram.bean.UserInfo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by GIO on 6/27/2015.
 */
public class UserInfoDao {
    ConcurrentHashMap<Integer, DriverInfo> drivers;
    ConcurrentHashMap<Integer, UserInfo> users;
    private DriverDao driverDao;
    private UserDao userDao;

    public UserInfoDao(DriverDao driverDao, UserDao userDao, ConcurrentHashMap<Integer, DriverInfo> drivers, ConcurrentHashMap<Integer, UserInfo> users) {
        this.driverDao = driverDao;
        this.userDao = userDao;
        this.drivers = drivers;
        this.users = users;
    }


    public UserInfo getUserInfo(int userID) {
        User user = userDao.getUserByID(userID);
        if (user == null) {
            ExternalAlgorithms.debugPrint(userID + " does not exists");
            return null;
        } else if (!user.isVerified()) {
            ExternalAlgorithms.debugPrint(userID + " is not verified");
            return null;
        } else {
            UserInfo userInfo = new UserInfo();

            userInfo.setTimeLimit(user.getPreference().getTimeLimit());
            userInfo.setTimeLimit(user.getUserID());
            userInfo.setNumPassengers(user.getPreference().getPassengersCount());

            return userInfo;
        }
    }

}
