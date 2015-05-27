package ge.taxistgela.model;

import ge.taxistgela.bean.DriverPreferences;
import ge.taxistgela.bean.User;
import ge.taxistgela.dao.UserDaoAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class UserManagerAPI {
    private UserDaoAPI userDao;

    public  UserManagerAPI(UserDaoAPI userDao){
        this.userDao = userDao;
    }


    /**
     * Returns User selected by the certain userID.
     *
     * @param userID
     * @return User generated from database.
     */
    public abstract User getUserByID(int userID);

    /**
     * Returns Users selected by the driver preferences criteria.
     *
     * @param driverPreferences
     * @return List of users generated from database.
     */
    public abstract List<User> getUsersByPreferences(DriverPreferences driverPreferences);

    /**
     * Tries to login with the certain email and password.
     *
     * @param usernameOrEmail
     * @param password
     * @return
     */
    public abstract User loginUser(String usernameOrEmail, String password);

    /**
     * Registers the certain user.
     * Returns operation result.
     *
     * @param user
     * @return errorCode
     */
    public abstract int registerUser(User user);


}
