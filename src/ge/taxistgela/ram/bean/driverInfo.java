package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GIO on 6/27/2015.
 */
public class DriverInfo {
    public List<UserInfo> timetable = Collections.synchronizedList(new ArrayList<>());
    public List<UserInfo> inTheCar = Collections.synchronizedList(new ArrayList<>());
    public Object lock = new Object();
    private Location location = null;
    private int numOfPassengers;
    private int maxNumOfPassengers;
    private int driverID;
    private double costPerKM;


    public void update(DriverInfo driverInfo) {
        maxNumOfPassengers = driverInfo.getNumOfPassengers();
        costPerKM = driverInfo.getCostPerKM();
    }

    public double getCostPerKM() {
        return costPerKM;
    }

    public void setCostPerKM(double costPerKM) {
        this.costPerKM = costPerKM;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    public int getMaxNumOfPassengers() {
        return maxNumOfPassengers;
    }

    public void setMaxNumOfPassengers(int maxNumOfPassengers) {
        this.maxNumOfPassengers = maxNumOfPassengers;
    }
}
