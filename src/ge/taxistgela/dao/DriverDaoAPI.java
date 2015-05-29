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
     * Tries to login with the certain email and password
     * Returns null if no driver exists.
     *
     * @param email
     * @param password
     * @return Loggedin Driver.
     */
    public Driver loginDriver(String email, String password);

    /**
     * Registers the certain driver.
     * Returns operation result.
     *
     * @param driver
     * @return operationCode
     */
    public int registerDriver(Driver driver);

    /**
     * Updates the certain driver with the new data.
     *
     * @param driver
     * @return operationCode
     */
    public int updateDriver(Driver driver);

    /**
     * Checks if the driver exists with the certain personalID.
     *
     * @param personalID
     * @return true/false
     */
    public boolean checkPersonalID(String personalID);

    /**
     * Checks if the driver exists with the certain email.
     *
     * @param email
     * @return true/false
     */
    public boolean checkEmail(String email);

    /**
     * Checks if the driver exists with the certain phoneNumber.
     *
     * @param phoneNumber
     * @return true/false
     */
    public boolean checkPhoneNumber(String phoneNumber);
}
