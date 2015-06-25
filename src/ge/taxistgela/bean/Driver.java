package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Alex on 5/25/2015.
 */
public class Driver extends GeneralCheckableInformation {
    private Integer driverID;
    private String personalID;
    private String email;
    private String password;
    private Integer companyID;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private Car car;
    private String facebookID;
    private String googleID;
    private Location location;
    private Double rating;
    private DriverPreference preferences;
    private Boolean isActive;
    private Boolean isVerifiedEmail;
    private Boolean isVerifiedPhone;
    private String token;

    public Driver() {
        token = RandomStringUtils.randomAscii(20);
    }

    public Driver(Integer driverID, String personalID, String email, String password, Integer companyID, String firstName, String lastName, Gender gender, String phoneNumber, Car car, String facebookID, String googleID, Location location, Double rating, DriverPreference preferences, Boolean isActive, Boolean isVerifiedEmail, Boolean isVerifiedPhone) {
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
        setLocation(location);
        setRating(rating);
        setPreferences(preferences);
        setIsActive(isActive);
        setIsVerifiedEmail(isVerifiedEmail);
        setIsVerifiedPhone(isVerifiedPhone);
    }

    public Driver(Driver driver) {
        this(driver.getDriverID(), driver.getPersonalID(), driver.getEmail(), driver.getPassword(), driver.getCompanyID(),
                driver.getFirstName(), driver.getLastName(), driver.getGender(), driver.getPhoneNumber(), driver.getCar(),
                driver.getFacebookID(), driver.getGoogleID(), driver.getLocation(), driver.getRating(), driver.getPreferences(),
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

                ExternalAlgorithms.equalsNull(getLocation(), o.getLocation()) &&
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

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public Boolean getIsVerifiedEmail() {
        return isVerifiedEmail;
    }

    public void setIsVerifiedEmail(Boolean isVerifiedEmail) {
        this.isVerifiedEmail = isVerifiedEmail;
    }

    public Boolean getIsVerifiedPhone() {
        return isVerifiedPhone;
    }

    public void setIsVerifiedPhone(Boolean isVerifiedPhone) {
        this.isVerifiedPhone = isVerifiedPhone;
    }
}

