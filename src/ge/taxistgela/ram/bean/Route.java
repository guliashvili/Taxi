package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GIO on 6/29/2015.
 */
public class Route {
    private List<User> inCar = Collections.synchronizedList(new ArrayList<>());
    private List<RouteElement> route = Collections.synchronizedList(new ArrayList<>());

    public synchronized void addOrder(OrderInfo orderInfo) {
        RouteElement a = new RouteElement(orderInfo.getStart(), orderInfo, true);
        RouteElement b = new RouteElement(orderInfo.getEnd(), orderInfo, false);

        route.add(a);
        route.add(b);
    }

    public synchronized void pickUser(int userID) {
        RouteElement a = null;
        for (RouteElement elem : route) {
            if (elem.getOrderInfo().getUser().getUserID() == userID && elem.isPickUser()) {
                a = elem;
                break;
            }
        }
        if (route.removeIf(routeElement -> routeElement.getOrderInfo().getUser().getUserID() == userID && routeElement.isPickUser())) {
            inCar.add(a.getOrderInfo().getUser());
        }
    }

    public synchronized void leaveUser(int userID) {
        RouteElement a = null;
        for (RouteElement elem : route) {
            if (elem.getOrderInfo().getUser().getUserID() == userID && elem.isPickUser()) {
                a = elem;
                break;
            }
        }
        if (route.removeIf(routeElement -> routeElement.getOrderInfo().getUser().getUserID() == userID && routeElement.isPickUser())) {
            inCar.add(a.getOrderInfo().getUser());
        }
    }


}
