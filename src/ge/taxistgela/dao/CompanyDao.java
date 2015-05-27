package ge.taxistgela.dao;

import ge.taxistgela.bean.Company;
import ge.taxistgela.db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Alex on 5/25/2015.
 */
public class CompanyDao implements CompanyDaoAPI, OperationCodes {

    @Override
    public Company loginCompany(String email, String password) {
        try(Connection con = DBConnectionProvider.getConnection()){
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM Companies WHERE email='"+email+"' AND password='"+password+"'"); // NEEDS HASHING
            res.next();
            return new Company(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8));
        }catch(SQLException e){
            //NEEDS LOGGING
        }
        return null;
    }

    @Override
    public int registerCompany(Company company) {
        try(Connection con = DBConnectionProvider.getConnection()){
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO Companies (companyCode,email,password,companyName,phoneNumber,facebookID,googleID) VALUES('"+company.getCompanyCode()
                    +"','"+company.getEmail()+"','"+company.getPassword()+"','"+company.getCompanyName()+"','"+company.getPhoneNumber()+"','"+company.getFacebookID()+"','"+company.getGoogleID()+"') ",
                    Statement.RETURN_GENERATED_KEYS);
            ResultSet res = st.getGeneratedKeys();
            res.next();
            company.setCompanyID(res.getInt(1));
        }catch(SQLException e){

        }
        return 0;
    }

    @Override
    public int updateCompany(Company company) {
        try(Connection con = DBConnectionProvider.getConnection()){
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE Companies SET companyCode='"+company. getCompanyCode()+"'" +
                    ",email='"+company.getEmail()+"',password='"+company.getPassword()+"',companyName='"+company.getCompanyName()+"'" +
                    ",phoneNumber='"+company.getPhoneNumber()+"',facebookID='"+company.getFacebookID()+"',googleID='"+company.getGoogleID()+"'");
        }catch(SQLException e){

        }
        return 0;
    }
}
