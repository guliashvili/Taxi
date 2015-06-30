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
    private int orderID;
    private User user;
    private Driver driver;
    private BOOLEAN dealisDone;


    public OrderInfo(long createTime, Location end, int nPassengers, double maxPrice, Location start, long startTime, double distance, User user, Driver driver, BOOLEAN dealisDone, int orderID) {
        this.createTime = createTime;
        this.end = end;
        this.nPassengers = nPassengers;
        this.maxPrice = maxPrice;
        this.start = start;
        this.startTime = startTime;
        this.distance = distance;
        if (user != null) setUser(user);
        if (driver != null) setDriver(driver);
        this.dealisDone = dealisDone;
        this.orderID = orderID;
    }

    public OrderInfo(OrderInfo orderInfo) {
        this(orderInfo.createTime, orderInfo.end, orderInfo.nPassengers, orderInfo.maxPrice, orderInfo.start, orderInfo.startTime, orderInfo.distance,
                orderInfo.user, orderInfo.driver, orderInfo.dealisDone, orderInfo.orderID);
    }


    public synchronized boolean getSetTrueDealIsDone() {
        boolean ret = dealisDone.get();
        if (!ret)
            dealisDone.set(true);
        return ret;
    }

    public synchronized boolean getDealIsDone() {
        return dealisDone.get();
    }

    public synchronized int getOrderID() {
        return orderID;
    }

    public synchronized void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public synchronized Driver getDriver() {
        return driver;
    }

    public synchronized void setDriver(Driver driver) {
        this.driver = new Driver(driver);
    }

    public synchronized User getUser() {
        return user;
    }

    public synchronized void setUser(User user) {
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