package ge.taxistgela.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GIO on 6/16/2015.
 */
public class ErrorCode {
    private Map<String, String> errors = new HashMap<String, String>();

    public void union(ErrorCode a) {
        for (String elem : a.errors.keySet()) {
            errors.put(elem, a.errors.get(elem));
        }
    }

    public String getErrorMessage(String varName) {
        if (errors.containsKey(varName))
            return errors.get(varName);
        else
            return null;
    }

    public boolean errorNotAccrued() {
        return !errorAccrued();
    }

    public boolean errorAccrued() {
        return errors.size() > 0;
    }

    public void put(String varName, String info) {
        errors.put(varName, info);
    }

    public void unexpected() {
        put("unexpected", "unexpected error accrued");
    }

    public void carDescriptionLong() {
        put("carDescription", "Car description is very long");
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

    public void companyCodeFormat() {
        put("companyCode", "Not correct format");
    }

    public void emailFormat() {
        put("email", "Not correct format");
    }

    public void emailDuplicate() {
        put("email", "this email already exists");
    }

    public void passwordFormat() {
        put("password", "wrong format. password length is more then 50 or less then 2");
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

    public void googleIDDuplicate() {
        put("googleID", "this google account already exists");
    }




}
