package ge.taxistgela.model;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.User;
import ge.taxistgela.bean.UserPreference;
import ge.taxistgela.dao.UserDaoAPI;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.helper.RegistrationHelper;

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
    public User loginUser(String username, String password) {
        return userDao.loginUser(username,password);
    }

    @Override
    public int registerUser(User user) {
        int ret;

        if(!RegistrationHelper.isValid(user))
            ret = -1;
        else
            ret = userDao.registerUser(user);
        return  ret;
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public UserPreference getUserPreferenceByID(int userPreferenceID) {
        return  userDao.getUserPreferenceByID(userPreferenceID);
    }

    @Override
    public int insertUserPreference(UserPreference userPreference) {
        return  userDao.insertUserPreference(userPreference);
    }

    @Override
    public int updateUserPreference(UserPreference userPreference) {
        return  userDao.updateUserPreference(userPreference);
    }

    @Override
    public List<User> getUsersByPreferences(Driver driver) {
        List<User> ret = userDao.getUsersByPreferences(driver);
        //TODO filter by location
        return  ret;
    }

    @Override
    public boolean checkEmail(String email) {
        return  userDao.checkEmail(email);
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        return userDao.checkPhoneNumber(phoneNumber);
    }

    @Override
    public boolean checkFacebookID(String facebookID) {
        return userDao.checkFacebookID(facebookID);
    }

    @Override
    public boolean checkGoogleID(String googleID) {
        return userDao.checkGoogleID(googleID);
    }
}
