package ge.taxistgela.bean;

/**
 * Created by Alex on 5/25/2015.
 */
public class User {
    private enum Gender{
        MALE,FEMALE
    }

    private int userID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    Gender gender;
    private String facebookID;
    private String googleID;
    private UserPreferences prefences;

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

    public Gender isGender() {
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

    public UserPreferences getPrefences() {
        return prefences;
    }

    public void setPrefences(UserPreferences prefences) {
        this.prefences = prefences;
    }
}
