package ge.taxistgela.model;

import ge.taxistgela.bean.Company;
import ge.taxistgela.dao.CompanyDaoAPI;

/**
 * Created by GIO on 5/25/2015.
 */
public class CompanyManager extends   CompanyManagerAPI {
    public  CompanyManager(CompanyDaoAPI companyDao){super(companyDao);}

    @Override
    public Company loginCompany(String email, String password) {
        return  companyDao.loginCompany(email, password);
    }

    @Override
    public int registerCompany(Company company) {
        return  companyDao.registerCompany(company);
    }

    @Override
    public int updateCompany(Company company) {
        return companyDao.updateCompany(company);
    }
}
