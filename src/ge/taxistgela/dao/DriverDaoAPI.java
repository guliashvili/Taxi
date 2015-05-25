package ge.taxistgela.dao;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.UserPreferences;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public interface DriverDaoAPI {

    /**
     * Returns Driver selected by the certain driverID.
     *
     * @param driverID
     * @return Driver generated from database.
     */
    public Driver getDriveByID(int driverID);

    /**
     * Returns Drivers selected by the user preferences.
     * Does not consider TimeLimit
     * @param userPreferences
     * @return List of drivers generated from database.
     */
    public List<Driver> getDriverByPreferences(UserPreferences userPreferences);

    /**
     * Tries to login with the certain username/email and password
     * Returns null if no driver exists.
     *
     * @param usernameOrEmail
     * @param password
     * @return Loggedin Driver.
     */
    public Driver loginDriver(String usernameOrEmail, String password);

    /**
     * Registers the certain driver.
     * Returns operation result.
     *
     * @param driver
     * @return operationCode
     */
    public int registerDriver(Driver driver);
}
