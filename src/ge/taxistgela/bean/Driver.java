package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Alex on 5/25/2015.
 */
public class Driver extends SuperPersonalTokenUser {
    private Integer driverID;
    private String personalID;
    private Integer companyID;
    private Car car;
    private Double rating;
    private DriverPreference preferences;
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
        setCar(car);
        setFacebookID(facebookID);
        setGoogleID(googleID);
        setRating(rating);
        setPreferences(preferences);
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

    public synchronized void update(Integer driverID, String personalID, String email, String password, Integer companyID, String firstName, String lastName, Gender gender, String phoneNumber, Car car, String facebookID, String googleID, Double rating, DriverPreference preferences, Boolean isActive, Boolean isVerifiedEmail, Boolean isVerifiedPhone) {
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

    public synchronized void update(Driver driver) {
        update(driver.getDriverID(), driver.getPersonalID(), driver.getEmail(), driver.getPassword(), driver.getCompanyID(),
                driver.getFirstName(), driver.getLastName(), driver.getGender(), driver.getPhoneNumber(), driver.getCar(),
                driver.getFacebookID(), driver.getGoogleID(), driver.getRating(), driver.getPreferences(),
                driver.isActive(), driver.getIsVerifiedEmail(), driver.getIsVerifiedPhone());
        token = driver.token;
    }

    public synchronized Boolean getIsActive() {
        return isActive;
    }

    public synchronized void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public synchronized String getToken() {
        return token;
    }

    public synchronized void setToken(String token) {
        this.token = token;
    }

    @Override
    public synchronized boolean equals(Object obj) {
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
    public synchronized int hashCode() {
        return getDriverID();
    }

    public synchronized Car getCar() {
        return car;
    }

    public synchronized void setCar(Car car) {
        this.car = car;
    }

    public synchronized Integer getDriverID() {
        return driverID;
    }

    public synchronized void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }

    public synchronized String getPersonalID() {
        return personalID;
    }

    public synchronized void setPersonalID(String personalID) {
        this.personalID = personalID;
    }


    public synchronized Integer getCompanyID() {
        return companyID;
    }

    public synchronized void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }


    public synchronized Double getRating() {
        return rating;
    }

    public synchronized void setRating(Double rating) {
        this.rating = rating;
    }

    public synchronized DriverPreference getPreferences() {
        return preferences;
    }

    public synchronized void setPreferences(DriverPreference preferences) {
        this.preferences = preferences;
    }

    public synchronized Boolean isActive() {
        return isActive;
    }


}

