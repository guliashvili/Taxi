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

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.ErrorCode;
import ge.taxistgela.bean.User;
import ge.taxistgela.bean.UserPreference;
import ge.taxistgela.dao.UserDaoAPI;
import ge.taxistgela.ram.model.TaxRamAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class UserManagerAPI implements SuperUserTokenedManager {
    protected UserDaoAPI userDao;
    protected TaxRamAPI taxRam;

    public UserManagerAPI(UserDaoAPI userDao) {
        this.userDao = userDao;
    }

    /**
     * Returns UserPreference selected by the certain userPreferenceID.
     *
     * @param userPreferenceID
     * @return UserPreference generated from database.
     */
    public abstract UserPreference getUserPreferenceByID(Integer userPreferenceID);


    /**
     * Inserts the certain UserPreference.
     * Returns operation result.
     *
     * @param userPreference
     * @return operationCode
     */
    public abstract ErrorCode insertUserPreference(UserPreference userPreference);

    /**
     * Updates the userPreference with the new data.
     *
     * @param userPreference
     * @return operationCode
     */
    public abstract ErrorCode updateUserPreference(UserPreference userPreference);


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
    public abstract List<User> getUsersByPreferences(Driver driver);

    public abstract List<User> getAllUsers();
}
