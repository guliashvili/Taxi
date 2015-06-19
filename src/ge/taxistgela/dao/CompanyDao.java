package ge.taxistgela.dao;

import ge.taxistgela.bean.Company;
import ge.taxistgela.db.DBConnectionProvider;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.helper.HashGenerator;
import ge.taxistgela.helper.PreparedStatementEnhanced;
import ge.taxistgela.helper.ResultSetEnhanced;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Alex on 5/25/2015.
 */
public class CompanyDao implements CompanyDaoAPI {
    private final static String base_select_STMT = " SELECT * FROM Companies ";
    private final static String login_STMT = base_select_STMT + "  WHERE email=? AND password=? ";
    private final static String register_STMT = "INSERT INTO Companies (companyCode,email,password,companyName,phoneNumber,facebookID,googleID,isVerifiedEmail,isVerifiedPhone) VALUES(?,?,?,?,?,?,?,?,?)";
    private final static String update_STMT = "UPDATE Companies SET " +
            "companyCode=?,email=?,companyName=?,phoneNumber=?,facebookID=?,googleID=?,isVerifiedEmail=?,isVerifiedPhone=? " +
            "WHERE companyID = ?";

    @Override
    public Company loginCompany(String email, String password) {
        password = HashGenerator.getSaltHash(password);
        Company ret;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(login_STMT))) {

                st.setString(1, email);
                st.setString(2, password);


                ExternalAlgorithms.debugPrintSelect("Login Company \n" + st.toString());

                ResultSetEnhanced res = st.executeQuery();
                if (!res.next()) {
                    return null;
                }
                ret = new Company(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getBoolean(9), res.getBoolean(10));
            }
        } catch (SQLException e) {
            ret = null;
            ExternalAlgorithms.debugPrint(e);

        }
        return ret;
    }

    /*
    companyCode,email,password,companyName,phoneNumber,facebookID,googleID
    sets strings with that order
     */
    private boolean setStrings(PreparedStatementEnhanced st, Company company, boolean update) {
        boolean errorCode = false;
        try {
            int x = 1;
            st.setString(x++, company.getCompanyCode());
            st.setString(x++, company.getEmail());
            if (!update) st.setString(x++, HashGenerator.getSaltHash(company.getPassword()));
            st.setString(x++, company.getCompanyName());
            st.setString(x++, company.getPhoneNumber());
            st.setString(x++, company.getFacebookID());
            st.setString(x++, company.getGoogleID());
            st.setBoolean(x++, company.getIsVerifiedEmail());
            st.setBoolean(x++, company.getIsVerifiedPhone());

            if (update)
                st.setInt(x++, company.getCompanyID());
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public boolean changePassword(Company company) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("" +
                    "UPDATE Companies SET password=? WHERE companyID=?"))) {

                st.setString(1, HashGenerator.getSaltHash(company.getPassword()));
                st.setInt(2, company.getCompanyID());


                ExternalAlgorithms.debugPrintSelect("Update Company password\n" + st.toString());

                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public boolean registerCompany(Company company) {
        boolean errorCode = false;

        try (Connection con = DBConnectionProvider.getConnection()) {

            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(register_STMT, Statement.RETURN_GENERATED_KEYS))) {

                errorCode |= setStrings(st, company, false);

                ExternalAlgorithms.debugPrintSelect("Register Company\n" + st.toString());

                st.executeUpdate();
                ResultSetEnhanced res = st.getGeneratedKeys();
                if (!res.next()) {
                    errorCode = true;
                } else {
                    company.setCompanyID(res.getInt(1));
                }
            }
        } catch (SQLException e) {
            ExternalAlgorithms.debugPrint(e);
            errorCode = true;
        }
        return errorCode;
    }

    @Override
    public boolean updateCompany(Company company) {
        boolean errorCode = false;
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement(update_STMT))) {

                errorCode |= setStrings(st, company, true);


                ExternalAlgorithms.debugPrintSelect("Update Company\n" + st.toString());

                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public boolean checkEmail(String email) {
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT companyID FROM companies WHERE  email = ?"))) {

                st.setString(1, email);

                ExternalAlgorithms.debugPrintSelect("check Email Company\n" + st.toString());

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
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT companyID FROM companies WHERE  phoneNumber = ?"))) {

                st.setString(1, phoneNumber);

                ExternalAlgorithms.debugPrintSelect("Check Phone Number Company\n" + st.toString());

                ResultSetEnhanced res = st.executeQuery();
                return res.next();
            }
        } catch (SQLException e) {
            ExternalAlgorithms.debugPrint(e);
        }
        return false;
    }

    @Override
    public boolean checkCompanyCode(String companyCode) {
        try (Connection con = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT companyID FROM companies WHERE  companyCode = ?"))) {

                st.setString(1, companyCode);

                ExternalAlgorithms.debugPrintSelect("check company code \n" + st.toString());

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
                try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT companyID FROM companies WHERE  facebookID = ?"))) {

                    st.setString(1, facebookID);

                    ExternalAlgorithms.debugPrintSelect("check FacebookID company\n" + st.toString());


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
                try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(con.prepareStatement("SELECT companyID FROM companies WHERE  googleID = ?"))) {

                    st.setString(1, googleID);

                    ExternalAlgorithms.debugPrintSelect("Check GoogleID company\n" + st.toString());
                    ResultSetEnhanced res = st.executeQuery();
                    return res.next();
                }
            } catch (SQLException e) {

                ExternalAlgorithms.debugPrint(e);
            }
        return false;
    }
}
