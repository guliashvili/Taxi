package ge.taxistgela.dao;

import ge.taxistgela.bean.*;
import ge.taxistgela.db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class DriverDao implements DriverDaoAPI, OperationCodes {
    final static String login_STMT = "SELECT * FROM Drivers INNER JOIN Cars ON Drivers.CarID=Cars.CarID INNER JOIN DriverPreferences ON " +
            "DriverPreferences.DriverPreferenceID=Driver.DriverPreferenceID WHERE email=? AND password=?";
    final static String register_STMT = "INSERT INTO Drivers (personalID,password,email,companyID,firstName,lastName,gender,phoneNumber,carID,facebookID,googleID,rating,DriverPreferenceID,latitude,longitude,isActive)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    final static String update_STMT = "UPDATE Drivers SET personalID=?,password=?,email=?,companyID=?,firstName=?,lastName=?,gender=?,phoneNumber=?,carID=?,facebookID=?,googleID=?,rating=?,DriverPreferenceID=?,latitude=?,longitude=?,isActive=?";
    @Override
    public Driver getDriveByID(int driverID) {

        return null;
    }

    @Override
    public List<Driver> getDriverByPreferences(UserPreferences userPreferences) {
        return null;
    }

    @Override
    public Driver loginDriver(String email, String password) {
        try(Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(login_STMT)) {
                Car car = new Car();
                st.setString(1,email);
                st.setString(2,password);
                System.out.println(st.toString());
                ResultSet res = st.executeQuery();
                Driver output=new Driver();
                output.setDriverID(res.getInt("driverID"));
                output.setpersonalID(res.getString("personalID"));
                output.setEmail(res.getString("email"));
                output.setPassword(res.getString("password"));
                output.setCompanyID(res.getInt("companyID"));
                output.setFirstName(res.getString("firstName"));
                output.setLastName(res.getString("lastName"));

                output.setGender(Driver.Gender.valueOf(res.getString("gender")));
                car.setCarID(res.getString("CarID"));
                car.setCarDescription(res.getString("carDescrption"));
                car.setConditioning(res.getBoolean("conditioning"));
                car.setCarYear(res.getInt("carYear"));
                car.setNumPassengers(res.getInt("numPassengers"));

                output.setCar(car);

                output.setPhoneNumber(res.getString("phoneNumber"));
                output.setFacebookID(res.getString("facebookID"));
                output.setGoogleID(res.getString("googleID"));
                output.setRating(res.getDouble("rating"));
                DriverPreferences pref = new DriverPreferences();
                pref.setCoefficientPer(res.getDouble("coefficientPer"));
                pref.setMinimumUserRating(res.getDouble("minimumUserRating"));
                output.setPreferences(pref);
                output.setLocation(new Location(res.getBigDecimal("latitude"),res.getBigDecimal("longitude")));
                output.setIsActive(res.getBoolean("isActive"));
            }
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public int registerDriver(Driver driver) {
        try(Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(register_STMT)) {

                System.out.println(st.toString());
            }
        }catch (SQLException e){

        }
        return 0;
    }

    @Override
    public int updateDriver(Driver driver) {
        try(Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(update_STMT)) {

                System.out.println(st.toString());
            }
        }catch (SQLException e){

        }
        return 0;
    }
}
