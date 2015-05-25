package ge.taxistgela.bean;

/**
 * Created by Alex on 5/25/2015.
 */
public class Driver {
    private int driverID;
    private String presonalID;
    private String username;
    private String email;
    private String password;
    private int companyID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Car car;
    private String facebookID;
    private String googleIID;
    private Location location;
    private double rating;
    private DriverPreferences preferences;

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getPresonalID() {
        return presonalID;
    }

    public void setPresonalID(String presonalID) {
        this.presonalID = presonalID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
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

    public String getGoogleIID() {
        return googleIID;
    }

    public void setGoogleIID(String googleIID) {
        this.googleIID = googleIID;
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

    public DriverPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(DriverPreferences preferences) {
        this.preferences = preferences;
    }
}
