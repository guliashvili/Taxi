package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.*;


/**
 * Created by GIO on 6/28/2015.
 */
public class DriverInfo extends Driver {

    private Location location;
    public Object block = new Object();

    public DriverInfo() {
        super();
    }

    public DriverInfo(Driver driver) {
        super(driver);
    }

    public synchronized Location getLocation() {
        return location;
    }

    public synchronized void setLocation(Location location) {
        this.location = location;
    }
}
