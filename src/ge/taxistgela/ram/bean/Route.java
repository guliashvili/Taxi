package ge.taxistgela.ram.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GIO on 6/29/2015.
 */
public class Route {
    public List<OrderInfo> inCar = Collections.synchronizedList(new ArrayList<>());
    public List<RouteElement> route = Collections.synchronizedList(new ArrayList<>());

    public synchronized void addOrder(OrderInfo orderInfo) {
        RouteElement a = new RouteElement(orderInfo.getStart(), orderInfo, true);
        RouteElement b = new RouteElement(orderInfo.getEnd(), orderInfo, false);

        route.add(a);
        route.add(b);
    }

    public synchronized void pickUser(UserInfo userInfo, int orderID) {
        RouteElement a = null;
        for (RouteElement elem : route)
            if (elem.getOrderInfo().getUser().getUserID() == userInfo.getUserID() &&
                    elem.getOrderInfo().getOrderID() == orderID
                    && elem.isPickUser()) {
                a = elem;
                break;
            }

        if (a != null && route.removeIf(routeElement ->
                routeElement.getOrderInfo().getUser().getUserID() == userInfo.getUserID()
                        && routeElement.getOrderInfo().getOrderID() == orderID &&
                        routeElement.isPickUser())) {

            inCar.add(a.getOrderInfo());

        }
    }

    public synchronized void leaveUser(UserInfo userInfo, int orderID) {
        RouteElement a = null;
        for (RouteElement elem : route) {
            if (elem.getOrderInfo().getUser().getUserID() == userInfo.getUserID() &&
                    elem.getOrderInfo().getOrderID() == orderID
                    && !elem.isPickUser()) {
                a = elem;
                break;
            }
        }
        if (a != null) {
            boolean res1 = route.removeIf(routeElement ->
                    routeElement.getOrderInfo().getUser().getUserID() == userInfo.getUserID()
                            && routeElement.getOrderInfo().getOrderID() == orderID &&
                            !routeElement.isPickUser());
            boolean res2 = route.removeIf(routeElement ->
                    routeElement.getOrderInfo().getUser().getUserID() == userInfo.getUserID()
                            && routeElement.getOrderInfo().getOrderID() == orderID &&
                            !routeElement.isPickUser());
        }
    }


}
