package ge.taxistgela.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GIO on 6/16/2015.
 */
public class ErrorCode {
    private Map<String, String> errors = new HashMap<String, String>();

    public String getErrorMessage(String varName) {
        if (errors.containsKey(varName))
            return errors.get(varName);
        else
            return null;
    }

    public void carDescriptionLong() {
        errors.put("carDescription", "Car description is very long");
    }

    public void carYearOutOfRange() {
        errors.put("carYear", "Car Year is too small or too big");
    }

    public void numPassengersOutOfRange() {
        errors.put("numPassengers", "num of passengers is too small or too big");
    }

    public void companyCodeDuplicate() {
        errors.put("companyCode", "this companyCode already exists");
    }

    public void companyCodeFormat() {
        errors.put("companyCode", "Not correct format");
    }

    public void emailFormat() {
        errors.put("email", "Not correct format");
    }

    public void emailDuplicate() {
        errors.put("email", "this email already exists");
    }

    public void passwordFormat() {
        errors.put("password", "wrong format");
    }


}
