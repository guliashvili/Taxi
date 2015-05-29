package ge.taxistgela.dao;

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
     * Returns Users selected by the driver preferences criteria.
     *  Does not consider TimeLimit
     * @param driverPreference
     * @return List of users generated from database.
     */
    public List<User> getUsersByPreferences(DriverPreference driverPreference);

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




}
