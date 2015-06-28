package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;
import org.apache.commons.lang3.RandomStringUtils;
/**
 * Created by Alex on 5/25/2015.
 */
public class User extends SuperPersonalTokenUser {
    private Integer userID;
    private Double rating;
    private UserPreference preference;


    public User() {
        token = RandomStringUtils.randomAlphanumeric(20);
    }

    public User(Integer userID, String email, String password, String firstName, String lastName, String phoneNumber, Gender gender, String facebookID, String googleID, Double rating, UserPreference preference, Boolean isVerifiedEmail, Boolean isVerifiedPhone) {
        this();

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
        token = user.token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
                ExternalAlgorithms.equalsNull(getIsVerifiedPhone(), o.getIsVerifiedPhone()) &&
                ExternalAlgorithms.equalsNull(getToken(), o.getToken());
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

}
