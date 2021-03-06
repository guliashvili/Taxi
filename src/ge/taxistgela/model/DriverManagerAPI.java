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

import ge.taxistgela.bean.*;
import ge.taxistgela.dao.DriverDaoAPI;
import ge.taxistgela.ram.model.TaxRamAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class DriverManagerAPI implements SuperUserTokenedManager {
    protected DriverDaoAPI driverDao;
    protected TaxRamAPI taxRam;

    public DriverManagerAPI(DriverDaoAPI driverDao) {
        this.driverDao = driverDao;
    }

    /**
     * Returns car selected by the certain driverPreferenceID.
     *
     * @param carID
     * @return Car generated from database.
     */
    public abstract Car getCarByID(String carID);

    public abstract ErrorCode setDriverActiveStatus(int driverID, boolean isActive);

    /**
     * Inserts the certain car.
     * Returns operation result.
     *
     * @param car
     * @return operationCode
     */
    public abstract ErrorCode insertCar(Car car);

    /**
     * Updates the Car with the new data.
     * Will not change password
     *
     * @param car
     * @return operationCode
     */
    public abstract ErrorCode updateCar(Car car);

    /**
     * Returns DriverPreference selected by the certain driverPreferenceID.
     *
     * @param driverPreferenceID
     * @return DriverPreference generated from database.
     */
    public abstract DriverPreference getDriverPreferenceByID(Integer driverPreferenceID);


    /**
     * Inserts the certain driverPreference.
     * Returns operation result.
     *
     * @param driverPreference
     * @return operationCode
     */
    public abstract ErrorCode insertDriverPreference(DriverPreference driverPreference);

    /**
     * Updates the driverPreference with the new data.
     *
     * @param driverPreference
     * @return operationCode
     */
    public abstract ErrorCode updateDriverPreference(DriverPreference driverPreference);


    /**
     * Returns Driver selected by the certain companyID.
     *
     * @param companyID
     * @return Drivers generated from database.
     */
    public abstract List<Driver> getDriverByCompanyID(Integer companyID);


    /**
     * Returns Drivers selected by the user preferences.
     * Does not consider TimeLimit
     * returns null if problem
     * Considers
     *  User.minimumDriverRating
     *  User.conditioning
     *  User.carYear
     *  User.passengersCount
     *  Driver.minimumUserRating
     *  TODO
     * @param user
     * @return List of drivers generated from database.
     */
    public abstract List<Driver> getDriverByPreferences(User user);

    /**
     * Checks if the driver exists with the certain carID.
     *
     * @param carID
     * @return true/false
     */
    public abstract boolean checkCarID(String carID);

    /**
     * Verify driver companyID.
     *
     * @param token
     * @return
     */
    public abstract ErrorCode verifyCompany(String token);

    public abstract List<Driver> getAllDrivers();
}
