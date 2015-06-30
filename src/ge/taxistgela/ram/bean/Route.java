package ge.taxistgela.ram.bean;

import ge.taxistgela.helper.GoogleMapUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GIO on 6/29/2015.
 */
public class Route {
    public List<OrderInfo> inCar = Collections.synchronizedList(new ArrayList<>());
    public List<RouteElement> route = Collections.synchronizedList(new ArrayList<>());


    public static double getDistance(List<RouteElement> tmp) {
        double ret = 0;
        for (int i = 1; i < tmp.size(); i++) {
            ret += GoogleMapUtils.getRoad(tmp.get(i - 1).getLoc(), tmp.get(i).getLoc()).distance.inMeters;
        }
        ret /= 1000;

        return ret;
    }


    public static double findTheBest(RouteElement last, List<RouteElement> tmp, double bestAns, double curAns) {
        if (bestAns < curAns) return bestAns;
        if (tmp.size() == 0) return curAns;

        for (int i = 0; i < tmp.size(); i++) {
            boolean isNotLogical = false;
            if (!tmp.get(i).isPickUser()) {
                for (RouteElement elem : tmp) {
                    if (elem.isPickUser() &&
                            elem.getOrderInfo().getOrderID() == tmp.get(i).getOrderInfo().getOrderID()) {
                        isNotLogical = true;
                        break;
                    }
                }
            }
            if (isNotLogical) continue;

            double cur = curAns;
            if (last != null)
                cur += GoogleMapUtils.getRoad(last.getLoc(), tmp.get(i).getLoc()).distance.inMeters / 1000.0;

            last = tmp.get(i);
            tmp.remove(i);

            bestAns = findTheBest(last, tmp, bestAns, cur);

            tmp.add(i, last);

        }
        return bestAns;
    }

    public double getOptimalPrice(OrderInfo orderInfo) {
        List<RouteElement> tmp = new ArrayList<>();
        tmp.addAll(route);

        double dist0 = getDistance(tmp);

        tmp.add(new RouteElement(orderInfo.getStart(), orderInfo, false));
        tmp.add(new RouteElement(orderInfo.getEnd(), orderInfo, true));

        tmp.sort((o1, o2) -> o1.getOrderInfo().getOrderID() - o2.getOrderInfo().getOrderID());

        double dist1 = findTheBest(null, tmp, 99999999, 0);

        double M = orderInfo.getDistance();

        int n = 0;
        synchronized (inCar) {
            for (OrderInfo orderInfo1 : inCar) {
                if (orderInfo1.getOrderID() != orderInfo.getOrderID()) n++;
            }

            synchronized (route) {
                for (RouteElement routeElement : route) {
                    if (routeElement.getOrderInfo().getOrderID() != orderInfo.getOrderID() && routeElement.isPickUser())
                        n++;
                }
            }

        }
        double payD = dist1 - dist0 + M / (n + 1);

        return payD;
    }

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
