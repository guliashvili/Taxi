package ge.taxistgela.model;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.ErrorCode;
import ge.taxistgela.dao.CompanyDaoAPI;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class CompanyManagerAPI {
    protected CompanyDaoAPI companyDao;
    public  CompanyManagerAPI(CompanyDaoAPI companyDao){this.companyDao = companyDao;}


    /**
     * Tries to login with the certain email and password.
     * Returns null if no company exists.
     *
     * @param email
     * @param password
     * @return Loggedin user/or null if no such company exists
     */
    public abstract Company loginCompany(String email, String password);

    /**
     * Registers the certain oompany.
     * Returns operation result.
     *
     * @param company
     * @return operationCode
     */
    public abstract ErrorCode registerCompany(Company company);

    /**
     * Updates the certain company with the new data.
     * Will not change password
     * @param company
     * @return operationCode
     */
    public abstract ErrorCode updateCompany(Company company);

    /**
     * changes password to whats written in company(it should be in plain text not  hash)
     *
     * @param company
     * @return operationCode
     */
    public abstract ErrorCode changePassword(Company company);

    /**
     * verifies company email with the same token
     * errorCodes : alreadyVerified ; wrongToken
     *
     * @param token
     * @return operationCode
     */
    public abstract ErrorCode verifyCompanyEmail(String token);

    /**
     * verifies company phoneNumber with the same token
     * errorCodes : alreadyVerified ; wrongToken
     *
     * @param token
     * @return operationCode
     */
    public abstract ErrorCode verifyCompanyPhoneNumber(String token);


    /**
     * Checks if the company exists with the certain email.
     *
     * @param email
     * @return true/false
     */
    public abstract boolean checkEmail(String email);

    /**
     * Checks if the company exists with the certain phoneNumber.
     *
     * @param phoneNumber
     * @return true/false
     */
    public abstract boolean checkPhoneNumber(String phoneNumber);

    /**
     * Check if the company exists with the certain companyCode.
     *
     * @param companyCode
     * @return true/false
     */
    public abstract boolean checkCompanyCode(String companyCode);

    /**
     * Check if the company exists with the certain facebookID. null if he has not linked to profile.
     *
     * @param facebookID
     * @return true/false
     */
    public abstract boolean checkFacebookID(String facebookID);


    /**
     * Check if the company exists with the certain googleID. null if he has not linked to profile.
     *
     * @param googleID
     * @return true/false
     */
    public abstract boolean checkGoogleID(String googleID);

}