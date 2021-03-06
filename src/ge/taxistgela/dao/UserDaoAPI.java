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

package ge.taxistgela.dao;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.User;
import ge.taxistgela.bean.UserPreference;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public interface UserDaoAPI {

    /**
     * Returns UserPreference selected by the certain userPreferenceID.
     *
     * @param userPreferenceID
     * @return UserPreference generated from database.
     */
    UserPreference getUserPreferenceByID(int userPreferenceID);


    /**
     * Inserts the certain UserPreference.
     * Returns operation result.
     *
     * @param userPreference
     * @return operationCode
     */
    boolean insertUserPreference(UserPreference userPreference);

    /**
     * Updates the userPreference with the new data.
     *
     * @param userPreference
     * @return operationCode
     */
    boolean updateUserPreference(UserPreference userPreference);



    /**
     * Returns User selected by the certain userID.
     *
     * @param userID
     * @return User generated from database.
     */
    User getUserByID(int userID);

    User getUserByEmail(String email);

    User getUserByPhoneNumber(String phoneNumber);

    User getUserByGoogleID(String googleID);

    User getUserByFacebookID(String facebookID);


    /**
     * Returns Users selected by the driver  criteria.
     * Does not consider TimeLimit
     * returns null if problem
     * Considers
     *  User.minimumDriverRating
     *  User.conditioning
     *  User.carYear
     *  User.passengersCount
     *  Driver.minimumUserRating
     * @param driver
     * @return List of users generated from database.
     */
    List<User> getUsersByPreferences(Driver driver);

    /**
     * verifies user email
     *
     * @param email
     * @return true if error
     */
    boolean verifyUserEmail(String email);

    /**
     * verifies user phoneNumber with the same phoneNumber
     *
     * @param phoneNumber
     * @return true if error
     */
    boolean verifyUserPhoneNumber(String phoneNumber);

    Integer getUserIDByToken(String token);

    String getUserTokenByID(Integer userID);

    /**
     * Tries to login with the certain email and password.
     * Returns null if no user exists.
     *
     * @param email
     * @param password
     * @return Loggedin User.
     */
    User loginUser(String email, String password);

    /**
     * Registers the certain user.
     * Returns operation result.
     *
     * @param user
     * @return operationCode
     */
    boolean registerUser(User user);

    /**
     * Updates the user order with the new data.
     * will not change password
     *
     * @param user
     * @return operationCode
     */
    boolean updateUser(User user);

    /**
     * changes password to whats written in user(it should be in plain text not  hash)
     *
     * @param user
     * @return operationCode
     */
    boolean changePassword(User user);

    /**
     * Checks if the user exists with the certain email.
     *
     * @param email
     * @return true/false
     */
    boolean checkEmail(String email);

    /**
     * Checks if the user exists with the certain phoneNumber.
     *
     * @param phoneNumber
     * @return true/false
     */
    boolean checkPhoneNumber(String phoneNumber);


    boolean checkUserID(int userID);

    /**
     * Check if the user exists with the certain facebookID. null if he has not linked to profile.
     *
     * @param facebookID
     * @return true/false
     */
    boolean checkFacebookID(String facebookID);


    /**
     * Check if the user exists with the certain googleID. null if he has not linked to profile.
     *
     * @param googleID
     * @return true/false
     */
    boolean checkGoogleID(String googleID);

    List<User> getAllUsers();

}
