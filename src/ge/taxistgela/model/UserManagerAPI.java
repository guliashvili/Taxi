package ge.taxistgela.model;

import ge.taxistgela.bean.DriverPreference;
import ge.taxistgela.bean.User;
import ge.taxistgela.dao.UserDaoAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class UserManagerAPI {
    protected UserDaoAPI userDao;

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
     *  Does not consider TimeLimit
     * @param driverPreference
     * @return List of users generated from database.
     */
    public abstract List<User> getUsersByPreferences(DriverPreference driverPreference);

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
    public abstract int registerUser(User user);

    /**
     * Updates the user order with the new data.
     *
     * @param user
     * @return operationCode
     */
    public abstract int updateUser(User user);


}
