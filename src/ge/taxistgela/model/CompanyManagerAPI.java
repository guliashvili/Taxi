/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

package ge.taxistgela.model;

import ge.taxistgela.bean.Company;
import ge.taxistgela.dao.CompanyDaoAPI;
import ge.taxistgela.ram.model.TaxRamAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class CompanyManagerAPI implements SuperUserManager {
    protected CompanyDaoAPI companyDao;
    protected TaxRamAPI taxRam;

    public CompanyManagerAPI(CompanyDaoAPI companyDao) {
        this.companyDao = companyDao;
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

    public abstract List<Company> getAllCompanies();


}