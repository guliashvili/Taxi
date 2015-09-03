/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

package ge.taxistgela.bean;

import com.google.gson.annotations.Expose;
import ge.taxistgela.helper.ExternalAlgorithms;

import java.util.Date;

/**
 * Created by Alex on 5/25/2015.
 */
public class Order {
    @Expose
    private Integer orderID;
    @Expose
    private Integer userID;
    @Expose
    private Integer driverID;
    @Expose
    private Integer numPassengers;
    @Expose
    private Location startLocation;
    @Expose
    private Location endLocation;
    @Expose
    private Date startTime;
    @Expose
    private Date endTime;
    @Expose
    private Double paymentAmount;
    @Expose
    private Date callTime;
    @Expose
    private Boolean revokedByUser;
    @Expose
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
                ExternalAlgorithms.equalsNull(getCallTime(), o.getCallTime()) &&
                ExternalAlgorithms.equalsNull(getRevokedByDriver(), o.getRevokedByDriver()) &&
                ExternalAlgorithms.equalsNull(getRevokedByUser(), o.getRevokedByUser());
    }

    @Override
    public int hashCode() {
        return getOrderID();
    }

    public Boolean getRevokedByDriver() {
        return revokedByDriver;
    }

    public void setRevokedByDriver(Boolean revokedByDriver) {
        this.revokedByDriver = revokedByDriver;
    }

    public Boolean getRevokedByUser() {
        return revokedByUser;
    }

    public void setRevokedByUser(Boolean revokedByUser) {
        this.revokedByUser = revokedByUser;
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
