package ge.taxistgela.dao;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.DriverPreference;
import ge.taxistgela.bean.User;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public interface UserDaoAPI {

    /**
     * Returns User selected by the certain userID.
     *
     * @param userID
     * @return User generated from database.
     */
    public User getUserByID(int userID);

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
    public List<User> getUsersByPreferences(Driver driver);

    /**
     * Tries to login with the certain email and password.
     * Returns null if no user exists.
     *
     * @param email
     * @param password
     * @return Loggedin User.
     */
    public User loginUser(String email, String password);

    /**
     * Registers the certain user.
     * Returns operation result.
     *
     * @param user
     * @return operationCode
     */
    public int registerUser(User user);

    /**
     * Updates the user order with the new data.
     *
     * @param user
     * @return operationCode
     */
    public int updateUser(User user);

    /**
     * Checks if the user exists with the certain email.
     *
     * @param email
     * @return true/false
     */
    public boolean checkEmail(String email);

    /**
     * Checks if the user exists with the certain phoneNumber.
     *
     * @param phoneNumber
     * @return true/false
     */
    public boolean checkPhoneNumber(String phoneNumber);


    /**
     * Check if the user exists with the certain facebookID. null if he has not linked to profile.
     *
     * @param facebookID
     * @return true/false
     */
    public boolean checkFacebookID(String facebookID);


    /**
     * Check if the user exists with the certain googleID. null if he has not linked to profile.
     *
     * @param googleID
     * @return true/false
     */
    public boolean checkGoogleID(String googleID);

}
