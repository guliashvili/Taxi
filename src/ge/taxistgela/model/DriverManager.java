package ge.taxistgela.model;

import ge.taxistgela.bean.*;
import ge.taxistgela.dao.DriverDaoAPI;
import ge.taxistgela.helper.HashGenerator;
import ge.taxistgela.ram.model.TaxRamAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public class DriverManager extends  DriverManagerAPI {
    public DriverManager(DriverDaoAPI driverDao, TaxRamAPI taxRam) {
        super(driverDao, taxRam);
    }

    @Override
    public Driver getByFacebookID(String driverFacebookID) {
        if (driverFacebookID == null) {
            return null;
        }

        return driverDao.getDriverByFacebookID(driverFacebookID);
    }

    @Override
    public Driver getByGoogleID(String driverGoogleID) {
        if (driverGoogleID == null) {
            return null;
        }

        return driverDao.getDriverByGoogleID(driverGoogleID);
    }

    @Override
    public Driver getByEmail(String driverEmail) {
        if (driverEmail == null) {
            return null;
        }

        return driverDao.getDriverByEmail(driverEmail);
    }

    @Override
    public Driver getByPhoneNumber(String driverPhoneNumber) {
        if (driverPhoneNumber == null) {
            return null;
        }

        return driverDao.getDriverByPhoneNumber(driverPhoneNumber);
    }

    @Override
    public Integer getIDByToken(String token) {
        if (token == null)
            return null;
        else
            return driverDao.getDriverIDByToken(token);
    }

    @Override
    public String getTokenByID(Integer superUserID) {
        if (superUserID == null)
            return null;
        else
            return driverDao.getDriverTokenByID(superUserID);
    }

    @Override
    public Driver getByID(Integer driverID) {
        if (driverID == null)
            return null;
        else
            return driverDao.getDriverByID(driverID);
    }

    @Override
    public List<Driver> getDriverByCompanyID(Integer companyID) {
        if (companyID == null)
            return null;
        else
            return driverDao.getDriverByCompanyID(companyID);
    }

    @Override
    public Driver login(String email, String password) {
        Driver ret = null;
        if (email != null && password != null)
            ret = driverDao.loginDriver(email, password);
        return ret;
    }

    private ErrorCode getErrorsDriverLogin(Driver driver) {
        ErrorCode ret = new ErrorCode();
        if (driver == null) ret.unexpected();
        else {
            ret.union(driver.isValid());
            if (!checkEmail(driver.getEmail())) ret.emailDoesNotExists();
            if (!checkPhoneNumber(driver.getPhoneNumber())) ret.phoneNumberDoesNotExists();
            if (driver.getFacebookID() != null)
                if (!checkFacebookID(driver.getFacebookID())) ret.facebookIDDoesNotExists();
            if (driver.getGoogleID() != null)
                if (!checkGoogleID(driver.getGoogleID())) ret.googleIDDoesNotExists();
        }
        return ret;
    }

    private ErrorCode getErrorsDriverUpdate(Driver driver) {
        ErrorCode ret = new ErrorCode();
        if (driver == null) ret.unexpected();
        else {
            ret.union(driver.isValid());

        }
        return ret;
    }

    private ErrorCode getErrorsDriverRegister(Driver driver) {
        ErrorCode ret = new ErrorCode();
        if (driver == null) ret.unexpected();
        else {
            ret.union(driver.isValid());
            if (checkEmail(driver.getEmail())) ret.emailDuplicate();
            if (checkPhoneNumber(driver.getPhoneNumber())) ret.phoneNumberDuplicate();
            if (driver.getFacebookID() != null)
                if (checkFacebookID(driver.getFacebookID())) ret.facebookIDDuplicate();
            if (driver.getGoogleID() != null)
                if (checkGoogleID(driver.getGoogleID())) ret.googleIDDuplicate();
        }
        return ret;
    }
    @Override
    public ErrorCode register(SuperDaoUser d) {
        ErrorCode ret = new ErrorCode();
        if (d == null) {
            ret.nullArgument();
        } else if (!(d instanceof Driver)) {
            ret.wrongType();
        } else {
            Driver driver = (Driver) d;
            ret.union(getErrorsDriverRegister(driver));
            if (!ret.errorAccrued())
                if (driverDao.registerDriver(driver)) ret.unexpected();

        }
        return ret;
    }

    @Override
    public ErrorCode changePassword(SuperDaoUser d, String oldPassword) {
        ErrorCode ret = new ErrorCode();
        if (d == null) {
            ret.nullArgument();
        } else if (!(d instanceof Driver)) {
            ret.wrongType();
        } else {
            Driver driver = (Driver) d;
            ret = getErrorsDriverLogin(driver);
            if (driverDao.loginDriver(d.getEmail(), oldPassword) == null)
                ret.wrongPassword();
            else if (!ret.errorAccrued())
                if (driverDao.changePassword(driver)) ret.unexpected();
        }

        return ret;
    }

    @Override
    public ErrorCode verifyCompany(String token) {
        ErrorCode ret = new ErrorCode();
        if (token == null) {
            ret.nullArgument();
        } else {
            token = HashGenerator.decryptAES(token);
            if (token == null) {
                ret.setWrongToken();
            } else {
                String[] parts = token.split("#");

                Integer driverID = Integer.parseInt(parts[0]);
                Integer companyID = Integer.parseInt(parts[1]);

                Driver u = driverDao.getDriverByID(driverID);

                if (u.getCompanyID() != null) {
                    ret.setAlreadyVerified();
                } else {
                    if (driverDao.verifyDriverCompany(driverID, companyID)) {
                        ret.unexpected();
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public ErrorCode verifyEmail(String token) {
        ErrorCode ret = new ErrorCode();
        if (token == null) {
            ret.nullArgument();
        } else {
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
        }
        return ret;
    }

    @Override
    public ErrorCode verifyPhoneNumber(String token) {
        ErrorCode ret = new ErrorCode();
        if (token == null)
            ret.nullArgument();
        else {
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
        }
        return ret;
    }

    @Override
    public ErrorCode update(SuperDaoUser d) {
        ErrorCode ret = new ErrorCode();
        if (d == null)
            ret.nullArgument();
        else if (!(d instanceof Driver)) {
            ret.wrongType();
        } else {
            Driver driver = (Driver) d;
            ret.union(getErrorsDriverUpdate(driver));
            if (!ret.errorAccrued())
                if (driverDao.updateDriver(driver)) ret.unexpected();
        }
        return ret;
    }

    @Override
    public Car getCarByID(String carID) {
        Car ret = null;
        if (carID != null)
            ret = driverDao.getCarByID(carID);
        return ret;
    }

    private ErrorCode getErrorsCar(Car car) {
        ErrorCode ret = new ErrorCode();
        if (car.getCarDescription().length() > 500)
            ret.carDescriptionLong();
        return ret;
    }

    @Override
    public ErrorCode insertCar(Car car) {
        ErrorCode ret = new ErrorCode();
        if (car == null) {
            ret.nullArgument();
        } else {
            ret.union(getErrorsCar(car));
            if (!ret.errorAccrued())
                if (driverDao.insertCar(car))
                    ret.unexpected();
        }
        return ret;
    }

    @Override
    public ErrorCode updateCar(Car car) {
        ErrorCode ret = new ErrorCode();
        if (car == null) {
            ret.nullArgument();
        } else {
            ret.union(getErrorsCar(car));
            if (!ret.errorAccrued())
                if (driverDao.updateCar(car))
                    ret.unexpected();
        }
        return ret;
    }

    @Override
    public DriverPreference getDriverPreferenceByID(Integer driverPreferenceID) {
        DriverPreference ret = null;
        if (driverPreferenceID != null)
            ret = driverDao.getDriverPreferenceByID(driverPreferenceID);
        return ret;

    }

    private ErrorCode getErrorsDriverPreference(DriverPreference driverPreference) {
        ErrorCode ret = new ErrorCode();
        if (driverPreference == null)
            ret.nullArgument();

        return ret;
    }

    @Override
    public ErrorCode insertDriverPreference(DriverPreference driverPreference) {
        ErrorCode ret = new ErrorCode();
        if (driverPreference == null) {
            ret.nullArgument();
        } else {
            ret.union(getErrorsDriverPreference(driverPreference));
            if (!ret.errorAccrued())
                if (driverDao.insertDriverPreference(driverPreference))
                    ret.unexpected();
        }
        return ret;
    }

    @Override
    public ErrorCode setDriverActiveStatus(int driverID, boolean isActive) {
        ErrorCode ret = new ErrorCode();
        if (driverDao.getDriverByID(driverID) == null) {
            ret.unexpected();
        } else if (driverDao.setDriverActiveStatus(driverID, isActive)) {
            ret.unexpected();
        }

        return ret;
    }

    @Override
    public ErrorCode updateDriverPreference(DriverPreference driverPreference) {
        ErrorCode ret = new ErrorCode();
        if (driverPreference == null)
            ret.nullArgument();
        else {
            ret.union(getErrorsDriverPreference(driverPreference));
            if (!ret.errorAccrued())
                if (driverDao.updateDriverPreference(driverPreference))
                    ret.unexpected();
        }
        return ret;
    }

    @Override
    public List<Driver> getDriverByPreferences(User user) {
        List<Driver> ret = null;

        if (user != null) {
            ret = driverDao.getDriverByPreferences(user);
        }


        //TODO filter location
        return  ret;
    }

    @Override
    public boolean checkCarID(String carID) {
        if (carID == null) return false;
        else return driverDao.checkCarID(carID);
    }

    @Override
    public boolean checkEmail(String email) {
        if (email == null) return false;
        else return driverDao.checkEmail(email);
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        else return driverDao.checkPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean checkFacebookID(String facebookID) {
        if (facebookID == null) return null;
        else return driverDao.checkFacebookID(facebookID);
    }

    @Override
    public Boolean checkGoogleID(String googleID) {
        if (googleID == null) return null;
        else return driverDao.checkGoogleID(googleID);
    }
}
