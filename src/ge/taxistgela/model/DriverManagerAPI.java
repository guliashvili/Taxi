package ge.taxistgela.model;

import ge.taxistgela.bean.*;
import ge.taxistgela.dao.DriverDaoAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class DriverManagerAPI implements Verifies {
    protected DriverDaoAPI driverDao;
    public  DriverManagerAPI(DriverDaoAPI driverDao){
        this.driverDao = driverDao;
    }

    /**
     * Returns car selected by the certain driverPreferenceID.
     *
     * @param carID
     * @return Car generated from database.
     */
    public abstract Car getCarByID(String carID);


    /**
     * Inserts the certain car.
     * Returns operation result.
     *
     * @param car
     * @return operationCode
     */
    public abstract ErrorCode insertCar(Car car);

    /**
     * Updates the Car with the new data.
     * Will not change password
     *
     * @param car
     * @return operationCode
     */
    public abstract ErrorCode updateCar(Car car);

    /**
     * changes password to whats written in driver(it should be in plain text not  hash)
     *
     * @param driver
     * @return operationCode
     */
    public abstract ErrorCode changePassword(Driver driver);

    /**
     * Returns DriverPreference selected by the certain driverPreferenceID.
     *
     * @param driverPreferenceID
     * @return DriverPreference generated from database.
     */
    public abstract DriverPreference getDriverPreferenceByID(Integer driverPreferenceID);


    /**
     * Inserts the certain driverPreference.
     * Returns operation result.
     *
     * @param driverPreference
     * @return operationCode
     */
    public abstract ErrorCode insertDriverPreference(DriverPreference driverPreference);

    /**
     * Updates the driverPreference with the new data.
     *
     * @param driverPreference
     * @return operationCode
     */
    public abstract ErrorCode updateDriverPreference(DriverPreference driverPreference);

    /**
     * verifies driver email with the same token
     * errorCodes : alreadyVerified ; wrongToken
     *
     * @param token
     * @return operationCode
     */
    @Override
    public abstract ErrorCode verifyEmail(String token);

    /**
     * verifies driver phoneNumber with the same token
     * errorCodes : alreadyVerified ; wrongToken
     *
     * @param token
     * @return operationCode
     */
    @Override
    public abstract ErrorCode verifyPhoneNumber(String token);
    /**
     * Returns Driver selected by the certain driverID.
     *
     * @param driverID
     * @return Driver generated from database.
     */
    public abstract Driver getDriverByID(Integer driverID);

    /**
     * Returns Driver selected by the certain companyID.
     *
     * @param companyID
     * @return Drivers generated from database.
     */
    public abstract List<Driver> getDriverByCompanyID(Integer companyID);


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
    public abstract List<Driver> getDriverByPreferences(User user);

    /**
     * Tries to login with the certain email and password
     * Returns null if no driver exists.
     *
     * @param email
     * @param password
     * @return Loggedin Driver.
     */
    public abstract Driver loginDriver(String email, String password);

    /**
     * Registers the certain driver.
     * Returns operation result.
     *
     * @param driver
     * @return operationCode
     */
    public abstract ErrorCode registerDriver(Driver driver);

    /**
     * Updates the certain driver with the new data.
     *
     * @param driver
     * @return operationCode
     */
    public abstract ErrorCode updateDriver(Driver driver);

    /**
     * Checks if the driver exists with the certain carID.
     *
     * @param carID
     * @return true/false
     */
    public abstract boolean checkCarID(String carID);

    /**
     * Checks if the driver exists with the certain email.
     *
     * @param email
     * @return true/false
     */
    public abstract boolean checkEmail(String email);

    /**
     * Checks if the driver exists with the certain phoneNumber.
     *
     * @param phoneNumber
     * @return true/false
     */
    public abstract boolean checkPhoneNumber(String phoneNumber);

    /**
     * Check if the driver exists with the certain facebookID. null if he has not linked to profile.
     *
     * @param facebookID
     * @return true/false
     */
    public abstract boolean checkFacebookID(String facebookID);


    /**
     * Check if the driver exists with the certain googleID. null if he has not linked to profile.
     *
     * @param googleID
     * @return true/false
     */
    public abstract boolean checkGoogleID(String googleID);

}
