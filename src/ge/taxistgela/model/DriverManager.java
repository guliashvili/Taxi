package ge.taxistgela.model;

import ge.taxistgela.bean.*;
import ge.taxistgela.dao.DriverDaoAPI;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.helper.RegistrationHelper;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public class DriverManager extends  DriverManagerAPI {
    public  DriverManager(DriverDaoAPI driverDao){
        super(driverDao);
    }


    @Override
    public Driver getDriverByID(int driverID) {
        return  driverDao.getDriverByID(driverID);
    }

    @Override
    public List<Driver> getDriverByCompanyID(int companyID) {
        return driverDao.getDriverByCompanyID(companyID);
    }

    @Override
    public Driver loginDriver(String email, String password) {
        return driverDao.loginDriver(email, password);
    }

    @Override
    public int registerDriver(Driver driver) {
        int ret;
        if(!RegistrationHelper.isValid(driver))
            ret = -1;
        else
            ret = driverDao.registerDriver(driver);
        return  ret;
    }

    @Override
    public int updateDriver(Driver driver) {
        return 0;
    }

    @Override
    public Car getCarByID(int carID) {
        return driverDao.getCarByID(carID);
    }

    @Override
    public int insertCar(Car car) {
        return driverDao.insertCar(car);
    }

    @Override
    public int updateCar(Car car) {
        return driverDao.updateCar(car);
    }

    @Override
    public DriverPreference getDriverPreferenceByID(int driverPreferenceID) {
        return driverDao.getDriverPreferenceByID(driverPreferenceID);

    }

    @Override
    public int insertDriverPreference(DriverPreference driverPreference) {
        return driverDao.insertDriverPreference(driverPreference);
    }

    @Override
    public int updateDriverPreference(DriverPreference driverPreference) {
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
