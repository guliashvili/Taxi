package ge.taxistgela.model;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.ErrorCode;
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

    private ErrorCode getErrors(Company company) {
        ErrorCode ret = new ErrorCode();
        if (company == null) ret.unexpected();
        else {
            ret.union(company.isValid());
            if (checkEmail(company.getEmail())) ret.emailDuplicate();
            if (checkCompanyCode(company.getCompanyCode())) ret.companyCodeDuplicate();
            if (checkPhoneNumber(company.getPhoneNumber())) ret.phoneNumberDuplicate();
            if (checkFacebookID(company.getFacebookID())) ret.facebookIDDuplicate();
            if (checkGoogleID(company.getGoogleID())) ret.googleIDDuplicate();
        }
        return ret;
    }


    @Override
    public ErrorCode registerCompany(Company company) {
        ErrorCode ret = new ErrorCode();
        ret.union(getErrors(company));

        if (!ret.errorAccrued())
            if (companyDao.registerCompany(company))
                ret.unexpected();

        return ret;
    }

    @Override
    public ErrorCode updateCompany(Company company) {
        ErrorCode ret = new ErrorCode();
        ret.union(getErrors(company));

        if (!ret.errorAccrued())
            if (companyDao.updateCompany(company))
                ret.unexpected();

        return ret;
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
        return companyDao.checkCompanyCode(companyCode);
    }

    @Override
    public boolean checkFacebookID(String facebookID) {
        return companyDao.checkFacebookID(facebookID);
    }

    @Override
    public boolean checkGoogleID(String googleID) {
        return companyDao.checkGoogleID(googleID);
    }
}
