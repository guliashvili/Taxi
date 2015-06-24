package ge.taxistgela.model;

import ge.taxistgela.bean.*;
import ge.taxistgela.dao.DriverDaoAPI;
import ge.taxistgela.helper.HashGenerator;

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

    private ErrorCode getErrorsDriver(Driver driver) {
        ErrorCode ret = new ErrorCode();
        if (driver == null) ret.unexpected();
        else {
            ret.union(driver.isValid());
            if (checkEmail(driver.getEmail())) ret.emailDuplicate();
            if (checkPhoneNumber(driver.getPhoneNumber())) ret.phoneNumberDuplicate();
            if (checkFacebookID(driver.getFacebookID())) ret.facebookIDDuplicate();
            if (checkGoogleID(driver.getGoogleID())) ret.googleIDDuplicate();
        }
        return ret;
    }

    @Override
    public ErrorCode registerDriver(Driver driver) {
        ErrorCode ret = getErrorsDriver(driver);
        if (!ret.errorAccrued())
            if (driverDao.registerDriver(driver)) ret.unexpected();
        return ret;
    }

    @Override
    public ErrorCode changePassword(Driver driver) {
        ErrorCode ret = getErrorsDriver(driver);
        if (!ret.errorAccrued())
            if (driverDao.changePassword(driver)) ret.unexpected();
        return ret;
    }

    @Override
    public ErrorCode verifyEmail(String token) {
        ErrorCode ret = new ErrorCode();
        token = HashGenerator.decryptAES(token);
        if (token == null) {
            ret.setWrongToken();
        } else {
            Driver u = driverDao.getDriverByEmail(token);
            if (u.getIsVerifiedEmail()) ret.setAlreadyVerified();
            else {
                if (driverDao.verifyDriverEmail(token)) ret.unexpected();
            }
        }

        return ret;
    }

    @Override
    public ErrorCode verifyPhoneNumber(String token) {
        ErrorCode ret = new ErrorCode();
        token = HashGenerator.decryptAES(token);
        if (token == null) {
            ret.setWrongToken();
        } else {
            Driver u = driverDao.getDriverByPhoneNumber(token);
            if (u.getIsVerifiedPhone()) ret.setAlreadyVerified();
            else {
                if (driverDao.verifyDriverPhoneNumber(token)) ret.unexpected();
            }
        }

        return ret;
    }

    @Override
    public ErrorCode updateDriver(Driver driver) {
        ErrorCode ret = getErrorsDriver(driver);
        if (!ret.errorAccrued())
            if (driverDao.updateDriver(driver)) ret.unexpected();
        return ret;
    }

    @Override
    public Car getCarByID(String carID) {
        return driverDao.getCarByID(carID);
    }

    private ErrorCode getErrorsCar(Car car) {
        ErrorCode ret = new ErrorCode();
        if (car.getCarDescription().length() > 500)
            ret.carDescriptionLong();
        return ret;
    }

    @Override
    public ErrorCode insertCar(Car car) {
        ErrorCode ret = getErrorsCar(car);
        if (!ret.errorAccrued())
            if (driverDao.insertCar(car))
                ret.unexpected();
        return ret;
    }

    @Override
    public ErrorCode updateCar(Car car) {
        ErrorCode ret = getErrorsCar(car);
        if (!ret.errorAccrued())
            if (driverDao.updateCar(car))
                ret.unexpected();
        return ret;
    }

    @Override
    public DriverPreference getDriverPreferenceByID(Integer driverPreferenceID) {
        return driverDao.getDriverPreferenceByID(driverPreferenceID);

    }

    private ErrorCode getErrorsDriverPreference(DriverPreference driverPreference) {
        ErrorCode ret = new ErrorCode();

        return ret;
    }

    @Override
    public ErrorCode insertDriverPreference(DriverPreference driverPreference) {
        ErrorCode ret = getErrorsDriverPreference(driverPreference);
        if (!ret.errorAccrued())
            if (driverDao.insertDriverPreference(driverPreference))
                ret.unexpected();
        return ret;
    }

    @Override
    public ErrorCode updateDriverPreference(DriverPreference driverPreference) {
        ErrorCode ret = getErrorsDriverPreference(driverPreference);
        if (!ret.errorAccrued())
            if (driverDao.updateDriverPreference(driverPreference))
                ret.unexpected();
        return ret;
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
