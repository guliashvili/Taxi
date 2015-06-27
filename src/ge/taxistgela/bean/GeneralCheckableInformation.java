package ge.taxistgela.bean;

import ge.taxistgela.helper.HashGenerator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by GIO on 6/5/2015.
 */
public abstract class GeneralCheckableInformation implements Checkable {
    public abstract String getEmail();

    public abstract void setEmail(String email);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract String getPhoneNumber();

    public abstract void setPhoneNumber(String phoneNumber);

    public String getPhoneNumberToken() {
        return HashGenerator.encryptAES(getPhoneNumber());
    }

    public String getEmailToken() {
        return HashGenerator.encryptAES(getEmail());
    }

    public abstract Boolean getIsVerifiedEmail();

    public abstract void setIsVerifiedEmail(Boolean isVerifiedEmail);

    public abstract Boolean getIsVerifiedPhone();

    public abstract void setIsVerifiedPhone(Boolean isVerifiedPhone);

    public abstract String getFacebookID();

    public abstract void setFacebookID(String facebookID);

    public abstract String getGoogleID();

    public abstract void setGoogleID(String googleID);
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
}
