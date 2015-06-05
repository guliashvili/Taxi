package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Alex on 5/25/2015.
 */
public class Order {
    private int orderID;
    private int userID;
    private int driverID;
    private int numPassengers;
    private Location startLocation;
    private Location endLocation;
    private Date startTime;
    private Date endTime;
    private BigDecimal paymentAmount;
    private Date callTime;


    public Order(int orderID, int userID, int driverID, int numPassengers, Location startLocation, Location endLocation, Date startTime, Date endTime, BigDecimal paymentAmount, Date callTime) {
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
    public Order(){}

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Order)) return  false;
        Order o = (Order)obj;
        return  getOrderID() == o.getOrderID() &&
                getUserID() == o.getUserID() &&
                getDriverID() == o.getDriverID() &&
                getNumPassengers() == o.getNumPassengers() &&
                ExternalAlgorithms.equalsNull(getStartLocation(),o.getStartLocation()) &&
                ExternalAlgorithms.equalsNull(getEndLocation(),o.getEndLocation()) &&
                ExternalAlgorithms.equalsNull(getStartLocation(),o.getStartLocation()) &&
                ExternalAlgorithms.equalsNull(getEndTime(),o.getEndTime()) &&
                ExternalAlgorithms.equalsNull(getPaymentAmount(),o.getPaymentAmount()) &&
                ExternalAlgorithms.equalsNull(getCallTime(),o.getCallTime());
    }

    @Override
    public int hashCode() {
        return getOrderID();
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
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

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
