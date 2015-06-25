package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class User extends GeneralCheckableInformation {
    private Integer userID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Gender gender;
    private String facebookID;
    private String googleID;
    private Double rating;
    private UserPreference preference;
    private Boolean isVerifiedEmail;
    private Boolean isVerifiedPhone;

    public User() {
    }

    public User(Integer userID, String email, String password, String firstName, String lastName, String phoneNumber, Gender gender, String facebookID, String googleID, Double rating, UserPreference preference, Boolean isVerifiedEmail, Boolean isVerifiedPhone) {
        setUserID(userID);
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setGender(gender);
        setFacebookID(facebookID);
        setGoogleID(googleID);
        setRating(rating);
        setPreference(preference);
        setIsVerifiedEmail(isVerifiedEmail);
        setIsVerifiedPhone(isVerifiedPhone);
    }

    public User(User user) {
        this(user.getUserID(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(),
                user.getGender(), user.getFacebookID(), user.getGoogleID(), user.getRating(), user.getPreference(),
                user.getIsVerifiedEmail(), user.getIsVerifiedPhone());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) return false;
        User o = (User) obj;
        return ExternalAlgorithms.equalsNull(getUserID(), o.getUserID()) &&
                ExternalAlgorithms.equalsNull(getEmail(), o.getEmail()) &&
                ExternalAlgorithms.equalsNull(getPassword(), o.getPassword()) &&

                ExternalAlgorithms.equalsNull(getFirstName(), o.getFirstName()) &&
                ExternalAlgorithms.equalsNull(getLastName(), o.getLastName()) &&
                ExternalAlgorithms.equalsNull(getPhoneNumber(), o.getPhoneNumber()) &&

                ExternalAlgorithms.equalsNull(getGender(), o.getGender()) &&

                ExternalAlgorithms.equalsNull(getFacebookID(), o.getFacebookID()) &&
                ExternalAlgorithms.equalsNull(getGoogleID(), o.getGoogleID()) &&
                ExternalAlgorithms.equalsNull(getRating(), o.getRating()) &&
                ExternalAlgorithms.equalsNull(getPreference(), o.getPreference()) &&
                ExternalAlgorithms.equalsNull(getIsVerifiedEmail(), o.getIsVerifiedEmail()) &&
                ExternalAlgorithms.equalsNull(getIsVerifiedPhone(), o.getIsVerifiedPhone());
    }

    @Override
    public int hashCode() {
        return getUserID();
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    @Override
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public UserPreference getPreference() {
        return preference;
    }

    public void setPreference(UserPreference preference) {
        this.preference = preference;
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
