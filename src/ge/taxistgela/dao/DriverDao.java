package ge.taxistgela.dao;

import ge.taxistgela.bean.*;
import ge.taxistgela.db.DBConnectionProvider;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.helper.HashGenerator;
import ge.taxistgela.helper.PreparedStatementEnhanced;
import ge.taxistgela.helper.ResultSetEnhanced;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class DriverDao implements DriverDaoAPI {
    private final static String base_join_select_STMT = " SELECT * FROM Drivers INNER JOIN Cars ON Drivers.CarID=Cars.CarID INNER JOIN DriverPreferences ON " +
            "DriverPreferences.driverPreferenceID=Drivers.driverPreferenceID ";
    private final static String base_select_STMT = "SELECT * FROM Drivers ";
    private final static String login_STMT = base_select_STMT + " WHERE email=? AND password=?";
    private final static String driverById_STMT = base_select_STMT + "WHERE driverID = ?";
    private final static String driverByCompanyId_STMT = base_select_STMT + "WHERE companyID=?";
    private final static String register_STMT = "INSERT INTO " +
            "Drivers (personalID,password,email,companyID,firstName,lastName,gender,phoneNumber,carID," +
            "facebookID,googleID,rating,DriverPreferenceID,isActive,isVerifiedEmail,isVerifiedPhone,token)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String update_STMT = "UPDATE Drivers " +
            "SET personalID=?,email=?,companyID=?,firstName=?,lastName=?,gender=?,phoneNumber=?,carID=?,facebookID=?,googleID=?," +
            "rating=?,driverPreferenceID=?,isActive=?,isVerifiedEmail=?,isVerifiedPhone=?,token=? " +
            " WHERE driverID = ?";

    private final static String preferences_STMT = base_join_select_STMT +
            " WHERE " +
            "Drivers.rating >= ? AND " +
            "(NOT ? OR Cars.conditioning) AND " +
            "Cars.carYear >= ? AND " +
            "Cars.numPassengers >= ? AND " +
            "? >= DriverPreferences.minimumUserRating AND " +
            "Drivers.isActive AND " +
            "Drivers.isVerifiedPhone=TRUE AND Drivers.isVerifiedEmail=TRUE";

    private final static String insert_car_STMT = "INSERT INTO Cars(carID,carDescription,carYear,conditioning,numPassengers) " +
            "VALUES (?,?,?,?,?)";
    private final static String update_car_STMT = "UPDATE Cars " +
            "SET carDescription=?,carYear=?,conditioning=?,numPassengers=? " +
            "WHERE carID=?";

    private final static String insert_preference_STMT = "INSERT INTO DriverPreferences(minimumUserRating,coefficientPer)" +
            "VALUES (?,?)";
    private final static String update_preference_STMT = "UPDATE DriverPreferences " +
            "SET minimumUserRating=?,coefficientPer=?" +
            "WHERE driverPreferenceID=?";

    private boolean setStringsCar(PreparedStatementEnhanced st, Car car, boolean update) {
        boolean errorCode = false;
        int x = 1;
        try {
            if (!update)
                st.setString(x++, car.getCarID());
            st.setString(x++, car.getCarDescription());
            st.setInt(x++, car.getCarYear());
            st.setBoolean(x++, car.hasConditioning());
            st.setInt(x++, car.getNumPassengers());
            if (update)
                st.setString(x++, car.getCarID());
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    private Car getCar(ResultSetEnhanced res) {
        Car car = new Car();
        try {
            car.setCarID(res.getString("Cars.CarID"));
            car.setCarDescription(res.getString("Cars.carDescription"));
            car.setCarYear(res.getInt("Cars.carYear"));
            car.setConditioning(res.getBoolean("Cars.conditioning"));
            car.setNumPassengers(res.getInt("Cars.numPassengers"));

        } catch (SQLException e) {
            car = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return car;
    }

    @Override
    public Boolean setDriverActiveStatus(int driverID, boolean isActive) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("" +
                    "UPDATE Drivers SET isActive=? WHERE driverID=?"))) {

                st.setBoolean(1, isActive);
                st.setInt(2, driverID);
                ExternalAlgorithms.debugPrintSelect("setDriverActiveStatus\n" + st.toString());

                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public boolean verifyDriverEmail(String email) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("" +
                    "UPDATE Drivers SET isVerifiedEmail=TRUE WHERE email=?"))) {

                st.setString(1, email);
                ExternalAlgorithms.debugPrintSelect("Verify  Driver email password\n" + st.toString());

                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public boolean verifyDriverPhoneNumber(String phoneNumber) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("" +
                    "UPDATE Drivers SET isVerifiedPhone=TRUE WHERE phoneNumber=?"))) {

                st.setString(1, phoneNumber);
                ExternalAlgorithms.debugPrintSelect("Verify  Driver phoneNumber password\n" + st.toString());

                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }


    @Override
    public Car getCarByID(String carID) {

        Car output;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT * FROM Cars WHERE Cars.carID = ?"))) {

                st.setString(1, carID);


                ExternalAlgorithms.debugPrintSelect("get car by id \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();

                if (res.next()) output = getCar(res);
                else output = null;
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }

    @Override
    public boolean insertCar(Car car) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(insert_car_STMT, PreparedStatement.RETURN_GENERATED_KEYS))) {

                errorCode |= setStringsCar(st, car, false);
                ExternalAlgorithms.debugPrintSelect("insert car \n" + st.toString());


                st.executeUpdate();
                ResultSetEnhanced res = st.getGeneratedKeys();


            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);

        }
        return errorCode;

    }

    @Override
    public boolean updateCar(Car car) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(update_car_STMT))) {

                errorCode |= setStringsCar(st, car, true);


                ExternalAlgorithms.debugPrintSelect("update car \n" + st.toString());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);

        }
        return errorCode;

    }


    private DriverPreference getDriverPreference(ResultSetEnhanced res) {
        DriverPreference pref = new DriverPreference();
        try {
            pref.setDriverPreferenceID(res.getInt("DriverPreferences.driverPreferenceID"));
            pref.setCoefficientPer(res.getDouble("DriverPreferences.coefficientPer"));
            pref.setMinimumUserRating(res.getDouble("DriverPreferences.minimumUserRating"));

        } catch (SQLException e) {
            pref = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return pref;
    }

    @Override
    public DriverPreference getDriverPreferenceByID(int driverPreferenceID) {
        DriverPreference output;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT * FROM DriverPreferences WHERE DriverPreferences.driverPreferenceID = ?"))) {

                st.setInt(1, driverPreferenceID);

                ExternalAlgorithms.debugPrintSelect("get DriverPreference by id \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();

                if (res.next()) output = getDriverPreference(res);
                else output = null;
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }

    private boolean setStringsPreference(PreparedStatementEnhanced pt, DriverPreference dp, boolean update) {
        boolean errorCode = false;
        try {
            pt.setDouble(1, dp.getMinimumUserRating());
            pt.setDouble(2, dp.getCoefficientPer());
            if (update)
                pt.setInt(3, dp.getDriverPreferenceID());
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public boolean insertDriverPreference(DriverPreference driverPreference) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(insert_preference_STMT, PreparedStatement.RETURN_GENERATED_KEYS))) {

                errorCode |= setStringsPreference(st, driverPreference, false);

                ExternalAlgorithms.debugPrintSelect("insertDriverPreference \n" + st.toString());

                st.executeUpdate();
                ResultSetEnhanced res = st.getGeneratedKeys();
                if (res.next()) {
                    driverPreference.setDriverPreferenceID(res.getInt(1));
                } else {
                    errorCode = true;

                }

            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);

        }
        return errorCode;

    }

    @Override
    public boolean updateDriverPreference(DriverPreference driverPreference) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(update_preference_STMT))) {

                errorCode |= setStringsPreference(st, driverPreference, true);

                ExternalAlgorithms.debugPrintSelect("update Driver Preference \n" + st.toString());

                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);

        }
        return errorCode;
    }

    private Driver getDriver(ResultSetEnhanced res) {

        Driver output = new Driver();

        try {
            output.setDriverID(res.getInt("Drivers.driverID"));
            output.setPersonalID(res.getString("Drivers.personalID"));
            output.setPassword(res.getString("Drivers.password"));
            output.setEmail(res.getString("Drivers.email"));
            output.setCompanyID(res.getInt("Drivers.companyID"));
            output.setFirstName(res.getString("Drivers.firstName"));
            output.setLastName(res.getString("Drivers.lastName"));
            output.setGender(Gender.valueOf(res.getString("Drivers.gender")));
            output.setPhoneNumber(res.getString("Drivers.phoneNumber"));


            String carID = res.getString("Drivers.carID");
            Car car = getCarByID(carID);
            output.setCar(car);

            output.setFacebookID(res.getString("Drivers.facebookID"));
            output.setGoogleID(res.getString("Drivers.googleID"));
            output.setRating(res.getDouble("Drivers.rating"));

            int driverPreferenceID = res.getInt("Drivers.driverPreferenceID");
            DriverPreference pref = getDriverPreferenceByID(driverPreferenceID);
            output.setPreferences(pref);

            output.setIsActive(res.getBoolean("Drivers.isActive"));
            output.setIsVerifiedEmail(res.getBoolean("Drivers.isVerifiedEmail"));
            output.setIsVerifiedPhone(res.getBoolean("Drivers.isVerifiedPhone"));

            output.setToken(res.getString("Drivers.token"));
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }


    @Override
    public Integer getDriverIDByToken(String token) {
        Integer output;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT driverID FROM Drivers WHERE token=?"))) {

                st.setString(1, token);

                ExternalAlgorithms.debugPrintSelect("get DriverID by token \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                if (res.next()) output = res.getInt("Drivers.driverID");
                else output = null;
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }

    @Override
    public String getDriverTokenByID(Integer driverID) {
        String output;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT token FROM Drivers WHERE driverID=?"))) {

                st.setInt(1, driverID);

                ExternalAlgorithms.debugPrintSelect("get Driver token by ID \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                if (res.next()) output = res.getString("Drivers.token");
                else output = null;
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }

    @Override
    public Driver getDriverByID(int driverID) {
        Driver output;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(driverById_STMT))) {

                st.setInt(1, driverID);

                ExternalAlgorithms.debugPrintSelect("get Driver by ID \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                if (res.next()) output = getDriver(res);
                else output = null;
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }

    @Override
    public Driver getDriverByEmail(String email) {
        Driver output;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT * FROM drivers WHERE email=?"))) {

                st.setString(1, email);

                ExternalAlgorithms.debugPrintSelect("getDriverByEmail \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                if (res.next()) output = getDriver(res);
                else output = null;
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }

    @Override
    public Driver getDriverByPhoneNumber(String phoneNumber) {
        Driver output;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT * FROM drivers WHERE phoneNumber=?"))) {

                st.setString(1, phoneNumber);

                ExternalAlgorithms.debugPrintSelect("getDriverByPhoneNumber \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                if (res.next()) output = getDriver(res);
                else output = null;
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }

    @Override
    public Driver getDriverByFacebookID(String facebookID) {
        Driver output;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT * FROM drivers WHERE facebookID=?"))) {

                st.setString(1, facebookID);

                ExternalAlgorithms.debugPrintSelect("getDriverByFacebookID \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                if (res.next()) output = getDriver(res);
                else output = null;
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }

    @Override
    public Driver getDriverByGoogleID(String googleID) {
        Driver output;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT * FROM drivers WHERE googleID=?"))) {

                st.setString(1, googleID);

                ExternalAlgorithms.debugPrintSelect("getDriverByGoogleID \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                if (res.next()) output = getDriver(res);
                else output = null;
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }


    @Override
    public List<Driver> getDriverByCompanyID(int companyID) {
        List<Driver> output = new ArrayList<>();
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(driverByCompanyId_STMT))) {

                st.setInt(1, companyID);

                ExternalAlgorithms.debugPrintSelect("getDriverByCompanyID \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                while (res.next()) {
                    output.add(getDriver(res));
                }
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }

    @Override
    public List<Driver> getDriverByPreferences(User user) {
        List<Driver> output = new ArrayList<>();
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(preferences_STMT))) {

                st.setDouble(1, user.getPreference().getMinimumDriverRating());
                st.setBoolean(2, user.getPreference().isConditioning());
                st.setInt(3, user.getPreference().getCarYear());
                st.setInt(4, user.getPreference().getPassengersCount());
                st.setDouble(5, user.getRating());


                ExternalAlgorithms.debugPrintSelect("getDriverByPreferences \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                while (res.next())
                    output.add(getDriver(res));
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }

    @Override
    public Driver loginDriver(String email, String password) {
        Driver output;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(login_STMT))) {

                st.setString(1, email);
                st.setString(2, HashGenerator.getSaltHash(password));

                ExternalAlgorithms.debugPrintSelect("loginDriver \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();

                if (res.next()) output = getDriver(res);
                else output = null;
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;
    }

    /*(personalID,password,email,companyID,firstName,lastName,gender,phoneNumber,carID,facebookID,googleID,rating,driverPreferenceID,latitude,longitude,isActive)
        sets strings with that order
    */
    private boolean setStrings(PreparedStatementEnhanced st, Driver driver, boolean update) {
        boolean errorCode = false;
        try {
            int x = 1;
            st.setString(x++, driver.getPersonalID());
            if (!update) st.setString(x++, HashGenerator.getSaltHash(driver.getPassword()));
            st.setString(x++, driver.getEmail());
            st.setInt(x++, driver.getCompanyID());
            st.setString(x++, driver.getFirstName());
            st.setString(x++, driver.getLastName());
            st.setString(x++, driver.getGender().name());
            st.setString(x++, driver.getPhoneNumber());
            st.setString(x++, driver.getCar().getCarID());
            st.setString(x++, driver.getFacebookID());
            st.setString(x++, driver.getGoogleID());
            st.setDouble(x++, driver.getRating());
            st.setInt(x++, driver.getPreferences().getDriverPreferenceID());
            st.setBoolean(x++, driver.isActive());
            st.setBoolean(x++, driver.getIsVerifiedEmail());
            st.setBoolean(x++, driver.getIsVerifiedPhone());
            st.setString(x++, driver.getToken());
            if (update)
                st.setInt(x++, driver.getDriverID());

        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public boolean changePassword(Driver driver) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("" +
                    "UPDATE Drivers SET password=? WHERE driverID=?"))) {

                st.setString(1, HashGenerator.getSaltHash(driver.getPassword()));
                st.setInt(2, driver.getDriverID());


                ExternalAlgorithms.debugPrintSelect("Update Driver password\n" + st.toString());

                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public boolean registerDriver(Driver driver) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(register_STMT, PreparedStatement.RETURN_GENERATED_KEYS))) {


                errorCode |= setStrings(st, driver, false);

                ExternalAlgorithms.debugPrintSelect("register driver\n" + st.toString());
                st.executeUpdate();
                ResultSetEnhanced res = st.getGeneratedKeys();
                if (res.next()) {
                    driver.setDriverID(res.getInt(1));
                } else {
                    errorCode = true;

                }

            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);

        }
        return errorCode;

    }

    @Override
    public boolean updateDriver(Driver driver) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(update_STMT))) {

                errorCode |= setStrings(st, driver, true);

                ExternalAlgorithms.debugPrintSelect("updateDriver \n" + st.toString());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);

        }
        return errorCode;
    }

    @Override
    public boolean checkCarID(String carID) {
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT driverID FROM drivers WHERE  carID = ?"))) {

                st.setString(1, carID);

                ExternalAlgorithms.debugPrintSelect("checkCarID \n" + st.toString());

                ResultSetEnhanced res = st.executeQuery();
                return res.next();
            }
        } catch (SQLException e) {
            ExternalAlgorithms.debugPrint(e);

        }
        return false;
    }

    @Override
    public boolean checkEmail(String email) {
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT driverID FROM drivers WHERE  email = ?"))) {

                st.setString(1, email);

                ExternalAlgorithms.debugPrintSelect("check email \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                return res.next();
            }
        } catch (SQLException e) {
            ExternalAlgorithms.debugPrint(e);

        }
        return false;
    }

    @Override
    public boolean checkDriverID(int driverID) {
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT driverID FROM drivers WHERE  driverID = ?"))) {

                st.setInt(1, driverID);

                ExternalAlgorithms.debugPrintSelect("checkDriverID \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                return res.next();
            }
        } catch (SQLException e) {
            ExternalAlgorithms.debugPrint(e);

        }
        return false;
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT driverID FROM drivers WHERE  phoneNumber = ?"))) {

                st.setString(1, phoneNumber);

                ExternalAlgorithms.debugPrintSelect("checkPhoneNumber \n" + st.toString());
                ResultSetEnhanced res = st.executeQuery();
                return res.next();
            }
        } catch (SQLException e) {
            ExternalAlgorithms.debugPrint(e);

        }
        return false;
    }

    @Override
    public boolean checkFacebookID(String facebookID) {
        if (facebookID != null)
            try (Connection con = DBConnectionProvider.getConnection()) {
                try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT driverID FROM drivers WHERE  facebookID = ?"))) {

                    st.setString(1, facebookID);

                    ExternalAlgorithms.debugPrintSelect("checkFacebookID \n" + st.toString());
                    ResultSetEnhanced res = st.executeQuery();
                    return res.next();
                }
            } catch (SQLException e) {
                ExternalAlgorithms.debugPrint(e);

            }
        return false;
    }

    @Override
    public boolean checkGoogleID(String googleID) {
        if (googleID != null)
            try (Connection con = DBConnectionProvider.getConnection()) {
                try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT driverID FROM drivers WHERE  googleID = ?"))) {

                    st.setString(1, googleID);

                    ExternalAlgorithms.debugPrintSelect("checkGoogleID \n" + st.toString());
                    ResultSetEnhanced res = st.executeQuery();
                    return res.next();
                }
            } catch (SQLException e) {
                ExternalAlgorithms.debugPrint(e);

            }
        return false;
    }
}
