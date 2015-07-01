package ge.taxistgela.ram.bean;

import com.google.gson.annotations.Expose;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.Location;
import ge.taxistgela.bean.User;

/**
 * Created by GIO on 6/28/2015.
 */
public class OrderInfo{
    public final  static int MAXIMUM_ORDER_LIFETIME = 30;
    @Expose
    private double maxPrice;
    @Expose
    private double distance;
    @Expose
    private long createTime;
    @Expose
    private Location start, end;
    @Expose
    private int nPassengers;
    @Expose
    private long startTime;
    @Expose
    private int orderID;
    @Expose
    private User user;
    @Expose
    private Driver driver;
    @Expose
    private BOOLEAN dealisDone;
    @Expose
    private Location location;

    public OrderInfo(long createTime, Location end, int nPassengers, double maxPrice, Location start, long startTime, double distance, User user, Driver driver, BOOLEAN dealisDone, int orderID, Location location) {
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
        this.location = location;
    }

    public OrderInfo(OrderInfo orderInfo) {
        this(orderInfo.createTime, orderInfo.end, orderInfo.nPassengers, orderInfo.maxPrice, orderInfo.start, orderInfo.startTime, orderInfo.distance,
                orderInfo.user, orderInfo.driver, orderInfo.dealisDone, orderInfo.orderID, orderInfo.location);
    }


    public boolean getSetTrueDealIsDone() {
        boolean ret = dealisDone.get();
        if (!ret)
            dealisDone.set(true);
        return ret;
    }

    public boolean getDealIsDone() {
        return dealisDone.get();
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }


    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }


    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }


    public int getnPassengers() {
        return nPassengers;
    }

    public void setnPassengers(int nPassengers) {
        this.nPassengers = nPassengers;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }


    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

}