package ge.taxistgela.ram.bean;

import com.google.gson.annotations.Expose;
import ge.taxistgela.bean.Location;
import ge.taxistgela.helper.GoogleMapUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by GIO on 6/29/2015.
 */
public class Route implements Serializable {
    @Expose(serialize = true)
    public List<OrderInfo> inCar = Collections.synchronizedList(new ArrayList<>());
    @Expose(serialize = true)
    public List<RouteElement> route = Collections.synchronizedList(new ArrayList<>());
    public ConcurrentHashMap<Integer, DriverInfo> drivers = new ConcurrentHashMap<>();
    public ConcurrentHashMap<Integer, UserInfo> users = new ConcurrentHashMap<>();

    public Route(ConcurrentHashMap<Integer, DriverInfo> drivers, ConcurrentHashMap<Integer, UserInfo> users) {
        this.drivers = drivers;
        this.users = users;
    }

    public static double getDistance(List<RouteElement> tmp) {
        double ret = 0;
        for (int i = 1; i < tmp.size(); i++) {
            ret += GoogleMapUtils.getRoad(tmp.get(i - 1).getLoc(), tmp.get(i).getLoc()).distance.inMeters;
        }
        ret /= 1000;

        return ret;
    }


    public double findTheBest(Location last, double curTime, List<RouteElement> tmp, double bestAns, double curAns) {
        if (bestAns < curAns) return bestAns;
        if (tmp.size() == 0) return curAns;


        for (int i = 0; i < tmp.size(); i++) {
            boolean isNotLogical = false;
            double nextCurTime = curTime + GoogleMapUtils.getRoad(last, tmp.get(i).getLoc()).duration.inSeconds / 60.0;

            if (!tmp.get(i).isPickUser()) {
                for (RouteElement elem : tmp) {
                    if (elem.isPickUser() &&
                            elem.getOrderInfo().getOrderID() == tmp.get(i).getOrderInfo().getOrderID()) {
                        isNotLogical = true;
                        break;
                    }
                }

                isNotLogical |= (nextCurTime - tmp.get(i).getOrderInfo().getCreateTime()) >
                        tmp.get(i).getOrderInfo().getUser().getPreference().getTimeLimit();
            }


            if (isNotLogical) continue;

            double cur = curAns;

            cur += GoogleMapUtils.getRoad(last, tmp.get(i).getLoc()).distance.inMeters / 1000.0;


            RouteElement elem = tmp.get(i);
            tmp.remove(i);

            bestAns = findTheBest(last, nextCurTime,
                    tmp, bestAns, cur);

            tmp.add(i, elem);

        }
        return bestAns;
    }

    public double getOptimalPriceIfInserted(OrderInfo orderInfo) {
        List<RouteElement> tmp = new ArrayList<>();
        tmp.addAll(route);

        double dist0 = getDistance(tmp);

        tmp.add(new RouteElement(orderInfo.getStart(), orderInfo, false));
        tmp.add(new RouteElement(orderInfo.getEnd(), orderInfo, true));

        tmp.sort((o1, o2) -> o1.getOrderInfo().getOrderID() - o2.getOrderInfo().getOrderID());


        double dist1 = findTheBest(drivers.get(tmp.get(0).getOrderInfo().getDriver().getDriverID()).getLocation(),
                TimeUnit.MILLISECONDS.toMinutes(new Date().getTime()), tmp, 99999999, 0);

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

        return payD * orderInfo.getDriver().getPreferences().getCoefficientPer();
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
                            routeElement.isPickUser());
        }
    }


}
