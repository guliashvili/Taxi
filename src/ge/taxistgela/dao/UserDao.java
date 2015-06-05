package ge.taxistgela.dao;

import ge.taxistgela.bean.*;
import ge.taxistgela.db.DBConnectionProvider;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.helper.HashGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class UserDao implements UserDaoAPI, OperationCodes {
    private final static String checkMail_STM = "SELECT userID FROM Users WHERE  email = ?";
    private final static String checkPhoneNumber_STM = "SELECT userID FROM Users WHERE  phoneNumber = ?";
    private final static String checkFacebook_STM = "SELECT userID FROM Users WHERE  googleID = ?";
    private final static String checkGoogle_STM = "SELECT userID FROM Users WHERE  googleID = ?";


    private final static String base_select_STMT = "SELECT * FROM Users";
    private final static String base_joined_select_STMT = "SELECT * FROM Users INNER JOIN UserPreferences ON " +"UserPreferences.userPreferenceID=Users.userPreferenceID ";
    private final static String login_STMT = base_select_STMT + " WHERE Users.email=? AND Users.password=?";
    private final static String userByID_STMT = base_select_STMT + " WHERE Users.userID = ?";
    private final static String register_STMT = "INSERT INTO Users(password,email,firstName,lastName,phoneNumber,gender,rating,facebookID,googleID,userPreferenceID,isVerified) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private final static String update_STMT = "UPDATE Users SET password=?,email=?,firstName=?,lastName=?," +
            "phoneNumber=?,gender=?,rating=?,facebookID=?,googleID=?,Users.userPreferenceID=?,isVerified=? " +
            "WHERE Users.userID=?";
    private final static String preferences_STMT = base_joined_select_STMT +
            " WHERE " +
            "? >= UserPreferences.minimumDriverRating AND " +
            "(NOT UserPreferences.conditioning OR ?) AND " +
            "? >= UserPreferences.carYear AND " +
            "? >= UserPreferences.passengersCount AND " +
            "Users.rating >= ?";

    private final static String preference_insert_STMT =
            "INSERT INTO UserPreferences(minimumDriverRating,conditioning,carYear,passengersCount,wantsAlone,timeLimit) " +
                    "VALUES(?,?,?,?,?,?)";
    private final static String preference_update_STMT =
            "UPDATE UserPreferences SET " +
                    "userPreferenceID=?,minimumDriverRating=?,conditioning=?,carYear=?," +
                    "passengersCount=?,wantsAlone=?,timeLimit=? WHERE userPreferenceID = ?";
    private UserPreference getUserPreference(ResultSet res) {
        UserPreference up = new UserPreference();

        try {
            up.setUserPreferenceID(res.getInt("UserPreferences.userPreferenceID"));
            up.setMinimumDriverRating(res.getDouble("UserPreferences.minimumDriverRating"));
            up.setConditioning(res.getBoolean("UserPreferences.conditioning"));
            up.setCarYear(res.getInt("UserPreferences.carYear"));
            up.setPassengersCount(res.getInt("UserPreferences.passengersCount"));
            up.setWantsAlone(res.getBoolean("UserPreferences.wantsAlone"));
            up.setTimeLimit(res.getInt("UserPreferences.timeLimit"));
        } catch (SQLException e) {
            up = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return up;
    }

    @Override
    public UserPreference getUserPreferenceByID(int userPreferenceID) {
        UserPreference user;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("SELECT * FROM UserPreferences WHERE userpreferences.userPreferenceID = ?")) {
                st.setInt(1, userPreferenceID);

                ExternalAlgorithms.debugPrintSelect("getUserPreferenceByID \n" + st.toString());


                ResultSet res = st.executeQuery();
                if (res.next())
                    user = getUserPreference(res);
                else
                    user = null;
            }
        } catch (SQLException e) {
            user = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return user;
    }

    private int setStringsPreference(PreparedStatement st,UserPreference up,boolean update){
        int errorCode = 0;
        try {
            st.setDouble(1,up.getMinimumDriverRating());
            st.setBoolean(2,up.isConditioning());
            st.setInt(3,up.getCarYear());
            st.setInt(4,up.getPassengersCount());
            st.setBoolean(5,up.isWantsAlone());
            st.setInt(6,up.getTimeLimit());
            if(update)
                st.setInt(7,up.getUserPreferenceID());
        }catch(SQLException e){
            errorCode = -1;
            ExternalAlgorithms.debugPrint(e);
        }

        return errorCode;
    }
    @Override
    public int insertUserPreference(UserPreference userPreference) {
        int errorCode = 0;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(preference_insert_STMT, PreparedStatement.RETURN_GENERATED_KEYS)) {

                errorCode |= setStringsPreference(st,userPreference,false);


                ExternalAlgorithms.debugPrintSelect("insertUserPreferene \n" + st.toString());

                st.executeUpdate();
                ResultSet res = st.getGeneratedKeys();
                if (res.next()) {
                    userPreference.setUserPreferenceID(res.getInt(1));
                } else {
                    errorCode = -1;
                }

            }
        } catch (SQLException e) {
            errorCode = -1;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;

    }

    @Override
    public int updateUserPreference(UserPreference userPreference) {
        int errorCode = 0;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(preference_update_STMT)) {
                errorCode |= setStringsPreference(st, userPreference, true);

                ExternalAlgorithms.debugPrintSelect("updateUserPreference \n" + st.toString());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = -1;
            ExternalAlgorithms.debugPrint(e);

        }
        return errorCode;
    }

    private User getUser(ResultSet res) {
        User ret = new User();

        try {
            ret.setUserID(res.getInt("Users.userID"));
            ret.setPassword(res.getString("Users.password"));
            ret.setEmail(res.getString("Users.email"));
            ret.setFirstName(res.getString("Users.firstName"));
            ret.setLastName(res.getString("Users.lastName"));
            ret.setPhoneNumber(res.getString("Users.phoneNumber"));
            ret.setGender(Gender.valueOf(res.getString("Users.gender")));
            ret.setRating(res.getDouble("Users.rating"));
            ret.setFacebookID(res.getString("Users.facebookID"));
            ret.setGoogleID(res.getString("Users.googleID"));

            int userPreferenceID = res.getInt("Users.userPreferenceID");

            UserPreference up = getUserPreferenceByID(userPreferenceID);
            ret.setPreference(up);

        } catch (SQLException e) {
            ret = null;
            ExternalAlgorithms.debugPrint(e);
        }


        return ret;
    }

    @Override
    public User getUserByID(int userID) {
        User user;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(userByID_STMT)) {
                st.setInt(1, userID);

                ExternalAlgorithms.debugPrintSelect("getUserByID \n" + st.toString());


                ResultSet res = st.executeQuery();
                if (res.next())
                    user = getUser(res);
                else
                    user = null;
            }
        } catch (SQLException e) {
            user = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return user;
    }

    @Override
    public List<User> getUsersByPreferences(Driver driver) {
        List<User> output = new ArrayList<>();
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(preferences_STMT)) {

                st.setDouble(1, driver.getRating());
                st.setBoolean(2, driver.getCar().hasConditioning());
                st.setInt(3, driver.getCar().getCarYear());
                st.setInt(4, driver.getCar().getNumPassengers());
                st.setDouble(5, driver.getPreferences().getMinimumUserRating());

                ExternalAlgorithms.debugPrintSelect("getUserByPreferences \n" + st.toString());
                ResultSet res = st.executeQuery();
                while (res.next())
                    output.add(getUser(res));
            }
        } catch (SQLException e) {
            output = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return output;

    }


    @Override
    public User loginUser(String email, String password) {
        User user;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(login_STMT)) {
                st.setString(1, email);
                st.setString(2, HashGenerator.getSaltHash(password));


                ExternalAlgorithms.debugPrintSelect("loginUser \n" + st.toString());



                ResultSet res = st.executeQuery();
                if (res.next())
                    user = getUser(res);
                else
                    user = null;
            }
        } catch (SQLException e) {
            user = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return user;
    }

    /*
    password,email,firstName,lastName,phoneNumber,gender,rating,facebookID,googleID,userPreferenceID
    sets string with that order
     */
    private int setStrings(PreparedStatement st, User user, boolean update) {
        int errorCode = 0;
        try {
            st.setString(1, user.getPassword());
            st.setString(2, user.getEmail());
            st.setString(3, user.getFirstName());
            st.setString(4, user.getLastName());
            st.setString(5, user.getPhoneNumber());
            st.setString(6, user.getGender().name());
            st.setDouble(7, user.getRating());
            st.setString(8, user.getFacebookID());
            st.setString(9, user.getGoogleID());
            st.setInt(10, user.getPreference().getUserPreferenceID());
            st.setBoolean(11,user.isVerified());
            if (update)
                st.setInt(12, user.getUserID());

        } catch (SQLException e) {
            errorCode = -1;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public int registerUser(User user) {
        int errorCode = 0;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(register_STMT, PreparedStatement.RETURN_GENERATED_KEYS)) {

                errorCode |= setStrings(st, user, false);

                ExternalAlgorithms.debugPrintSelect("registerUser \n" + st.toString());

                st.executeUpdate();
                ResultSet res = st.getGeneratedKeys();
                if (res.next()) {
                    user.setUserID(res.getInt("userID"));
                } else {
                    errorCode = -1;
                }

            }
        } catch (SQLException e) {
            errorCode = -1;
            ExternalAlgorithms.debugPrint(e);

        }
        return errorCode;

    }

    @Override
    public int updateUser(User user) {
        int errorCode = 0;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(update_STMT)) {
                errorCode |= setStrings(st, user, true);

                ExternalAlgorithms.debugPrintSelect("updateUser \n" + st.toString());

                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = -1;
            ExternalAlgorithms.debugPrint(e);

        }
        return errorCode;
    }

    @Override
    public boolean checkEmail(String email) {
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(checkMail_STM)) {
                st.setString(1, email);

                ExternalAlgorithms.debugPrintSelect("checkEmail User \n" + st.toString());

                ResultSet res = st.executeQuery();
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
            try (PreparedStatement st = con.prepareStatement(checkPhoneNumber_STM)) {
                st.setString(1, phoneNumber);

                ExternalAlgorithms.debugPrintSelect("checkPhoneNumber User \n" + st.toString());

                ResultSet res = st.executeQuery();
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
                try (PreparedStatement st = con.prepareStatement(checkFacebook_STM)) {
                    st.setString(1, facebookID);

                    ExternalAlgorithms.debugPrintSelect("checkFacebookID User \n" + st.toString());

                    ResultSet res = st.executeQuery();
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
                try (PreparedStatement st = con.prepareStatement(checkGoogle_STM)) {
                    st.setString(1, googleID);

                    ExternalAlgorithms.debugPrintSelect("checkGoogleID user \n" + st.toString());

                    ResultSet res = st.executeQuery();
                    return res.next();
                }
            } catch (SQLException e) {

                ExternalAlgorithms.debugPrint(e);
            }
        return false;
    }
}
