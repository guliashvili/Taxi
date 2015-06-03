package ge.taxistgela.model;

import ge.taxistgela.bean.Company;
import ge.taxistgela.dao.CompanyDaoAPI;
import ge.taxistgela.helper.ExternalAlgorithms;

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
        int ret;
        if(!ExternalAlgorithms.isValidEmail(company.getEmail()))
            ret = -1;
        else
            ret = companyDao.registerCompany(company);
        return  ret;
    }

    @Override
    public int updateCompany(Company company) {
        return companyDao.updateCompany(company);
    }

    @Override
    public boolean checkEmail(String email) {
        return companyDao.checkEmail(email);
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        return companyDao.checkPhoneNumber(phoneNumber);
    }

    @Override
    public boolean checkCompanyCode(String companyCode) {
        return checkCompanyCode(companyCode);
    }

    @Override
    public boolean checkFacebookID(String facebookID) {
        return checkFacebookID(facebookID);
    }

    @Override
    public boolean checkGoogleID(String googleID) {
        return checkGoogleID(googleID);
    }
}
