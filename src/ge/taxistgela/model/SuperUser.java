package ge.taxistgela.model;

import ge.taxistgela.bean.ErrorCode;
import ge.taxistgela.bean.GeneralCheckableInformation;


/**
 * Created by GIO on 6/25/2015.
 */
public interface SuperUser {


    /**
     * Checks if the SuperUser exists with the certain email.
     *
     * @param email
     * @return true/false
     */
    boolean checkEmail(String email);

    /**
     * Checks if the SuperUser exists with the certain phoneNumber.
     *
     * @param phoneNumber
     * @return true/false
     */
    boolean checkPhoneNumber(String phoneNumber);


    /**
     * Check if the SuperUser exists with the certain facebookID. null if he has not linked to profile.
     *
     * @param facebookID
     * @return true/false
     */
    boolean checkFacebookID(String facebookID);


    /**
     * Check if the SuperUser exists with the certain googleID. null if he has not linked to profile.
     *
     * @param googleID
     * @return true/false
     */
    boolean checkGoogleID(String googleID);

    /**
     * changes password to whats written in SuperUser(it should be in plain text not  hash)
     *
     * @param SuperUser
     * @return operationCode
     */
    ErrorCode changePassword(GeneralCheckableInformation SuperUser);

    /**
     * Returns SuperUser selected by the certain superUserID.
     *
     * @param superUserID
     * @return User generated from database.
     */
    Object getByID(Integer superUserID);


    /**
     * Updates the SuperUser order with the new data.
     * Will not change password
     *
     * @param superUser
     * @return operationCode
     */
    ErrorCode update(GeneralCheckableInformation superUser);

    /**
     * Registers the certain SuperUser.
     * Returns operation result.
     *
     * @param superUser
     * @return operationCode
     */
    ErrorCode register(GeneralCheckableInformation superUser);

    /**
     * verifies SuperUser email with the same token
     * errorCodes : alreadyVerified ; wrongToken
     *
     * @param token
     * @return operationCode
     */

    ErrorCode verifyEmail(String token);


    /**
     * verifies SuperUser phoneNumber with the same token
     * errorCodes : alreadyVerified ; wrongToken
     *
     * @param token
     * @return operationCode
     */
    ErrorCode verifyPhoneNumber(String token);


    /**
     * Tries to login with the certain email and password.
     * Returns null if does not  exists.
     *
     * @param email
     * @param password
     * @return Loggedin SuperUser.
     */
    Object login(String email, String password);


}
