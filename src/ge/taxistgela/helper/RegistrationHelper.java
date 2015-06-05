package ge.taxistgela.helper;

import ge.taxistgela.bean.GeneralCheckableInformation;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by GIO on 6/5/2015.
 */
public class RegistrationHelper {

    static  public  boolean isValid(GeneralCheckableInformation info){
        return  isValidEmail(info.getEmail()) &&
                isValidPassword(info.getPassword()) &&
                isValidPhoneNumber(info.getPhoneNumber());
    }
    static  public  boolean isValidEmail(String email){
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    static  public  boolean isValidPassword(String password){
        boolean ret = true;
        ret &= password.length() >= 1;

        return ret;
    }

    /**
     * checks if phone is in format 5 xx xxx xxx
     * @param phoneNumber
     * @return returns true if number is in format noted
     */
    static public  boolean isValidPhoneNumber(String phoneNumber){
        phoneNumber = phoneNumber.replaceAll("[^0-9]","");
        if(phoneNumber.length() != 9) return  false;
        if(phoneNumber.charAt(0) != '5') return  false;
        return  true;
    }

}
