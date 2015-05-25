package ge.taxistgela.model;

import ge.taxistgela.dao.CompanyDaoAPI;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class CompanyManagerAPI {
     private CompanyDaoAPI companyDao;
     public  CompanyManagerAPI(CompanyDaoAPI companyDao){
         this.companyDao = companyDao;
     }

}
