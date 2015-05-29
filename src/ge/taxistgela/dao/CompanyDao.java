package ge.taxistgela.dao;

import ge.taxistgela.bean.Company;
import ge.taxistgela.db.DBConnectionProvider;
import ge.taxistgela.helper.HashGenerator;

import java.sql.*;

/**
 * Created by Alex on 5/25/2015.
 */
public class CompanyDao implements CompanyDaoAPI, OperationCodes {
    final static  String base_select_STMT = " SELECT * FROM Companies ";
    final static String login_STMT = base_select_STMT + "  WHERE email=? AND password=? ";
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

    /*
    companyCode,email,password,companyName,phoneNumber,facebookID,googleID
    sets strings with that order
     */
    private int setStrings(PreparedStatement st, Company company){
        int errorCode = 0;
        try {
            st.setString(1, company.getCompanyCode());
            st.setString(2, company.getEmail());
            st.setString(3, HashGenerator.getSaltHash(company.getPassword()));
            st.setString(4, company.getCompanyName());
            st.setString(5, company.getPhoneNumber());
            st.setString(6, company.getFacebookID());
            st.setString(7, company.getGoogleID());
        }catch (SQLException e){
            errorCode = -1;
        }
        return  errorCode;
    }

    @Override
    public int registerCompany(Company company) {
        int errorCode = 0;
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement(register_STMT,Statement.RETURN_GENERATED_KEYS)) {
                errorCode |= setStrings(st, company);

                st.executeUpdate();
                ResultSet res = st.getGeneratedKeys();
                if(!res.next()){
                    errorCode = -1;//TODO ERRORCODE
                }else {
                    company.setCompanyID(res.getInt(1));
                }
            }
        }catch(SQLException e){
            //TODO
            errorCode = -1;
        }
        return errorCode;
    }

    @Override
    public int updateCompany(Company company) {
        int errorCode = 0;
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement(update_STMT)) {
                errorCode |= setStrings(st, company);
                st.executeUpdate();

            }
        }catch(SQLException e){
            errorCode = -1;//TODO
        }
        return errorCode;
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
