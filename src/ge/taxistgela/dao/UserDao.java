package ge.taxistgela.dao;

import ge.taxistgela.bean.DriverPreference;
import ge.taxistgela.bean.User;
import ge.taxistgela.db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class UserDao implements UserDaoAPI, OperationCodes {

    @Override
    public User getUserByID(int userID) {
        return null;
    }

    @Override
    public List<User> getUsersByPreferences(DriverPreference driverPreference) {
        return null;
    }

    @Override
    public User loginUser(String email, String password) {
        return null;
    }

    @Override
    public int registerUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public boolean checkEmail(String email) {
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement("SELECT userID FROM users WHERE  email = ?")) {
                st.setString(1,email);
                ResultSet res = st.executeQuery();
                return res.next();
            }
        }catch(SQLException e){

        }
        return false;
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement("SELECT userID FROM users WHERE  phoneNumber = ?")) {
                st.setString(1,phoneNumber);
                ResultSet res = st.executeQuery();
                return res.next();
            }
        }catch(SQLException e){

        }
        return false;
    }

    @Override
    public boolean checkFacebookID(String facebookID) {
        if(facebookID != null)
            try(Connection con = DBConnectionProvider.getConnection()){
                try(PreparedStatement st = con.prepareStatement("SELECT userID FROM users WHERE  googleID = ?")) {
                    st.setString(1,facebookID);
                    ResultSet res = st.executeQuery();
                    return res.next();
                }
            }catch(SQLException e){

            }
        return false;
    }


    @Override
    public boolean checkGoogleID(String googleID) {
        if(googleID != null)
            try(Connection con = DBConnectionProvider.getConnection()){
                try(PreparedStatement st = con.prepareStatement("SELECT userID FROM users WHERE  googleID = ?")) {
                    st.setString(1,googleID);
                    ResultSet res = st.executeQuery();
                    return res.next();
                }
            }catch(SQLException e){

            }
        return false;
    }
}
