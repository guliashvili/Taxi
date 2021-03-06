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

import ge.taxistgela.helper.HashGenerator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by GIO on 6/5/2015.
 */
public abstract class SuperDaoUser implements Checkable {
    private String email;
    private String password;
    private String facebookID;
    private String googleID;
    private String phoneNumber;
    private Boolean isVerifiedEmail;
    private Boolean isVerifiedPhone;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsVerifiedEmail() {
        return isVerifiedEmail;
    }

    public void setIsVerifiedEmail(Boolean isVerifiedEmail) {
        this.isVerifiedEmail = isVerifiedEmail;
    }

    public Boolean getIsVerifiedPhone() {
        return isVerifiedPhone;
    }

    public void setIsVerifiedPhone(Boolean isVerifiedPhone) {
        this.isVerifiedPhone = isVerifiedPhone;
    }

    public String getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhoneNumberToken() {
        return HashGenerator.encryptAES(getPhoneNumber());
    }

    public String getEmailToken() {
        return HashGenerator.encryptAES(getEmail());
    }


    public ErrorCode isValid() {
        ErrorCode ret = new ErrorCode();
        ret.union(isValidEmail(getEmail()));
        ret.union(isValidPassword(getPassword()));
        ret.union(isValidPhoneNumber(getPhoneNumber()));

        return ret;
    }

    /**
     * checks if phone is in format 5 xx xxx xxx
     *
     * @param phoneNumber
     * @return returns true if number is in format noted
     */
    private ErrorCode isValidPhoneNumber(String phoneNumber) {
        ErrorCode ret = new ErrorCode();
        if (phoneNumber == null) ret.phoneNumberFormat();
        else {
            phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
            if (phoneNumber.length() != 9 || phoneNumber.charAt(0) != '5')
                ret.passwordFormat();
        }
        return ret;
    }

    private ErrorCode isValidEmail(String email) {
        ErrorCode ret = new ErrorCode();
        if (email == null || email.length() > 50)
            ret.emailFormat();
        else {
            try {
                InternetAddress emailAddress = new InternetAddress(email);
                emailAddress.validate();
            } catch (AddressException ex) {
                ret.emailFormat();
            }
        }
        return ret;
    }

    private ErrorCode isValidPassword(String password) {
        ErrorCode ret = new ErrorCode();
        if (password == null || password.length() > 50)
            ret.passwordFormat();
        return ret;
    }

    public boolean isVerified() {
        return getIsVerifiedEmail() && getIsVerifiedPhone();
    }
}
