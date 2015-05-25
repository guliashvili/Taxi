package ge.taxistgela.dao;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.UserPreferences;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class DriverDao implements DriverDaoAPI, OperationCodes {

    @Override
    public Driver getDriveByID(int driverID) {
        return null;
    }

    @Override
    public List<Driver> getDriverByPreferences(UserPreferences userPreferences) {
        return null;
    }

    @Override
    public Driver loginDriver(String usernameOrEmail, String password) {
        return null;
    }

    @Override
    public int registerDriver(Driver driver) {
        return 0;
    }
}
