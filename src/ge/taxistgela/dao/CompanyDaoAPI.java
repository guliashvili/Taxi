package ge.taxistgela.dao;

import ge.taxistgela.bean.Company;

/**
 * Created by Alex on 5/25/2015.
 */
public interface CompanyDaoAPI {

    /**
     * Tries to login with the certain username/email and password.
     * Returns null if no company exists.
     *
     * @param usernameOrEmail
     * @param password
     * @return Loggedin user.
     */
    public Company loginCompany(String usernameOrEmail, String password);

    /**
     * Registers the certain oompany.
     * Returns operation result.
     *
     * @param company
     * @return operationCode
     */
    public int registerCompany(Company company);

    /**
     * Updates the certain company with the new data.
     *
     * @param company
     * @return operationCode
     */
    public int updateCompany(Company company);
}
