package ge.taxistgela.ram.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GIO on 6/27/2015.
 */
public class UserInfo {
    public Object lock = new Object();
    public long orderInstallStartTime = -1;
    public List<DriverInfo> firstRoundDrivers = Collections.synchronizedList(new ArrayList<>());
    public List<DriverInfo> secondRoundDrivers = Collections.synchronizedList(new ArrayList<>());




    private double cost = 0;
    private double timeLimit;
    private int numPassengers;
    private int userID;

    public void update(UserInfo userInfo) {
        timeLimit = userInfo.getTimeLimit();
        numPassengers = userInfo.getNumPassengers();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void addCost(double addCost) {
        this.cost += addCost;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }
}
