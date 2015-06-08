package ge.taxistgela.model;

import ge.taxistgela.bean.Car;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.DriverPreference;
import ge.taxistgela.bean.User;
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
    public Driver getDriverByID(Integer driverID) {
        return  driverDao.getDriverByID(driverID);
    }

    @Override
    public List<Driver> getDriverByCompanyID(Integer companyID) {
        return driverDao.getDriverByCompanyID(companyID);
    }

    @Override
    public Driver loginDriver(String email, String password) {
        return driverDao.loginDriver(email, password);
    }

    @Override
    public boolean registerDriver(Driver driver) {
        boolean errorCode;
        if (driver == null || !driver.isValid())
            errorCode = true;
        else
            errorCode = driverDao.registerDriver(driver);
        return  errorCode;
    }

    @Override
    public boolean updateDriver(Driver driver) {
        boolean errorCode;
        if (driver == null || !driver.isValid())
            errorCode = true;
        else
            errorCode = driverDao.updateDriver(driver);
        return  errorCode;
    }

    @Override
    public Car getCarByID(String carID) {
        return driverDao.getCarByID(carID);
    }

    @Override
    public boolean insertCar(Car car) {
        return driverDao.insertCar(car);
    }

    @Override
    public boolean updateCar(Car car) {
        return driverDao.updateCar(car);
    }

    @Override
    public DriverPreference getDriverPreferenceByID(Integer driverPreferenceID) {
        return driverDao.getDriverPreferenceByID(driverPreferenceID);

    }

    @Override
    public boolean insertDriverPreference(DriverPreference driverPreference) {
        return driverDao.insertDriverPreference(driverPreference);
    }

    @Override
    public boolean updateDriverPreference(DriverPreference driverPreference) {
        return driverDao.updateDriverPreference(driverPreference);
    }

    @Override
    public List<Driver> getDriverByPreferences(User user) {
        List<Driver> ret = driverDao.getDriverByPreferences(user);
        //TODO filter location
        return  ret;
    }

    @Override
    public boolean checkCarID(String carID) {
        return driverDao.checkCarID(carID);
    }

    @Override
    public boolean checkEmail(String email) {
        return driverDao.checkEmail(email);
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        return driverDao.checkPhoneNumber(phoneNumber);
    }

    @Override
    public boolean checkFacebookID(String facebookID) {
        return driverDao.checkFacebookID(facebookID);
    }

    @Override
    public boolean checkGoogleID(String googleID) {
        return driverDao.checkGoogleID(googleID);
    }
}
