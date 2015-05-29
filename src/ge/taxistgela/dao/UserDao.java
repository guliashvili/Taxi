package ge.taxistgela.dao;

import ge.taxistgela.bean.DriverPreferences;
import ge.taxistgela.bean.User;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class UserDao implements UserDaoAPI, OperationCodes {

    @Override
    public User getUserByID(int userID) {
        return null;
    }

    @Override
    public List<User> getUsersByPreferences(DriverPreferences driverPreferences) {
        return null;
    }

    @Override
    public User loginUser(String email, String password) {
        return null;
    }

    @Override
    public int registerUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        return false;
    }
}
