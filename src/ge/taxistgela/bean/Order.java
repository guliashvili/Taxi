package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

import java.util.Date;

/**
 * Created by Alex on 5/25/2015.
 */
public class Order {
    private Integer orderID;
    private Integer userID;
    private Integer driverID;
    private Integer numPassengers;
    private Location startLocation;
    private Location endLocation;
    private Date startTime;
    private Date endTime;
    private Double paymentAmount;
    private Date callTime;
    private Boolean revokedByUser;
    private Boolean revokedByDriver;

    public Order() {

    }

    public Order(Integer orderID, Integer userID, Integer driverID, Integer numPassengers, Location startLocation, Location endLocation, Date startTime, Date endTime,
                 Double paymentAmount, Date callTime, Boolean revokedByUser, Boolean revokedByDriver) {
        this();

        setOrderID(orderID);
        setUserID(userID);
        setDriverID(driverID);
        setNumPassengers(numPassengers);
        setStartLocation(startLocation);
        setEndLocation(endLocation);
        setStartTime(startTime);
        setEndTime(endTime);
        setPaymentAmount(paymentAmount);
        setCallTime(callTime);
        setRevokedByUser(revokedByUser);
        setRevokedByDriver(revokedByDriver);
    }

    public Order(Order ord) {
        this(ord.getOrderID(), ord.getUserID(), ord.getDriverID(), ord.getNumPassengers(), ord.getStartLocation(),
                ord.getEndLocation(), ord.getStartTime(), ord.getEndTime(), ord.getPaymentAmount(), ord.getCallTime(),
                ord.getRevokedByUser(), ord.getRevokedByDriver());
    }


    @Override
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Order)) return false;
        Order o = (Order) obj;
        return ExternalAlgorithms.equalsNull(getOrderID(), o.getOrderID()) &&
                ExternalAlgorithms.equalsNull(getUserID(), o.getUserID()) &&
                ExternalAlgorithms.equalsNull(getDriverID(), o.getDriverID()) &&
                ExternalAlgorithms.equalsNull(getNumPassengers(), o.getNumPassengers()) &&
                ExternalAlgorithms.equalsNull(getStartLocation(), o.getStartLocation()) &&
                ExternalAlgorithms.equalsNull(getEndLocation(), o.getEndLocation()) &&
                ExternalAlgorithms.equalsNull(getStartLocation(), o.getStartLocation()) &&
                ExternalAlgorithms.equalsNull(getEndTime(), o.getEndTime()) &&
                ExternalAlgorithms.equalsNull(getPaymentAmount(), o.getPaymentAmount()) &&
                ExternalAlgorithms.equalsNull(getCallTime(), o.getCallTime()) &&
                ExternalAlgorithms.equalsNull(getRevokedByDriver(), o.getRevokedByDriver()) &&
                ExternalAlgorithms.equalsNull(getRevokedByUser(), o.getRevokedByUser());
    }

    @Override
    public synchronized int hashCode() {
        return getOrderID();
    }

    public synchronized Boolean getRevokedByDriver() {
        return revokedByDriver;
    }

    public synchronized void setRevokedByDriver(Boolean revokedByDriver) {
        this.revokedByDriver = revokedByDriver;
    }

    public synchronized Boolean getRevokedByUser() {
        return revokedByUser;
    }

    public synchronized void setRevokedByUser(Boolean revokedByUser) {
        this.revokedByUser = revokedByUser;
    }

    public synchronized Integer getOrderID() {
        return orderID;
    }

    public synchronized void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public synchronized Integer getUserID() {
        return userID;
    }

    public synchronized void setUserID(Integer userID) {
        this.userID = userID;
    }

    public synchronized Integer getDriverID() {
        return driverID;
    }

    public synchronized void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }

    public synchronized Integer getNumPassengers() {
        return numPassengers;
    }

    public synchronized void setNumPassengers(Integer numPassengers) {
        this.numPassengers = numPassengers;
    }

    public synchronized Location getStartLocation() {
        return startLocation;
    }

    public synchronized void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public synchronized Location getEndLocation() {
        return endLocation;
    }

    public synchronized void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public synchronized Date getStartTime() {
        return startTime;
    }

    public synchronized void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public synchronized Date getEndTime() {
        return endTime;
    }

    public synchronized void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public synchronized Double getPaymentAmount() {
        return paymentAmount;
    }

    public synchronized void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public synchronized Date getCallTime() {
        return callTime;
    }

    public synchronized void setCallTime(Date callTime) {
        this.callTime = callTime;
    }
}
