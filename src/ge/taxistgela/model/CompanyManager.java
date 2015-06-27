package ge.taxistgela.model;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.ErrorCode;
import ge.taxistgela.bean.GeneralCheckableInformation;
import ge.taxistgela.dao.CompanyDaoAPI;
import ge.taxistgela.helper.HashGenerator;
import ge.taxistgela.ram.model.TaxRam;

/**
 * Created by GIO on 5/25/2015.
 */
public class CompanyManager extends   CompanyManagerAPI {
    public CompanyManager(CompanyDaoAPI companyDao, TaxRamAPI taxRam) {
        super(companyDao, taxRam);
    }

    @Override
    public Object getByFacebookID(String facebookID) {
        if (facebookID == null) {
            return null;
        }

        return companyDao.getCompanyByFacebookID(facebookID);
    }

    @Override
    public Object getByGoogleID(String googleID) {
        if (googleID == null) {
            return null;
        }

        return companyDao.getCompanyByGoogleID(googleID);
    }

    @Override
    public Object getByEmail(String email) {
        if (email == null) {
            return null;
        }

        return companyDao.getCompanyByEmail(email);
    }

    @Override
    public Object getByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }

        return companyDao.getCompanyByPhoneNumber(phoneNumber);
    }

    @Override
    public Double getCompanyScore(Integer companyID) {
        return companyDao.getCompanyScore(companyID);
    }
    @Override
    public Company login(String email, String password) {
        Company ret = null;
        if (email != null || password != null) ret = companyDao.loginCompany(email, password);
        return ret;
    }

    private ErrorCode getErrorsRegister(Company company) {
        ErrorCode ret = new ErrorCode();
        if (company == null) ret.unexpected();
        else {
            ret.union(company.isValid());
            if (checkEmail(company.getEmail())) ret.emailDuplicate();
            if (checkCompanyCode(company.getCompanyCode())) ret.companyCodeDuplicate();
            if (checkPhoneNumber(company.getPhoneNumber())) ret.phoneNumberDuplicate();
            if (company.getFacebookID() != null)
                if (checkFacebookID(company.getFacebookID())) ret.facebookIDDuplicate();
            if (company.getGoogleID() != null)
                if (checkGoogleID(company.getGoogleID())) ret.googleIDDuplicate();
        }
        return ret;
    }

    private ErrorCode getErrorsLogin(Company company) {
        ErrorCode ret = new ErrorCode();
        if (company == null) ret.unexpected();
        else {
            ret.union(company.isValid());
            if (!checkEmail(company.getEmail())) ret.emailDoesNotExists();
            if (!checkCompanyCode(company.getCompanyCode())) ret.companyCodeDoesNotExists();
            if (!checkPhoneNumber(company.getPhoneNumber())) ret.phoneNumberDoesNotExists();
            if (company.getFacebookID() != null)
                if (!checkFacebookID(company.getFacebookID())) ret.facebookIDDoesNotExists();
            if (company.getGoogleID() != null)
                if (!checkGoogleID(company.getGoogleID())) ret.googleIDDoesNotExists();
        }
        return ret;
    }

    private ErrorCode getErrorsUpdate(Company company) {
        ErrorCode ret = new ErrorCode();
        if (company == null) ret.unexpected();
        else {
            ret.union(company.isValid());

        }
        return ret;
    }
    @Override
    public Integer getCompanyIDByCode(String companyCode) {
        if (companyCode == null)
            return null;
        else
            return companyDao.getCompanyIDByCode(companyCode);
    }

    @Override
    public ErrorCode register(GeneralCheckableInformation c) {
        ErrorCode ret = new ErrorCode();
        if (c == null)
            ret.nullArgument();
        else if (!(c instanceof Company)) {
            ret.wrongType();
        } else {
            Company company = (Company) c;
            ret.union(getErrorsRegister(company));

            if (!ret.errorAccrued())
                if (companyDao.registerCompany(company))
                    ret.unexpected();
        }

        return ret;
    }

    @Override
    public ErrorCode verifyEmail(String token) {
        ErrorCode ret = new ErrorCode();
        token = HashGenerator.decryptAES(token);
        if (token == null) {
            ret.nullArgument();
        } else {
            Company u = companyDao.getCompanyByEmail(token);
            if (u.getIsVerifiedEmail()) ret.setAlreadyVerified();
            else {
                if (companyDao.verifyCompanyEmail(token)) ret.unexpected();
            }
        }

        return ret;
    }

    @Override
    public ErrorCode verifyPhoneNumber(String token) {
        ErrorCode ret = new ErrorCode();
        token = HashGenerator.decryptAES(token);
        if (token == null) {
            ret.nullArgument();
        } else {
            Company u = companyDao.getCompanyByPhoneNumber(token);
            if (u.getIsVerifiedPhone()) ret.setAlreadyVerified();
            else {
                if (companyDao.verifyCompanyPhoneNumber(token)) ret.unexpected();
            }
        }

        return ret;
    }

    @Override
    public ErrorCode changePassword(GeneralCheckableInformation c, String oldPassword) {
        ErrorCode ret = new ErrorCode();
        if (c == null)
            ret.nullArgument();
        else if (!(c instanceof Company)) {
            ret.wrongType();
        } else {
            Company company = (Company) c;
            ret.union(getErrorsLogin(company));

            if (companyDao.loginCompany(c.getEmail(), oldPassword) == null)
                ret.wrongPassword();
            else if (!ret.errorAccrued())
                if (companyDao.changePassword(company))
                    ret.unexpected();
        }

        return ret;
    }

    @Override
    public ErrorCode update(GeneralCheckableInformation c) {
        ErrorCode ret = new ErrorCode();
        if (c == null)
            ret.nullArgument();
        else if (!(c instanceof Company)) {
            ret.wrongType();
        } else {
            Company company = (Company) c;
            ret.union(getErrorsUpdate(company));

            if (!ret.errorAccrued())
                if (companyDao.updateCompany(company))
                    ret.unexpected();
        }


        return ret;
    }

    @Override
    public Object getByID(Integer superUserID) {
        Object ret = null;
        if (superUserID != null)
            ret = companyDao.getCompanyByID(superUserID);
        return ret;
    }

    @Override
    public boolean checkEmail(String email) {
        if (email == null) return false;
        else return companyDao.checkEmail(email);
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        else return companyDao.checkPhoneNumber(phoneNumber);
    }

    @Override
    public boolean checkCompanyCode(String companyCode) {
        if (companyCode == null) return false;
        else return companyDao.checkCompanyCode(companyCode);
    }

    @Override
    public Boolean checkFacebookID(String facebookID) {
        if (facebookID == null) return null;
        else return companyDao.checkFacebookID(facebookID);
    }

    @Override
    public Boolean checkGoogleID(String googleID) {
        if (googleID == null) return null;
        else return companyDao.checkGoogleID(googleID);
    }
}
