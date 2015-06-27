package ge.taxistgela.model;

import ge.taxistgela.dao.CompanyDaoAPI;
import ge.taxistgela.ram.model.TaxRam;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class CompanyManagerAPI implements SuperUserManager {
    protected CompanyDaoAPI companyDao;
    protected TaxRam taxRam;
    public CompanyManagerAPI(CompanyDaoAPI companyDao, TaxRam taxRam) {
        this.companyDao = companyDao;
        this.taxRam = taxRam;
    }

    public abstract Double getCompanyScore(Integer companyID);

    public abstract Integer getCompanyIDByCode(String companyCode);
    /**
     * Check if the company exists with the certain companyCode.
     *
     * @param companyCode
     * @return true/false
     */
    public abstract boolean checkCompanyCode(String companyCode);


}