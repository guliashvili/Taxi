package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.helper.HashGenerator;

/**
 * Created by Alex on 5/25/2015.
 */
public class Driver implements GeneralCheckableInformation{
    private int driverID;
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
    private double rating;
    private DriverPreference preferences;
    private boolean isActive;
    private  boolean isVerified;

    public Driver(int driverID, String personalID, String email, String password, Integer companyID, String firstName, String lastName, Gender gender, String phoneNumber, Car car, String facebookID, String googleID, Location location, double rating, DriverPreference preferences, boolean isActive, boolean isVerified) {
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
        setIsVerified(isVerified);
    }

    public  Driver(){}


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Driver)) return  false;
        Driver o = (Driver)obj;
        return  getDriverID() == o.getDriverID() &&
                ExternalAlgorithms.equalsNull(getPersonalID(),o.getPersonalID()) &&
                ExternalAlgorithms.equalsNull(getEmail(), o.getEmail()) &&
                ExternalAlgorithms.equalsNull(getPassword(),o.getPassword()) &&
                getCompanyID() == o.getCompanyID() &&

                ExternalAlgorithms.equalsNull(getFirstName(), o.getFirstName()) &&
                ExternalAlgorithms.equalsNull(getLastName(),o.getLastName()) &&

                ExternalAlgorithms.equalsNull(getGender(), o.getGender()) &&

                ExternalAlgorithms.equalsNull(getPhoneNumber(), o.getPhoneNumber()) &&
                ExternalAlgorithms.equalsNull(getCar(),o.getCar()) &&

                ExternalAlgorithms.equalsNull(getFacebookID(), o.getFacebookID()) &&
                ExternalAlgorithms.equalsNull(getGoogleID(),o.getGoogleID()) &&

                ExternalAlgorithms.equalsNull(getLocation(), o.getLocation()) &&
                getRating() == o.getRating() &&
                ExternalAlgorithms.equalsNull(getPreferences(),o.getPreferences()) &&
                isActive == o.isActive &&
                isVerified() == o.isVerified;
    }

    @Override
    public int hashCode() {
        return getDriverID();
    }


    public boolean isVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public DriverPreference getPreferences() {
        return preferences;
    }

    public void setPreferences(DriverPreference preferences) {
        this.preferences = preferences;
    }
}
