package ge.taxistgela.bean;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GIO on 6/16/2015.
 */
public class ErrorCode {
    private Map<String, String> errors = new HashMap<String, String>();

    public ErrorCode() {

    }

    public ErrorCode(ErrorCode errorCode) {
        errors = new HashMap<>(errorCode.errors);
    }

    public void union(ErrorCode a) {
        for (String elem : a.errors.keySet()) {
            errors.put(elem, a.errors.get(elem));
        }
    }

    public String getErrorMessage(String varName) {
        varName = varName.toLowerCase();
        if (errors.containsKey(varName))
            return errors.get(varName);
        else
            return null;
    }

    public String toJson() {
        return new Gson().toJson(errors);
    }

    public boolean errorNotAccrued() {
        return !errorAccrued();
    }

    public boolean errorAccrued() {
        return errors.size() > 0;
    }

    public void put(String varName, String info) {
        varName = varName.toLowerCase();
        errors.put(varName, info);
    }

    public void nullArgument() {
        put("nullArgument", "Some argument is null");
    }

    public void wrongType() {
        put("wrongType", "Find out phone and fuck us coz we have too shitty error in code");
    }
    public void unexpected() {
        put("unexpected", "unexpected error accrued");
    }

    public void carDescriptionLong() {
        put("carDescription", "Car description is very long");
    }

    public void wrongCaptcha() {
        put("wrongCaptcha", "user did wrong capthca");
    }
    public void carYearOutOfRange() {
        put("carYear", "Car Year is too small or too big");
    }

    public void numPassengersOutOfRange() {
        put("numPassengers", "num of passengers is too small or too big");
    }

    public void companyCodeDuplicate() {
        put("companyCode", "this companyCode already exists");
    }

    public void companyCodeDoesNotExists() {
        put("companyCode", "company code does not exists");
    }

    public void companyCodeFormat() {
        put("companyCode", "Not correct format");
    }

    public void emailFormat() {
        put("email", "Not correct format");
    }

    public void emailDuplicate() {
        put("email", "this email already exists");
    }

    public void emailDoesNotExists() {
        put("email", "email does not exists");
    }
    public void passwordFormat() {
        put("password", "wrong format. password length is more then 50 or null");
    }

    public void wrongPassword() {
        put("password", "Old password is wrong");
    }

    public void companyNameLong() {
        put("companyName", "Company name is too long");
    }

    public void phoneNumberFormat() {
        put("phoneNumber", "Not correct format");
    }

    public void phoneNumberDuplicate() {
        put("phoneNumber", "Phone number already exists");
    }

    public void phoneNumberDoesNotExists() {
        put("phoneNumber", "Phone number does not exists");
    }
    public void firstNameLong() {
        put("firstName", "first name is long");
    }

    public void lastNameLong() {
        put("lastName", "last name is long");
    }

    public void descriptionLong() {
        put("description", "description is long");
    }

    public void facebookIDDuplicate() {
        put("facebookID", "this facebook account already exists");
    }

    public void facebookIDDoesNotExists() {
        put("facebookID", "this facebook account does not exists");
    }

    public void googleIDDuplicate() {
        put("googleID", "this google account already exists");
    }

    public void googleIDDoesNotExists() {
        put("googleID", "this google account does not exists");
    }

    public void setWrongToken() {
        put("wrongToken", "This link is broken");
    }

    public void setAlreadyVerified() {
        put("alreadyVerified", "User is already verified");
    }


}
