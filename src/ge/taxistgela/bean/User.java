package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class User {
    private int userID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Gender gender;
    private String facebookID;
    private String googleID;
    private double rating;
    private UserPreference preference;
    private  boolean isVerified;

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  User)) return  false;
        User o = (User)obj;
        return  getUserID() == o.getUserID() &&
                ExternalAlgorithms.equalsNull(getEmail(), o.getEmail()) &&
                ExternalAlgorithms.equalsNull(getPassword(),o.getPassword()) &&

                ExternalAlgorithms.equalsNull(getFirstName(), o.getFirstName()) &&
                ExternalAlgorithms.equalsNull(getLastName(),o.getLastName()) &&
                ExternalAlgorithms.equalsNull(getPhoneNumber(),o.getPhoneNumber()) &&

                ExternalAlgorithms.equalsNull(getGender(), o.getGender()) &&

                ExternalAlgorithms.equalsNull(getFacebookID(), o.getFacebookID()) &&
                ExternalAlgorithms.equalsNull(getGoogleID(),o.getGoogleID()) &&
                getRating() == o.getRating() &&
                ExternalAlgorithms.equalsNull(getPreference(),o.getPreference()) &&
                isVerified() == o.isVerified;
    }

    @Override
    public int hashCode() {
        return getUserID();
    }


    public boolean isVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public UserPreference getPreference() {
        return preference;
    }

    public void setPreference(UserPreference preference) {
        this.preference = preference;
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

}
