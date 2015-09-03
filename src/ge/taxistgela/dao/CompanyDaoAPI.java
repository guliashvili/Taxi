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

package ge.taxistgela.dao;

import ge.taxistgela.bean.Company;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public interface CompanyDaoAPI {

    List<Company> getAllCompanies();

    Company getCompanyByID(int userID);

    Company getCompanyByEmail(String email);

    Company getCompanyByPhoneNumber(String phoneNumber);

    Company getCompanyByGoogleID(String googleID);

    Company getCompanyByFacebookID(String facebookID);

    Double getCompanyScore(Integer companyID);

    Integer getCompanyIDByCode(String companyCode);
    /**
     * verifies company email
     *
     * @param email
     * @return true if error
     */
    boolean verifyCompanyEmail(String email);

    /**
     * verifies company phoneNumber with the same phoneNumber
     *
     * @param phoneNumber
     * @return true if error
     */
    boolean verifyCompanyPhoneNumber(String phoneNumber);

    /**
     * Tries to login with the certain email and password.
     * Returns null if no company exists.
     *
     * @param email
     * @param password
     * @return Logged in user/or null if no such company exists
     */
    Company loginCompany(String email, String password);

    /**
     * Registers the certain oompany.
     * Returns operation result.
     *
     * @param company
     * @return operationCode
     */
    boolean registerCompany(Company company);

    /**
     * Updates the certain company with the new data.
     * will not change password
     *
     * @param company
     * @return operationCode
     */
    boolean updateCompany(Company company);

    /**
     * changes password to whats written in company(it should be in plain text not  hash)
     *
     * @param company
     * @return operationCode
     */
    boolean changePassword(Company company);

    /**
     * Checks if the company exists with the certain email.
     *
     * @param email
     * @return true/false
     */
    boolean checkEmail(String email);

    /**
     * Checks if the company exists with the certain phoneNumber.
     *
     * @param phoneNumber
     * @return true/false
     */
    boolean checkPhoneNumber(String phoneNumber);

    /**
     * Check if the company exists with the certain companyCode.
     *
     * @param companyCode
     * @return true/false
     */
    boolean checkCompanyCode(String companyCode);

    /**
     * Check if the company exists with the certain facebookID. null if he has not linked to profile.
     *
     * @param facebookID
     * @return true/false
     */
    boolean checkFacebookID(String facebookID);


    /**
     * Check if the company exists with the certain googleID. null if he has not linked to profile.
     *
     * @param googleID
     * @return true/false
     */
    boolean checkGoogleID(String googleID);
}
