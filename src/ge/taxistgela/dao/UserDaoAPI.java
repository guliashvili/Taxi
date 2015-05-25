package ge.taxistgela.dao;

import ge.taxistgela.bean.DriverPreferences;
import ge.taxistgela.bean.User;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public interface UserDaoAPI {

    // TODO Add Error Constants

    /**
     * Returns User selected by the certain userID.
     *
     * @param userID
     * @return User generated from database.
     */
    public User getUserByID(int userID);

    /**
     * Returns Users selected by the driver preferences criteria.
     *
     * @param driverPreferences
     * @return List of users generated from database.
     */
    public List<User> getUsersByPreferences(DriverPreferences driverPreferences);

    /**
     * Tries to login with the certain username/email and password.
     *
     * @param usernameOrEmail
     * @param password
     * @return
     */
    public User loginUser(String usernameOrEmail, String password);

    /**
     * Registers the certain user.
     * Returns operation result.
     *
     * @param user
     * @return errorCode
     */
    public int registerUser(User user);
}
