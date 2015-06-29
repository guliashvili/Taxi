package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.Location;

/**
 * Created by GIO on 6/29/2015.
 */
public class RouteElement {
    private OrderInfo orderInfo;
    private Location loc;
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
