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
import ge.taxistgela.bean.Location;

import java.io.Serializable;

/**
 * Created by GIO on 6/29/2015.
 */
public class RouteElement implements Serializable {
    @Expose
    private OrderInfo orderInfo;
    @Expose
    private Location loc;
    @Expose
    private boolean pickUser; // True if you should pick at that location or false if you should leave

    public RouteElement(Location loc, OrderInfo orderInfo, boolean pickUser) {
        this.loc = loc;
        this.orderInfo = orderInfo;
        this.pickUser = pickUser;
    }

    public RouteElement(RouteElement routeElement) {
        this(routeElement.loc, routeElement.orderInfo, routeElement.pickUser);
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = new OrderInfo(orderInfo);
    }

    public boolean isPickUser() {
        return pickUser;
    }

    public void setPickUser(boolean pickUser) {
        this.pickUser = pickUser;
    }


}
