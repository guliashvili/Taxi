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

    public static double getDistance(Location start, List<RouteElement> tmp) {
        double ret = 0;
        for (int i = 1; i < tmp.size(); i++) {
            ret += GoogleMapUtils.getRoad(tmp.get(i - 1).getLoc(), tmp.get(i).getLoc()).distance.inMeters;
        }
        if (tmp.size() > 0) ret += GoogleMapUtils.getRoad(start, tmp.get(0).getLoc()).distance.inMeters;

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

            bestAns = findTheBest(elem.getLoc(), nextCurTime,
                    tmp, bestAns, cur);

            tmp.add(i, elem);

        }
        return bestAns;
    }

    public int getCount(int orderID) {
        int n = 0;
        for (OrderInfo orderInfo1 : inCar) {
            if (orderInfo1.getOrderID() != orderID) n++;
        }

        for (RouteElement routeElement : route) {
            if (routeElement.getOrderInfo().getOrderID() != orderID && routeElement.isPickUser())
                n++;
        }
        return n;
    }

    public double getOptimalPriceIfInserted(OrderInfo orderInfo) {


        List<RouteElement> tmp = new ArrayList<>();
        tmp.addAll(route);

        double dist0 = getDistance(drivers.get(orderInfo.getDriver().getDriverID()).getLocation(), tmp);

        List<RouteElement> tmp2 = new ArrayList<>();
        tmp2.add(new RouteElement(orderInfo.getStart(), orderInfo, true));
        tmp2.add(new RouteElement(orderInfo.getEnd(), orderInfo, false));

        double M = getDistance(drivers.get(orderInfo.getDriver().getDriverID()).getLocation(), tmp2);

        tmp.addAll(tmp2);

        double dist1 = findTheBest(drivers.get(orderInfo.getDriver().getDriverID()).getLocation(),
                TimeUnit.MILLISECONDS.toMinutes(new Date().getTime()), tmp, 99999999, 0);



        int n = getCount(orderInfo.getOrderID());
        double me = dist1 - dist0;
        double payD = me + (M - me) / (n + 1);

        payD *= orderInfo.getDriver().getPreferences().getCoefficientPer();

        payD = Math.max(payD, 1.0);

        return payD;
    }

    public synchronized double updateLocations(Location last, double curTime, List<RouteElement> tmp, double bestAns, double curAns,
                                               List<RouteElement> current, List<RouteElement> best) {
        if (bestAns < curAns)
            return bestAns;

        if (tmp.size() == 0) {
            best.clear();
            best.addAll(current);

            return curAns;
        }


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
            current.add(elem);
            tmp.remove(i);

            bestAns = updateLocations(elem.getLoc(), nextCurTime,
                    tmp, bestAns, cur, current, best);

            current.remove(current.size() - 1);
            tmp.add(i, elem);

        }
        return bestAns;
    }

    public synchronized void addOrder(OrderInfo orderInfo) {
        List<RouteElement> tmp = new ArrayList<>();
        tmp.addAll(route);

        double dist0 = getDistance(drivers.get(orderInfo.getDriver().getDriverID()).getLocation(), tmp);

        List<RouteElement> tmp2 = new ArrayList<>();
        tmp2.add(new RouteElement(orderInfo.getStart(), orderInfo, true));
        tmp2.add(new RouteElement(orderInfo.getEnd(), orderInfo, false));

        double M = getDistance(drivers.get(orderInfo.getDriver().getDriverID()).getLocation(), tmp2);

        tmp.addAll(tmp2);

        List<RouteElement> current = new ArrayList<>();
        List<RouteElement> best = new ArrayList<>();
        double dist1 = updateLocations(drivers.get(orderInfo.getDriver().getDriverID()).getLocation(),
                TimeUnit.MILLISECONDS.toMinutes(new Date().getTime()), tmp, 99999999, 0, current, best);


        int n = getCount(orderInfo.getOrderID());
        double me = dist1 - dist0;
        double payMe = me + (M - me) / (n + 1);
        double addOther = 0;
        if (n > 0) {
            addOther = (M - me) / (n + 1) - (M - me) / n;
        }
        addOther *= orderInfo.getDriver().getPreferences().getCoefficientPer();
        payMe *= orderInfo.getDriver().getPreferences().getCoefficientPer();

        for (RouteElement elem : best) {
            if (elem.getOrderInfo().getUser().getUserID() == orderInfo.getOrderID()) {
                payMe = Math.max(payMe, 1.0);
                payMe = Math.min(payMe, elem.getOrderInfo().getMaxPrice());
                elem.getOrderInfo().setMaxPrice(payMe);
            } else {
                double price = Math.max(elem.getOrderInfo().getMaxPrice() + addOther, 1);
                price = Math.min(price, elem.getOrderInfo().getMaxPrice());
                elem.getOrderInfo().setMaxPrice(price);
            }

        }

        route = best;
        
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

    public synchronized double leaveUser(UserInfo userInfo, int orderID) {

        OrderInfo orderInfoo = null;
        for (OrderInfo elem : inCar) {
            if (elem.getUser().getUserID() == userInfo.getUserID()) orderInfoo = elem;
        }
        route.removeIf(routeElement -> routeElement.getOrderInfo().getUser().getUserID() == userInfo.getUserID());
        inCar.removeIf(orderInfo -> orderInfo.getUser().getUserID() == userInfo.getUserID());

        if (orderInfoo == null) return -1;
        else
            return orderInfoo.getMaxPrice();

    }


}
