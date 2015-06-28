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



    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
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

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}