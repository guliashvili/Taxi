package ge.taxistgela.bean;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by GIO on 6/5/2015.
 */
public abstract class GeneralCheckableInformation implements Checkable {
    public abstract String getEmail();

    public abstract String getPassword();

    public abstract String getPhoneNumber();


    public boolean isValid() {
        return isValidEmail(getEmail()) &&
                isValidPassword(getPassword()) &&
                isValidPhoneNumber(getPhoneNumber());
    }

    /**
     * checks if phone is in format 5 xx xxx xxx
     *
     * @param phoneNumber
     * @return returns true if number is in format noted
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        if (phoneNumber.length() != 9) return false;
        return phoneNumber.charAt(0) == '5';
    }

    private boolean isValidEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private boolean isValidPassword(String password) {
        boolean ret = true;
        ret &= password.length() >= 1;

        return ret;
    }
}
