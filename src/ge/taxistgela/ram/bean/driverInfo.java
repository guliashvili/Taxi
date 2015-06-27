package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GIO on 6/27/2015.
 */
public class driverInfo {
    public List<userInfo> timetable = Collections.synchronizedList(new ArrayList<>());
    public List<userInfo> inTheCar = Collections.synchronizedList(new ArrayList<>());
    public Object lock = new Object();
    private Location location = null;
    private Integer numOfPassengers;
    private Integer maxNumOfPassengers;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(Integer numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    public Integer getMaxNumOfPassengers() {
        return maxNumOfPassengers;
    }

    public void setMaxNumOfPassengers(Integer maxNumOfPassengers) {
        this.maxNumOfPassengers = maxNumOfPassengers;
    }
}
