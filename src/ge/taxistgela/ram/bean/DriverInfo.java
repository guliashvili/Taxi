package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.*;

/**
 * Created by GIO on 6/28/2015.
 */
public class DriverInfo extends Driver {

    private Location location;

    public DriverInfo() {
        super();
    }

    public DriverInfo(Driver driver) {
        super(driver);
    }

    public DriverInfo(Integer driverID, String personalID, String email, String password, Integer companyID, String firstName, String lastName, Gender gender, String phoneNumber, Car car, String facebookID, String googleID, Double rating, DriverPreference preferences, Boolean isActive, Boolean isVerifiedEmail, Boolean isVerifiedPhone) {
        super(driverID, personalID, email, password, companyID, firstName, lastName, gender, phoneNumber, car, facebookID, googleID, rating, preferences, isActive, isVerifiedEmail, isVerifiedPhone);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
