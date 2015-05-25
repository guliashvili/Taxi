package ge.taxistgela.dao;

import ge.taxistgela.bean.Company;

/**
 * Created by Alex on 5/25/2015.
 */
public class CompanyDao implements CompanyDaoAPI, OperationCodes {

    @Override
    public Company loginCompany(String usernameOrEmail, String password) {
        return null;
    }

    @Override
    public int registerCompany(Company company) {
        return 0;
    }

    @Override
    public int updateCompany(Company company) {
        return 0;
    }
}
