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

package ge.taxistgela.model;

import ge.taxistgela.bean.ErrorCode;
import ge.taxistgela.bean.SuperDaoUser;


/**
 * Created by GIO on 6/25/2015.
 */
public interface SuperUserManager {


    /**
     * Checks if the SuperUserManager exists with the certain email.
     *
     * @param email
     * @return true/false
     */
    boolean checkEmail(String email);

    /**
     * Checks if the SuperUserManager exists with the certain phoneNumber.
     *
     * @param phoneNumber
     * @return true/false
     */
    boolean checkPhoneNumber(String phoneNumber);


    /**
     * Check if the SuperUserManager exists with the certain facebookID. null if he has not linked to profile.
     *
     * @param facebookID
     * @return true/false
     */
    Boolean checkFacebookID(String facebookID);


    /**
     * Check if the SuperUserManager exists with the certain googleID. null if he has not linked to profile.
     *
     * @param googleID
     * @return true/false
     */
    Boolean checkGoogleID(String googleID);

    /**
     * changes password to whats written in SuperUserManager(it should be in plain text not  hash)
     *
     * @param SuperUser
     * @return operationCode
     */
    ErrorCode changePassword(SuperDaoUser SuperUser, String oldPassword);

    /**
     * Returns SuperUserManager selected by the certain superUserID.
     *
     * @param superUserID
     * @return User generated from database.
     */
    SuperDaoUser getByID(Integer superUserID);

    SuperDaoUser getByFacebookID(String superUserFacebookID);

    SuperDaoUser getByGoogleID(String superUserGoogleID);

    SuperDaoUser getByEmail(String superUserEmail);

    SuperDaoUser getByPhoneNumber(String superUserPhoneNumber);


    /**
     * Updates the SuperUserManager order with the new data.
     * Will not change password
     *
     * @param superUser
     * @return operationCode
     */
    ErrorCode update(SuperDaoUser superUser);

    /**
     * Registers the certain SuperUserManager.
     * Returns operation result.
     *
     * @param superUser
     * @return operationCode
     */
    ErrorCode register(SuperDaoUser superUser);

    /**
     * verifies SuperUserManager email with the same token
     * errorCodes : alreadyVerified ; wrongToken
     *
     * @param token
     * @return operationCode
     */

    ErrorCode verifyEmail(String token);


    /**
     * verifies SuperUserManager phoneNumber with the same token
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
     * @return Loggedin SuperUserManager.
     */
    Object login(String email, String password);

}
