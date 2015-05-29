package ge.taxistgela.dao;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.User;
import ge.taxistgela.bean.UserPreference;

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
     * Considers
     *  User.minimumDriverRating
     *  User.conditioning
     *  User.carYear
     *  User.passengersCount
     *  Driver.minimumUserRating
     * @param user
     * @return List of drivers generated from database.
     */
    public List<Driver> getDriverByPreferences(User user);

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
     * Checks if the driver exists with the certain carID.
     *
     * @param carID
     * @return true/false
     */
    public boolean checkCarID(String carID);

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

    /**
     * Check if the driver exists with the certain facebookID. null if he has not linked to profile.
     *
     * @param facebookID
     * @return true/false
     */
    public boolean checkFacebookID(String facebookID);


    /**
     * Check if the driver exists with the certain googleID. null if he has not linked to profile.
     *
     * @param googleID
     * @return true/false
     */
    public boolean checkGoogleID(String googleID);
}
