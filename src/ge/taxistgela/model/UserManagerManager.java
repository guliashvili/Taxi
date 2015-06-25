package ge.taxistgela.model;

import ge.taxistgela.bean.*;
import ge.taxistgela.dao.UserDaoAPI;
import ge.taxistgela.helper.HashGenerator;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public class UserManagerManager extends UserManagerManagerAPI {
    public UserManagerManager(UserDaoAPI userDao) {
        super(userDao);
    }

    @Override
    public User getByID(Integer userID) {
        return userDao.getUserByID(userID);
    }


    @Override
    public User login(String username, String password) {
        return userDao.loginUser(username, password);
    }

    private ErrorCode getErrorsUser(User user) {
        ErrorCode ret = new ErrorCode();
        ret.union(user.isValid());
        if (checkEmail(user.getEmail()))
            ret.emailDuplicate();
        if (checkPhoneNumber(user.getPhoneNumber()))
            ret.phoneNumberDuplicate();
        if (checkGoogleID(user.getGoogleID()))
            ret.googleIDDuplicate();
        if (checkFacebookID(user.getFacebookID()))
            ret.facebookIDDuplicate();

        return ret;
    }

    @Override
    public ErrorCode verifyEmail(String token) {
        ErrorCode ret = new ErrorCode();
        token = HashGenerator.decryptAES(token);
        if (token == null) {
            ret.setWrongToken();
        } else {
            User u = userDao.getUserByEmail(token);
            if (u.getIsVerifiedEmail()) ret.setAlreadyVerified();
            else {
                if (userDao.verifyUserEmail(token)) ret.unexpected();
            }
        }

        return ret;
    }

    @Override
    public ErrorCode verifyPhoneNumber(String token) {
        ErrorCode ret = new ErrorCode();
        token = HashGenerator.decryptAES(token);
        if (token == null) {
            ret.setWrongToken();
        } else {
            User u = userDao.getUserByPhoneNumber(token);
            if (u.getIsVerifiedPhone()) ret.setAlreadyVerified();
            else {
                if (userDao.verifyUserPhoneNumber(token)) ret.unexpected();
            }
        }

        return ret;
    }

    @Override
    public ErrorCode register(GeneralCheckableInformation u) {
        ErrorCode ret = new ErrorCode();
        if (!(u instanceof User))
            ret.wrongType();
        else {
            User user = (User) u;
            ret.union(getErrorsUser(user));
            if (!ret.errorAccrued())
                if (userDao.registerUser(user))
                    ret.unexpected();
        }
        return ret;
    }

    @Override
    public ErrorCode changePassword(GeneralCheckableInformation u) {
        ErrorCode ret = new ErrorCode();
        if (!(u instanceof User))
            ret.wrongType();
        else {
            User user = (User) u;
            ret.union(getErrorsUser(user));
            if (!ret.errorAccrued())
                if (userDao.changePassword(user))
                    ret.unexpected();

        }

        return ret;
    }

    @Override
    public ErrorCode update(GeneralCheckableInformation u) {
        ErrorCode ret = new ErrorCode();
        if (!(u instanceof User))
            ret.wrongType();
        else {
            User user = (User) u;

            ret.union(getErrorsUser(user));
            if (!ret.errorAccrued())
                if (userDao.updateUser(user))
                    ret.unexpected();
        }
        return ret;
    }

    @Override
    public UserPreference getUserPreferenceByID(Integer userPreferenceID) {
        return  userDao.getUserPreferenceByID(userPreferenceID);
    }

    private ErrorCode getErrorsUserPreference(UserPreference userPreference) {
        ErrorCode ret = new ErrorCode();

        return ret;
    }
    @Override
    public ErrorCode insertUserPreference(UserPreference userPreference) {
        ErrorCode ret = getErrorsUserPreference(userPreference);
        if (!ret.errorAccrued())
            if (userDao.insertUserPreference(userPreference))
                ret.unexpected();

        return ret;
    }

    @Override
    public ErrorCode updateUserPreference(UserPreference userPreference) {
        ErrorCode ret = getErrorsUserPreference(userPreference);
        if (!ret.errorAccrued())
            if (userDao.updateUserPreference(userPreference))
                ret.unexpected();

        return ret;
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
