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

    public Order(Integer orderID, Integer userID, Integer driverID, Integer numPassengers, Location startLocation, Location endLocation, Date startTime, Date endTime, Double paymentAmount, Date callTime) {
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
    }

    public Order() {
    }

    @Override
    public boolean equals(Object obj) {
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
                ExternalAlgorithms.equalsNull(getCallTime(), o.getCallTime());
    }

    @Override
    public int hashCode() {
        return getOrderID();
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getDriverID() {
        return driverID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }

    public Integer getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(Integer numPassengers) {
        this.numPassengers = numPassengers;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }
}
