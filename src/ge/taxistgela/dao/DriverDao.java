package ge.taxistgela.dao;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.Location;
import ge.taxistgela.bean.UserPreferences;
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
    final static String login_STMT = "SELECT * FROM Drivers WHERE email=? AND password=?";
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
                st.setString(1,email);
                st.setString(2,password);
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

                //TODO Car

                output.setPhoneNumber(res.getString("phoneNumber"));
                output.setFacebookID(res.getString("facebookID"));
                output.setGoogleID(res.getString("googleID"));
                output.setRating(res.getDouble("rating"));
                //TODO driverPreference
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

            }
        }catch (SQLException e){

        }
        return 0;
    }

    @Override
    public int updateDriver(Driver driver) {
        try(Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(update_STMT)) {

            }
        }catch (SQLException e){

        }
        return 0;
    }
}
