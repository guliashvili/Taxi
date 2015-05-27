package ge.taxistgela.model;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.UserPreferences;
import ge.taxistgela.dao.DriverDaoAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract  class DriverManagerAPI {
    protected DriverDaoAPI driverDao;
    public  DriverManagerAPI(DriverDaoAPI driverDao){
        this.driverDao = driverDao;
    }

    /**
     * Returns Driver selected by the certain driverID.
     *
     * @param driverID
     * @return Driver generated from database.
     */
    public abstract Driver getDriveByID(int driverID);

    /**
     * Returns Drivers selected by the user preferences.
     * Does not consider TimeLimit
     * @param userPreferences
     * @return List of drivers generated from database.
     */
    public abstract List<Driver> getDriverByPreferences(UserPreferences userPreferences);

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
    public abstract int registerDriver(Driver driver);

    /**
     * Updates the certain driver with the new data.
     *
     * @param driver
     * @return operationCode
     */
    public abstract int updateDriver(Driver driver);

}
