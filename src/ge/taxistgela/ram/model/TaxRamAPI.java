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
