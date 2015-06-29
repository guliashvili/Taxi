package ge.taxistgela.model;

import ge.taxistgela.bean.*;
import ge.taxistgela.dao.UserDaoAPI;
import ge.taxistgela.helper.HashGenerator;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public class UserManager extends UserManagerAPI {
    public UserManager(UserDaoAPI userDao) {
        super(userDao);
    }

    @Override
    public User getByEmail(String userEmail) {
        if (userEmail == null) return null;
        else return userDao.getUserByEmail(userEmail);
    }

    @Override
    public User getByFacebookID(String userFacebookID) {
        if (userFacebookID == null) return null;
        else return userDao.getUserByFacebookID(userFacebookID);
    }

    @Override
    public User getByGoogleID(String userGoogleID) {
        if (userGoogleID == null) return null;
        else return userDao.getUserByGoogleID(userGoogleID);
    }

    @Override
    public User getByPhoneNumber(String userPhoneNumber) {
        if (userPhoneNumber == null)
            return null;
        else return userDao.getUserByPhoneNumber(userPhoneNumber);
    }

    @Override
    public User getByID(Integer userID) {
        if (userID == null)
            return null;
        else
            return userDao.getUserByID(userID);
    }


    @Override
    public User login(String username, String password) {

        if (username == null || password == null)
            return null;
        else
            return userDao.loginUser(username, password);
    }

    private ErrorCode getErrorsUserLogin(User user) {
        ErrorCode ret = new ErrorCode();
        ret.union(user.isValid());
        if (!checkEmail(user.getEmail()))
            ret.emailDoesNotExists();
        if (!checkPhoneNumber(user.getPhoneNumber()))
            ret.phoneNumberDoesNotExists();
        if (user.getGoogleID() != null)
            if (!checkGoogleID(user.getGoogleID())) ret.googleIDDoesNotExists();
        if (user.getFacebookID() != null)
            if (!checkFacebookID(user.getFacebookID())) ret.facebookIDDoesNotExists();

        return ret;
    }

    private ErrorCode getErrorsUserUpdate(User user) {
        ErrorCode ret = new ErrorCode();
        ret.union(user.isValid());


        return ret;
    }

    private ErrorCode getErrorsUserRegister(User user) {
        ErrorCode ret = new ErrorCode();
        ret.union(user.isValid());
        if (checkEmail(user.getEmail()))
            ret.emailDuplicate();
        if (checkPhoneNumber(user.getPhoneNumber()))
            ret.phoneNumberDuplicate();
        if (user.getGoogleID() != null)
            if (checkGoogleID(user.getGoogleID())) ret.googleIDDuplicate();
        if (user.getFacebookID() != null)
            if (checkFacebookID(user.getFacebookID())) ret.facebookIDDuplicate();

        return ret;
    }
    @Override
    public Integer getIDByToken(String token) {
        if (token == null)
            return null;
        else
            return userDao.getUserIDByToken(token);
    }

    @Override
    public String getTokenByID(Integer superUserID) {

        if (superUserID == null)
            return null;
        else
            return userDao.getUserTokenByID(superUserID);
    }
    @Override
    public ErrorCode verifyEmail(String token) {
        ErrorCode ret = new ErrorCode();
        if (token == null) {
            ret.nullArgument();
        } else {
            System.out.println(token);
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
        }

        return ret;
    }

    @Override
    public ErrorCode verifyPhoneNumber(String token) {
        ErrorCode ret = new ErrorCode();
        if (token == null) {
            ret.nullArgument();
        } else {
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
        }
        return ret;
    }

    @Override
    public ErrorCode register(SuperDaoUser u) {
        ErrorCode ret = new ErrorCode();
        if (u == null)
            ret.nullArgument();
        else if (!(u instanceof User))
            ret.wrongType();
        else {
            User user = (User) u;
            ret.union(getErrorsUserRegister(user));
            if (!ret.errorAccrued())
                if (userDao.registerUser(user))
                    ret.unexpected();
        }
        return ret;
    }

    @Override
    public ErrorCode changePassword(SuperDaoUser u, String oldPassword) {
        ErrorCode ret = new ErrorCode();
        if (u == null)
            ret.nullArgument();
        else if (!(u instanceof User))
            ret.wrongType();
        else {
            User user = (User) u;
            ret.union(getErrorsUserLogin(user));
            if (userDao.loginUser(u.getEmail(), oldPassword) == null)
                ret.wrongPassword();
            else if (!ret.errorAccrued())
                if (userDao.changePassword(user))
                    ret.unexpected();

        }

        return ret;
    }

    @Override
    public ErrorCode update(SuperDaoUser u) {
        ErrorCode ret = new ErrorCode();
        if (u == null)
            ret.nullArgument();
        else if (!(u instanceof User))
            ret.wrongType();
        else {
            User user = (User) u;

            ret.union(getErrorsUserUpdate(user));
            if (!ret.errorAccrued())
                if (userDao.updateUser(user))
                    ret.unexpected();
        }
        return ret;
    }

    @Override
    public UserPreference getUserPreferenceByID(Integer userPreferenceID) {
        if (userPreferenceID == null)
            return null;
        else
            return userDao.getUserPreferenceByID(userPreferenceID);
    }

    private ErrorCode getErrorsUserPreference(UserPreference userPreference) {
        ErrorCode ret = new ErrorCode();
        if (userPreference == null)
            ret.nullArgument();
        return ret;
    }
    @Override
    public ErrorCode insertUserPreference(UserPreference userPreference) {
        ErrorCode ret = new ErrorCode();
        if (userPreference == null) {

        } else {
            ret.union(getErrorsUserPreference(userPreference));
            if (!ret.errorAccrued())
                if (userDao.insertUserPreference(userPreference))
                    ret.unexpected();
        }
        return ret;
    }

    @Override
    public ErrorCode updateUserPreference(UserPreference userPreference) {
        ErrorCode ret = new ErrorCode();
        if (userPreference == null)
            ret.nullArgument();
        else {
            ret.union(getErrorsUserPreference(userPreference));
            if (!ret.errorAccrued())
                if (userDao.updateUserPreference(userPreference))
                    ret.unexpected();
        }
        return ret;
    }

    @Override
    public List<User> getUsersByPreferences(Driver driver) {
        List<User> ret = null;
        if (driver != null) {
            userDao.getUsersByPreferences(driver);
        }
        //TODO filter by location
        return  ret;
    }

    @Override
    public boolean checkEmail(String email) {
        if (email == null) return false;
        else return userDao.checkEmail(email);
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        else return userDao.checkPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean checkFacebookID(String facebookID) {
        if (facebookID == null) return null;
        else return userDao.checkFacebookID(facebookID);
    }

    @Override
    public Boolean checkGoogleID(String googleID) {
        if (googleID == null) return null;
        else return userDao.checkGoogleID(googleID);
    }
}
