package ge.taxistgela.ram.bean;

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

    public List<OrderInfo> waitingList = Collections.synchronizedList(new ArrayList<>());
    public Route route;

    public Object block = new Object();
    public int nPassengers = 0;
    private Location location = null;

    public DriverInfo(Driver driver, ConcurrentHashMap<Integer, UserInfo> users, ConcurrentHashMap<Integer, DriverInfo> drivers) {
        super(driver);
        route = new Route(drivers, users);
    }

    public DriverInfo(DriverInfo driverInfo) {
        super(driverInfo);
        waitingList = Collections.synchronizedList(new ArrayList<>());
        route = new Route(driverInfo.route.drivers, driverInfo.route.users);
        block = driverInfo.block;
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
