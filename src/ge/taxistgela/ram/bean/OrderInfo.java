package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.Location;

/**
 * Created by GIO on 6/28/2015.
 */
public class OrderInfo{
    public final  static int MAXIMUM_ORDER_LIFETIME = 30;
    private int driverID;
    private double maxPrice;
    private double distance;
    private double pricePer;
    private  int userID;
    private long createTime;
    private Location start,end;
    private int nPassengers;
    private long startTime;
    private long timeLimit;


    public OrderInfo(long createTime, int driverID, Location end, int nPassengers, double maxPrice, Location start, int userID, long startTime, long timeLimit, double distance, double pricePer) {
        this.createTime = createTime;
        this.driverID = driverID;
        this.end = end;
        this.nPassengers = nPassengers;
        this.maxPrice = maxPrice;
        this.start = start;
        this.userID = userID;
        this.startTime = startTime;
        this.timeLimit = timeLimit;
        this.distance = distance;
        this.pricePer = pricePer;

    }

    public synchronized void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public synchronized void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
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

    public synchronized double getPricePer() {
        return pricePer;
    }

    public synchronized void setPricePer(double pricePer) {
        this.pricePer = pricePer;
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