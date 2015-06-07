package ge.taxistgela.dao;

import ge.taxistgela.bean.Car;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.DriverPreference;
import ge.taxistgela.bean.User;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public interface DriverDaoAPI {
    /**
     * Returns car selected by the certain driverPreferenceID.
     *
     * @param carID
     * @return Car generated from database.
     */
    Car getCarByID(int carID);


    /**
     * Inserts the certain car.
     * Returns operation result.
     *
     * @param car
     * @return operationCode
     */
    boolean insertCar(Car car);

    /**
     * Updates the Car with the new data.
     *
     * @param car
     * @return operationCode
     */
    boolean updateCar(Car car);


    /**
     * Returns DriverPreference selected by the certain driverPreferenceID.
     *
     * @param driverPreferenceID
     * @return DriverPreference generated from database.
     */
    DriverPreference getDriverPreferenceByID(int driverPreferenceID);


    /**
     * Inserts the certain driverPreference.
     * Returns operation result.
     *
     * @param driverPreference
     * @return operationCode
     */
    boolean insertDriverPreference(DriverPreference driverPreference);

    /**
     * Updates the driverPreference with the new data.
     *
     * @param driverPreference
     * @return operationCode
     */
    boolean updateDriverPreference(DriverPreference driverPreference);

    /**
     * Returns Driver selected by the certain driverID.
     *
     * @param driverID
     * @return Driver generated from database.
     */
    Driver getDriverByID(int driverID);

    /**
     * Returns Drivers selected by the certain companyID.
     *
     * @param companyID
     * @return List of drivers generated from database.
     */
    List<Driver> getDriverByCompanyID(int companyID);

    /**
     * Returns Drivers selected by the user preferences.
     * Does not consider TimeLimit
     * returns null if problem
     * Considers
     *  User.minimumDriverRating
     *  User.conditioning
     *  User.carYear
     *  User.passengersCount
     *  Driver.minimumUserRating
     *  TODO
     * @param user
     * @return List of drivers generated from database.
     */
    List<Driver> getDriverByPreferences(User user);

    /**
     * Tries to login with the certain email and password
     * Returns null if no driver exists.
     *
     * @param email
     * @param password
     * @return Loggedin Driver.
     */
    Driver loginDriver(String email, String password);

    /**
     * Registers the certain driver.
     * Returns operation result.
     *
     * @param driver
     * @return operationCode
     */
    boolean registerDriver(Driver driver);

    /**
     * Updates the certain driver with the new data.
     *
     * @param driver
     * @return operationCode
     */
    boolean updateDriver(Driver driver);


    /**
     * Checks if the driver exists with the certain carID.
     *
     * @param carID
     * @return true/false
     */
    boolean checkCarID(String carID);

    /**
     * Checks if the driver exists with the certain email.
     *
     * @param email
     * @return true/false
     */
    boolean checkEmail(String email);

    /**
     * Checks if the driver exists with the certain phoneNumber.
     *
     * @param phoneNumber
     * @return true/false
     */
    boolean checkPhoneNumber(String phoneNumber);

    /**
     * Check if the driver exists with the certain facebookID. null if he has not linked to profile.
     *
     * @param facebookID
     * @return true/false
     */
    boolean checkFacebookID(String facebookID);


    /**
     * Check if the driver exists with the certain googleID. null if he has not linked to profile.
     *
     * @param googleID
     * @return true/false
     */
    boolean checkGoogleID(String googleID);
}
