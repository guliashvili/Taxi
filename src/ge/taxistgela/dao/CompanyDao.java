package ge.taxistgela.dao;

import ge.taxistgela.bean.Company;
import ge.taxistgela.db.DBConnectionProvider;
import ge.taxistgela.helper.HashGenerator;
import ge.taxistgela.helper.ExternalAlgorithms;

import java.sql.*;

/**
 * Created by Alex on 5/25/2015.
 */
public class CompanyDao implements CompanyDaoAPI, OperationCodes {
    private final static  String base_select_STMT = " SELECT * FROM Companies ";
    private final static String login_STMT = base_select_STMT + "  WHERE email=? AND password=? ";
    private final static String register_STMT="INSERT INTO Companies (companyCode,email,password,companyName,phoneNumber,facebookID,googleID,isVerified) VALUES(?,?,?,?,?,?,?,?)";
    private final static String update_STMT="UPDATE Companies SET " +
            "companyCode=?,email=?,password=?,companyName=?,phoneNumber=?,facebookID=?,googleID=?,isVerified=? " +
            "WHERE companyID = ?";
    @Override
    public Company loginCompany(String email, String password) {
        password = HashGenerator.getSaltHash(password);
        Company ret;
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement(login_STMT)) {
                st.setString(1,email);
                st.setString(2, password);


                ExternalAlgorithms.debugPrintSelect("Login Company \n" + st.toString());

                ResultSet res = st.executeQuery();
                if(!res.next()){
                    return null;
                }
                ret = new Company(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getBoolean(9));
            }
        }catch(SQLException e){
            ret = null;
            ExternalAlgorithms.debugPrint(e);

        }
        return ret;
    }

    /*
    companyCode,email,password,companyName,phoneNumber,facebookID,googleID
    sets strings with that order
     */
    private int setStrings(PreparedStatement st, Company company,boolean update){
        int errorCode = 0;
        try {
            st.setString(1, company.getCompanyCode());
            st.setString(2, company.getEmail());
            st.setString(3, HashGenerator.getSaltHash(company.getPassword()));
            st.setString(4, company.getCompanyName());
            st.setString(5, company.getPhoneNumber());
            st.setString(6, company.getFacebookID());
            st.setString(7, company.getGoogleID());
            st.setBoolean(8,company.isVerified());

            if(update)
                st.setInt(9,company.getCompanyID());
        }catch (SQLException e){
            errorCode = -1;
            ExternalAlgorithms.debugPrint(e);
        }
        return  errorCode;
    }

    @Override
    public int registerCompany(Company company) {
        int errorCode = 0;

        try(Connection con = DBConnectionProvider.getConnection()){

            try(PreparedStatement st = con.prepareStatement(register_STMT,Statement.RETURN_GENERATED_KEYS)) {

                errorCode |= setStrings(st, company,false);

                ExternalAlgorithms.debugPrintSelect("Register Company\n" + st.toString());

                st.executeUpdate();
                ResultSet res = st.getGeneratedKeys();
                if(!res.next()){
                    errorCode = -1;
                }else {
                    company.setCompanyID(res.getInt(1));
                }
            }
        }catch(SQLException e){

            ExternalAlgorithms.debugPrint(e);
            errorCode = -1;
        }
        return errorCode;
    }

    @Override
    public int updateCompany(Company company) {
        int errorCode = 0;
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement(update_STMT)) {
                errorCode |= setStrings(st, company,true);


                ExternalAlgorithms.debugPrintSelect("Update Company\n" + st.toString());

                st.executeUpdate();
            }
        }catch(SQLException e){
            errorCode = -1;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public boolean checkEmail(String email) {
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement("SELECT companyID FROM companies WHERE  email = ?")) {
                st.setString(1,email);

                ExternalAlgorithms.debugPrintSelect("check Email Company\n" + st.toString());

                ResultSet res = st.executeQuery();
                return res.next();
            }
        }catch(SQLException e){
            ExternalAlgorithms.debugPrint(e);
        }
        return false;
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement("SELECT companyID FROM companies WHERE  phoneNumber = ?")) {
                st.setString(1,phoneNumber);

                ExternalAlgorithms.debugPrintSelect("Check Phone Number Company\n" + st.toString());

                ResultSet res = st.executeQuery();
                return res.next();
            }
        }catch(SQLException e){
            ExternalAlgorithms.debugPrint(e);
        }
        return false;
    }

    @Override
    public boolean checkCompanyCode(String companyCode) {
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement("SELECT companyID FROM companies WHERE  companyCode = ?")) {
                st.setString(1,companyCode);

                ExternalAlgorithms.debugPrintSelect("check company code \n" + st.toString());

                ResultSet res = st.executeQuery();
                return res.next();
            }
        }catch(SQLException e){
            ExternalAlgorithms.debugPrint(e);
        }
        return false;
    }

    @Override
    public boolean checkFacebookID(String facebookID) {
        if(facebookID != null)
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement("SELECT companyID FROM companies WHERE  facebookID = ?")) {
                st.setString(1,facebookID);

                ExternalAlgorithms.debugPrintSelect("check FacebookID company\n" + st.toString());


                ResultSet res = st.executeQuery();
                return res.next();
            }
        }catch(SQLException e){

            ExternalAlgorithms.debugPrint(e);
        }
        return false;
    }

    @Override
    public boolean checkGoogleID(String googleID) {
        if(googleID != null)
        try(Connection con = DBConnectionProvider.getConnection()){
            try(PreparedStatement st = con.prepareStatement("SELECT companyID FROM companies WHERE  googleID = ?")) {
                st.setString(1,googleID);

                ExternalAlgorithms.debugPrintSelect("Check GoogleID company\n" +  st.toString());
                ResultSet res = st.executeQuery();
                return res.next();
            }
        }catch(SQLException e){

            ExternalAlgorithms.debugPrint(e);
        }
        return false;
    }
}
