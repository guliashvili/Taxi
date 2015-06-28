package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by GIO on 6/28/2015.
 */
public class DriverInfo extends Driver {

    public List<OrderInfo> waitingList = Collections.synchronizedList(new ArrayList<OrderInfo>());
    public Object block = new Object();
    public int nPassengers;
    private Location location;

    public DriverInfo() {
        super();
    }

    public DriverInfo(Driver driver) {
        super(driver);
    }

    public synchronized int getnPassengers() {
        return nPassengers;
    }

    public synchronized void setnPassengers(int nPassengers) {
        this.nPassengers = nPassengers;
    }

    public synchronized Location getLocation() {
        return location;
    }

    public synchronized void setLocation(Location location) {
        this.location = location;
    }
}
