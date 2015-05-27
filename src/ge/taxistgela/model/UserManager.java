package ge.taxistgela.model;

import ge.taxistgela.bean.DriverPreferences;
import ge.taxistgela.bean.User;
import ge.taxistgela.dao.UserDaoAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public class UserManager extends  UserManagerAPI{
    public  UserManager(UserDaoAPI userDao){
        super(userDao);
    }

    @Override
    public User getUserByID(int userID) {
        return userDao.getUserByID(userID);
    }

    @Override
    public List<User> getUsersByPreferences(DriverPreferences driverPreferences) {
        return userDao.getUsersByPreferences(driverPreferences);
    }

    @Override
    public User loginUser(String username, String password) {
        return userDao.loginUser(username,password);
    }

    @Override
    public int registerUser(User user) {
        return userDao.registerUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}
