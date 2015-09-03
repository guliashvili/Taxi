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

package ge.taxistgela.bean;

import com.google.gson.annotations.Expose;
import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class Company extends SuperDaoUser {
    @Expose
    private Integer companyID;
    @Expose
    private String companyCode;
    @Expose
    private String companyName;


    public Company() {
    }

    public Company(Integer companyID, String companyCode, String email, String password, String companyName, String phoneNumber, String facebookID, String googleID, Boolean isVerifiedEmail, Boolean isVerifiedPhone) {
        setCompanyID(companyID);
        setCompanyCode(companyCode);
        setEmail(email);
        setPassword(password);
        setCompanyName(companyName);
        setPhoneNumber(phoneNumber);
        setFacebookID(facebookID);
        setGoogleID(googleID);
        setIsVerifiedEmail(isVerifiedEmail);
        setIsVerifiedPhone(isVerifiedPhone);
    }

    public Company(Company company) {
        this(company.getCompanyID(), company.getCompanyCode(), company.getEmail(), company.getPassword(), company.getCompanyName(),
                company.getPhoneNumber(), company.getFacebookID(), company.getGoogleID(), company.getIsVerifiedEmail(), company.getIsVerifiedPhone());
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Company)) return  false;
        Company o = (Company)obj;
        return  ExternalAlgorithms.equalsNull(getCompanyCode(),o.getCompanyCode()) &&
                ExternalAlgorithms.equalsNull(getCompanyID() , o.getCompanyID()) &&
                ExternalAlgorithms.equalsNull(getCompanyName(), o.getCompanyName()) &&
                ExternalAlgorithms.equalsNull(getEmail(), o.getEmail()) &&
                ExternalAlgorithms.equalsNull(getFacebookID(), o.getFacebookID()) &&
                ExternalAlgorithms.equalsNull(getGoogleID(),o.getGoogleID()) &&
                ExternalAlgorithms.equalsNull(getPassword(),o.getPassword()) &&
                ExternalAlgorithms.equalsNull(getPhoneNumber(), o.getPhoneNumber()) &&
                ExternalAlgorithms.equalsNull(getIsVerifiedEmail(), o.getIsVerifiedEmail()) &&
                ExternalAlgorithms.equalsNull(getIsVerifiedPhone(), o.getIsVerifiedPhone());
    }

    @Override
    public int hashCode() {
        return getCompanyID();
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }



    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}


