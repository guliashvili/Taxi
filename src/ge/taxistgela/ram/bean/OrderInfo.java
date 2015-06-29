package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.Location;
import ge.taxistgela.bean.User;

/**
 * Created by GIO on 6/28/2015.
 */
public class OrderInfo{
    public final  static int MAXIMUM_ORDER_LIFETIME = 30;
    private double maxPrice;
    private double distance;
    private long createTime;
    private Location start,end;
    private int nPassengers;
    private long startTime;
    private User user;
    private Driver driver;


    public OrderInfo(long createTime, Location end, int nPassengers, double maxPrice, Location start, long startTime, double distance, User user, Driver driver) {
        this.createTime = createTime;
        this.end = end;
        this.nPassengers = nPassengers;
        this.maxPrice = maxPrice;
        this.start = start;
        this.startTime = startTime;
        this.distance = distance;
        if (user != null) setUser(user);
        if (driver != null) setDriver(driver);

    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = new Driver(driver);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = new User(user);
    }

    public synchronized void setStartTime(long startTime) {
        this.startTime = startTime;
    }


    public synchronized void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public synchronized double getDistance() {
        return distance;
    }

    public synchronized void setDistance(double distance) {
        this.distance = distance;
    }

    public synchronized double getMaxPrice() {
        return maxPrice;
    }

    public synchronized void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }


    public synchronized Long getStartTime() {
        return startTime;
    }

    public synchronized void setStartTime(Long startTime) {
        this.startTime = startTime;
    }


    public synchronized int getnPassengers() {
        return nPassengers;
    }

    public synchronized void setnPassengers(int nPassengers) {
        this.nPassengers = nPassengers;
    }

    public synchronized Long getCreateTime() {
        return createTime;
    }

    public synchronized void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


    public synchronized Location getEnd() {
        return end;
    }

    public synchronized void setEnd(Location end) {
        this.end = end;
    }


    public synchronized Location getStart() {
        return start;
    }

    public synchronized void setStart(Location start) {
        this.start = start;
    }

}