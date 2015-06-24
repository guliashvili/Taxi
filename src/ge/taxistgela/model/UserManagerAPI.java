package ge.taxistgela.model;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.ErrorCode;
import ge.taxistgela.bean.User;
import ge.taxistgela.bean.UserPreference;
import ge.taxistgela.dao.UserDaoAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class UserManagerAPI implements Verifies {
    protected UserDaoAPI userDao;

    public  UserManagerAPI(UserDaoAPI userDao){
        this.userDao = userDao;
    }

    /**
     * Returns UserPreference selected by the certain userPreferenceID.
     *
     * @param userPreferenceID
     * @return UserPreference generated from database.
     */
    public abstract UserPreference getUserPreferenceByID(Integer userPreferenceID);


    /**
     * Inserts the certain UserPreference.
     * Returns operation result.
     *
     * @param userPreference
     * @return operationCode
     */
    public abstract ErrorCode insertUserPreference(UserPreference userPreference);

    /**
     * Updates the userPreference with the new data.
     *
     * @param userPreference
     * @return operationCode
     */
    public abstract ErrorCode updateUserPreference(UserPreference userPreference);



    /**
     * Returns User selected by the certain userID.
     *
     * @param userID
     * @return User generated from database.
     */
    public abstract User getUserByID(Integer userID);

    /**
     * Returns Users selected by the driver  criteria.
     * Does not consider TimeLimit
     * returns null if problem
     * Considers
     *  User.minimumDriverRating
     *  User.conditioning
     *  User.carYear
     *  User.passengersCount
     *  Driver.minimumUserRating
     * @param driver
     * @return List of users generated from database.
     */
    public abstract List<User> getUsersByPreferences(Driver driver);


    /**
     * verifies user email with the same token
     * errorCodes : alreadyVerified ; wrongToken
     *
     * @param token
     * @return operationCode
     */
    @Override
    public abstract ErrorCode verifyEmail(String token);

    /**
     * verifies user phoneNumber with the same token
     * errorCodes : alreadyVerified ; wrongToken
     *
     * @param token
     * @return operationCode
     */
    @Override
    public abstract ErrorCode verifyPhoneNumber(String token);

    /**
     * Tries to login with the certain email and password.
     * Returns null if no user exists.
     *
     * @param email
     * @param password
     * @return Loggedin User.
     */
    public abstract User loginUser(String email, String password);

    /**
     * Registers the certain user.
     * Returns operation result.
     *
     * @param user
     * @return operationCode
     */
    public abstract ErrorCode registerUser(User user);

    /**
     * Updates the user order with the new data.
     * Will not change password
     *
     * @param user
     * @return operationCode
     */
    public abstract ErrorCode updateUser(User user);

    /**
     * changes password to whats written in user(it should be in plain text not  hash)
     *
     * @param user
     * @return operationCode
     */
    public abstract ErrorCode changePassword(User user);

    /**
     * Checks if the user exists with the certain email.
     *
     * @param email
     * @return true/false
     */
    public abstract boolean checkEmail(String email);

    /**
     * Checks if the user exists with the certain phoneNumber.
     *
     * @param phoneNumber
     * @return true/false
     */
    public abstract boolean checkPhoneNumber(String phoneNumber);


    /**
     * Check if the user exists with the certain facebookID. null if he has not linked to profile.
     *
     * @param facebookID
     * @return true/false
     */
    public abstract boolean checkFacebookID(String facebookID);


    /**
     * Check if the user exists with the certain googleID. null if he has not linked to profile.
     *
     * @param googleID
     * @return true/false
     */
    public abstract boolean checkGoogleID(String googleID);
}
