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

    public synchronized String getPhoneNumber() {
        return phoneNumber;
    }

    public synchronized void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public synchronized Boolean getIsVerifiedEmail() {
        return isVerifiedEmail;
    }

    public synchronized void setIsVerifiedEmail(Boolean isVerifiedEmail) {
        this.isVerifiedEmail = isVerifiedEmail;
    }

    public synchronized Boolean getIsVerifiedPhone() {
        return isVerifiedPhone;
    }

    public synchronized void setIsVerifiedPhone(Boolean isVerifiedPhone) {
        this.isVerifiedPhone = isVerifiedPhone;
    }

    public synchronized String getFacebookID() {
        return facebookID;
    }

    public synchronized void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public synchronized String getGoogleID() {
        return googleID;
    }

    public synchronized void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public synchronized String getEmail() {
        return email;
    }

    public synchronized void setEmail(String email) {
        this.email = email;
    }

    public synchronized String getPassword() {
        return password;
    }

    public synchronized void setPassword(String password) {
        this.password = password;
    }


    public synchronized String getPhoneNumberToken() {
        return HashGenerator.encryptAES(getPhoneNumber());
    }

    public synchronized String getEmailToken() {
        return HashGenerator.encryptAES(getEmail());
    }


    public synchronized ErrorCode isValid() {
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
    private synchronized ErrorCode isValidPhoneNumber(String phoneNumber) {
        ErrorCode ret = new ErrorCode();
        if (phoneNumber == null) ret.phoneNumberFormat();
        else {
            phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
            if (phoneNumber.length() != 9 || phoneNumber.charAt(0) != '5')
                ret.passwordFormat();
        }
        return ret;
    }

    private synchronized ErrorCode isValidEmail(String email) {
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

    private synchronized ErrorCode isValidPassword(String password) {
        ErrorCode ret = new ErrorCode();
        if (password == null || password.length() > 50)
            ret.passwordFormat();
        return ret;
    }

    public synchronized boolean isVerified() {
        return getIsVerifiedEmail() && getIsVerifiedPhone();
    }
}
