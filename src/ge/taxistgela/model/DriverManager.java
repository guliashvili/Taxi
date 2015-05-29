package ge.taxistgela.model;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.UserPreference;
import ge.taxistgela.dao.DriverDaoAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public class DriverManager extends  DriverManagerAPI {
    public  DriverManager(DriverDaoAPI driverDao){
        super(driverDao);
    }


    @Override
    public Driver getDriveByID(int driverID) {
        return  driverDao.getDriveByID(driverID);
    }

    @Override
    public List<Driver> getDriverByPreferences(UserPreference userPreference) {
        return  driverDao.getDriverByPreferences(userPreference);
    }

    @Override
    public Driver loginDriver(String email, String password) {
        return driverDao.loginDriver(email, password);
    }

    @Override
    public int registerDriver(Driver driver) {
        return driverDao.registerDriver(driver);
    }

    @Override
    public int updateDriver(Driver driver) {
        return 0;
    }
}
