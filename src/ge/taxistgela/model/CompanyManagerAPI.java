package ge.taxistgela.model;

import ge.taxistgela.bean.Company;
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
    public abstract int registerCompany(Company company);

    /**
     * Updates the certain company with the new data.
     *
     * @param company
     * @return operationCode
     */
    public abstract int updateCompany(Company company);

}