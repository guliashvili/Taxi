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
                st.setString(2, HashGenerator.getHash(password));//NEEDS HASH
                ResultSet res = st.executeQuery();
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
                st.setString(3,HashGenerator.getHash(company.getPassword()));
                st.setString(4,company.getCompanyName());
                st.setString(5,company.getPhoneNumber());
                st.setString(6,company.getFacebookID());
                st.setString(7,company.getGoogleID());
                st.executeUpdate();
                ResultSet res = st.getGeneratedKeys();
                res.next();
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
                st.setString(3,HashGenerator.getHash(company.getPassword()));
                st.setString(4,company.getCompanyName());
                st.setString(5,company.getPhoneNumber());
                st.setString(6,company.getFacebookID());
                st.setString(7,company.getGoogleID());
                st.executeUpdate();
            }
        }catch(SQLException e){

        }
        return 0;
    }
}
