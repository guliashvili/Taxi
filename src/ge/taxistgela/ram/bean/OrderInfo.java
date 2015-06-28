package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.Location;

/**
 * Created by GIO on 6/28/2015.
 */
public class OrderInfo{
    public final  static int MAXIMUM_ORDER_LIFETIME = 30;
    private int driverID;
    private double price;
    private  int userID;
    private long createTime;
    private Location start,end;
    private int nPassengers;
    private long startTime;
    private long timeLimit;

    public OrderInfo(long createTime, int driverID, Location end, int nPassengers, double price, Location start, int userID,long startTime,long timeLimit) {
        this.createTime = createTime;
        this.driverID = driverID;
        this.end = end;
        this.nPassengers = nPassengers;
        this.price = price;
        this.start = start;
        this.userID = userID;
        this.startTime = startTime;
        this.timeLimit = timeLimit;
    }


    public synchronized Long getStartTime() {
        return startTime;
    }

    public synchronized void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public synchronized Long getTimeLimit() {
        return timeLimit;
    }

    public synchronized void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
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

    public synchronized int getDriverID() {
        return driverID;
    }

    public synchronized void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public synchronized Location getEnd() {
        return end;
    }

    public synchronized void setEnd(Location end) {
        this.end = end;
    }

    public synchronized double getPrice() {
        return price;
    }

    public synchronized void setPrice(double price) {
        this.price = price;
    }

    public synchronized Location getStart() {
        return start;
    }

    public synchronized void setStart(Location start) {
        this.start = start;
    }

    public synchronized int getUserID() {
        return userID;
    }

    public synchronized void setUserID(int userID) {
        this.userID = userID;
    }
}