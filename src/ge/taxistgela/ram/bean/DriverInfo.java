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

package ge.taxistgela.ram.bean;

import com.google.gson.annotations.Expose;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


/**
 * Created by GIO on 6/28/2015.
 */
public class DriverInfo extends Driver {

    @Expose
    public List<OrderInfo> waitingList = Collections.synchronizedList(new ArrayList<>());
    @Expose
    public Route route;

    @Expose
    public int nPassengers = 0;
    @Expose
    private Location location = null;

    public DriverInfo(Driver driver, ConcurrentHashMap<Integer, UserInfo> users, ConcurrentHashMap<Integer, DriverInfo> drivers) {
        super(driver);
        route = new Route(drivers, users);
    }

    public DriverInfo(DriverInfo driverInfo) {
        super(driverInfo);
        waitingList = Collections.synchronizedList(new ArrayList<>());
        route = new Route(driverInfo.route.drivers, driverInfo.route.users);
        nPassengers = driverInfo.nPassengers;
        location = driverInfo.location;
    }

    public synchronized void removeBadOrders() {
        waitingList.removeIf(orderInfo ->
                TimeUnit.MILLISECONDS.toMinutes(new Date().getTime()) - orderInfo.getCreateTime() > OrderInfo.MAXIMUM_ORDER_LIFETIME);
        waitingList.removeIf(orderInfo -> orderInfo.getDealIsDone());

    }

    public synchronized int getnPassengers() {
        return nPassengers;
    }

    public synchronized void setnPassengers(int nPassengers) {
        this.nPassengers = nPassengers;
    }

    public synchronized Location getLocation() {
        return location;
    }

    public synchronized void setLocation(Location location) {
        this.location = location;
    }
}
