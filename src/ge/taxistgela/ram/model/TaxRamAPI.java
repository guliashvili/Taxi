package ge.taxistgela.ram.model;

import ge.taxistgela.bean.Location;
import ge.taxistgela.bean.Order;
import ge.taxistgela.ram.bean.DriverInfo;
import ge.taxistgela.ram.bean.OrderInfo;
import ge.taxistgela.ram.bean.Route;
import ge.taxistgela.ram.bean.UserInfo;

import java.util.List;

/**
 * Created by GIO on 6/27/2015.
 */
public interface TaxRamAPI {
    void updateDriverLocation(int driverID, Location location);

    Location getDriverLocation(int driverID);

    double getPrice(DriverInfo driverInfo, UserInfo userInfo, OrderInfo orderInfo);

    void addOrder(Order order);

    List<OrderInfo> getWaitingUsers(int driverID);

    List<OrderInfo> getWaitingDrivers(int userID);

    boolean driverChoice(int driverID, int userID, int orderID, boolean accept);

    boolean userChoice(int driverID, int userID, int orderID, boolean accept);

    boolean pickUser(int driverID, int orderID, int userID);

    double leaveUser(int driverID, int orderID, int userID);

    Route getRouteDriver(int driverID);

    OrderInfo getRouteUser(int userID);

    boolean revokeOrderUser(int userID);

    boolean revokeOrderDriver(int userID);
}
