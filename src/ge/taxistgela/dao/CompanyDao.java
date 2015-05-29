package ge.taxistgela.dao;

import ge.taxistgela.bean.Company;
import ge.taxistgela.db.DBConnectionProvider;
import ge.taxistgela.helper.HashGenerator;

import java.sql.*;

/**
 * Created by Alex on 5/25/2015.
 */
public class CompanyDao implements CompanyDaoAPI, OperationCodes {
    final static String login_STMT="SELECT * FROM Companies WHERE email=? AND password=?";
    final static String register_STMT="INSERT INTO Companies (companyCode,email,password,companyName,phoneNumber,facebookID,googleID) VALUES(?,?,?,?,?,?,?)";
    final static String update_STMT="UPDATE Companies SET companyCode=?,email=?,password=?,companyName=?,phoneNumber=?,facebookID=?,googleID=?";
    @Override
    public Company loginCompany(String email, String password) {
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement(login_STMT)) {
                st.setString(1,email);
                st.setString(2, HashGenerator.getSaltHash(password));
                ResultSet res = st.executeQuery();
                if(!res.next()){
                    return null;
                }
                return new Company(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8));
            }
        }catch(SQLException e){
            //NEEDS LOGGING
        }
        return null;
    }

    @Override
    public int registerCompany(Company company) {
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement(register_STMT,Statement.RETURN_GENERATED_KEYS)) {
                st.setString(1,company.getCompanyCode());
                st.setString(2,company.getEmail());
                st.setString(3,HashGenerator.getSaltHash(company.getPassword()));
                st.setString(4,company.getCompanyName());
                st.setString(5,company.getPhoneNumber());
                st.setString(6,company.getFacebookID());
                st.setString(7,company.getGoogleID());
                st.executeUpdate();
                ResultSet res = st.getGeneratedKeys();
                if(!res.next()){
                    return 0;//TODO ERRORCODE
                }
                company.setCompanyID(res.getInt(1));
            }
        }catch(SQLException e){

        }
        return 0;
    }

    @Override
    public int updateCompany(Company company) {
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement(update_STMT)) {
                st.setString(1,company.getCompanyCode());
                st.setString(2,company.getEmail());
                st.setString(3,HashGenerator.getSaltHash(company.getPassword()));
                st.setString(4,company.getCompanyName());
                st.setString(5,company.getPhoneNumber());
                st.setString(6,company.getFacebookID());
                st.setString(7,company.getGoogleID());
                st.executeUpdate();
                return 0;//TODO success code
            }
        }catch(SQLException e){

        }
        return 0;
    }

    @Override
    public boolean checkEmail(String email) {
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement("SELECT companyID FROM companies WHERE  email = ?")) {
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
            try(PreparedStatement st = con.prepareStatement("SELECT companyID FROM companies WHERE  phoneNumber = ?")) {
                st.setString(1,phoneNumber);
                ResultSet res = st.executeQuery();
                return res.next();
            }
        }catch(SQLException e){

        }
        return false;
    }

    @Override
    public boolean checkCompanyCode(String companyCode) {
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement("SELECT companyID FROM companies WHERE  companyCode = ?")) {
                st.setString(1,companyCode);
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
            try(PreparedStatement st = con.prepareStatement("SELECT companyID FROM companies WHERE  facebookID = ?")) {
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
            try(PreparedStatement st = con.prepareStatement("SELECT companyID FROM companies WHERE  googleID = ?")) {
                st.setString(1,googleID);
                ResultSet res = st.executeQuery();
                return res.next();
            }
        }catch(SQLException e){

        }
        return false;
    }
}
