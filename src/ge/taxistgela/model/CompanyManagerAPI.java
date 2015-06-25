package ge.taxistgela.model;

import ge.taxistgela.dao.CompanyDaoAPI;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class CompanyManagerAPI implements SuperUserManager {
    protected CompanyDaoAPI companyDao;
    public  CompanyManagerAPI(CompanyDaoAPI companyDao){this.companyDao = companyDao;}

    /**
     * Check if the company exists with the certain companyCode.
     *
     * @param companyCode
     * @return true/false
     */
    public abstract boolean checkCompanyCode(String companyCode);


}