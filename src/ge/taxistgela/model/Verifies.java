package ge.taxistgela.model;

import ge.taxistgela.bean.ErrorCode;

/**
 * Created by GIO on 6/25/2015.
 */
public interface Verifies {
    ErrorCode verifyEmail(String token);

    ErrorCode verifyPhoneNumber(String token);


}
