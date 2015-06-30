package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by GIO on 6/28/2015.
 */
public class UserInfo extends User {




    public Object block = new Object();
    public List<OrderInfo> waitingList = Collections.synchronizedList(new ArrayList<>());
    private OrderInfo orderInfo = null;
    private DriverInfo driverInfo = null;
    public UserInfo() {
        super();
    }


    public UserInfo(User user) {
        super(user);
    }

    public synchronized void removeBadOrders() {
        waitingList.removeIf(orderInfo ->
                TimeUnit.MILLISECONDS.toMinutes(new Date().getTime()) - orderInfo.getCreateTime() > OrderInfo.MAXIMUM_ORDER_LIFETIME);
        waitingList.removeIf(orderInfo -> orderInfo.getDealIsDone());

    }

    public synchronized OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public synchronized void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public synchronized DriverInfo getDriverInfo() {
        return driverInfo;
    }

    public synchronized void setDriverInfo(DriverInfo driverInfo) {
        this.driverInfo = driverInfo;
    }
}
