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

package ge.taxistgela.bean;

import com.google.gson.annotations.Expose;
import ge.taxistgela.helper.ExternalAlgorithms;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Alex on 5/25/2015.
 */
public class Driver extends SuperPersonalTokenUser {
    @Expose
    private Integer driverID;
    @Expose
    private String personalID;
    @Expose
    private Integer companyID;
    @Expose
    private Car car;
    @Expose
    private Double rating;
    @Expose
    private DriverPreference preferences;
    @Expose
    private Boolean isActive;

    public Driver() {
        token = RandomStringUtils.randomAlphanumeric(20);
    }

    public Driver(Integer driverID, String personalID, String email, String password, Integer companyID, String firstName, String lastName, Gender gender, String phoneNumber, Car car, String facebookID, String googleID, Double rating, DriverPreference preferences, Boolean isActive, Boolean isVerifiedEmail, Boolean isVerifiedPhone) {
        this();

        setDriverID(driverID);
        setPersonalID(personalID);
        setEmail(email);
        setPassword(password);
        setCompanyID(companyID);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setCar(new Car(car));
        setFacebookID(facebookID);
        setGoogleID(googleID);
        setRating(rating);
        setPreferences(new DriverPreference(preferences));
        setIsActive(isActive);
        setIsVerifiedEmail(isVerifiedEmail);
        setIsVerifiedPhone(isVerifiedPhone);
    }

    public Driver(Driver driver) {
        this(driver.getDriverID(), driver.getPersonalID(), driver.getEmail(), driver.getPassword(), driver.getCompanyID(),
                driver.getFirstName(), driver.getLastName(), driver.getGender(), driver.getPhoneNumber(), driver.getCar(),
                driver.getFacebookID(), driver.getGoogleID(), driver.getRating(), driver.getPreferences(),
                driver.isActive(), driver.getIsVerifiedEmail(), driver.getIsVerifiedPhone());
        token = driver.token;
    }

    public void update(Integer driverID, String personalID, String email, String password, Integer companyID, String firstName, String lastName, Gender gender, String phoneNumber, Car car, String facebookID, String googleID, Double rating, DriverPreference preferences, Boolean isActive, Boolean isVerifiedEmail, Boolean isVerifiedPhone) {
        setDriverID(driverID);
        setPersonalID(personalID);
        setEmail(email);
        setPassword(password);
        setCompanyID(companyID);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setCar(new Car(car));
        setFacebookID(facebookID);
        setGoogleID(googleID);
        setRating(rating);
        setPreferences(new DriverPreference(preferences));
        setIsActive(isActive);
        setIsVerifiedEmail(isVerifiedEmail);
        setIsVerifiedPhone(isVerifiedPhone);
    }

    public void update(Driver driver) {
        if (driver == null) return;
        update(driver.getDriverID(), driver.getPersonalID(), driver.getEmail(), driver.getPassword(), driver.getCompanyID(),
                driver.getFirstName(), driver.getLastName(), driver.getGender(), driver.getPhoneNumber(), driver.getCar(),
                driver.getFacebookID(), driver.getGoogleID(), driver.getRating(), driver.getPreferences(),
                driver.isActive(), driver.getIsVerifiedEmail(), driver.getIsVerifiedPhone());
        token = driver.token;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Driver)) return false;
        Driver o = (Driver) obj;
        return ExternalAlgorithms.equalsNull(getDriverID(), o.getDriverID()) &&
                ExternalAlgorithms.equalsNull(getPersonalID(), o.getPersonalID()) &&
                ExternalAlgorithms.equalsNull(getEmail(), o.getEmail()) &&
                ExternalAlgorithms.equalsNull(getPassword(), o.getPassword()) &&
                ExternalAlgorithms.equalsNull(getCompanyID(), o.getCompanyID()) &&

                ExternalAlgorithms.equalsNull(getFirstName(), o.getFirstName()) &&
                ExternalAlgorithms.equalsNull(getLastName(), o.getLastName()) &&

                ExternalAlgorithms.equalsNull(getGender(), o.getGender()) &&

                ExternalAlgorithms.equalsNull(getPhoneNumber(), o.getPhoneNumber()) &&
                ExternalAlgorithms.equalsNull(getCar(), o.getCar()) &&

                ExternalAlgorithms.equalsNull(getFacebookID(), o.getFacebookID()) &&
                ExternalAlgorithms.equalsNull(getGoogleID(), o.getGoogleID()) &&

                ExternalAlgorithms.equalsNull(getRating(), o.getRating()) &&
                ExternalAlgorithms.equalsNull(getPreferences(), o.getPreferences()) &&
                ExternalAlgorithms.equalsNull(isActive(), o.isActive()) &&
                ExternalAlgorithms.equalsNull(getIsVerifiedEmail(), o.getIsVerifiedEmail()) &&
                ExternalAlgorithms.equalsNull(getIsVerifiedPhone(), o.getIsVerifiedPhone());
    }

    @Override
    public int hashCode() {
        return getDriverID();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Integer getDriverID() {
        return driverID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }


    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }


    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public DriverPreference getPreferences() {
        return preferences;
    }

    public void setPreferences(DriverPreference preferences) {
        this.preferences = preferences;
    }

    public Boolean isActive() {
        return isActive;
    }


}

