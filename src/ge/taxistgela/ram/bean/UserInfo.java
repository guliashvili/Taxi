package ge.taxistgela.ram.bean;

import com.google.gson.annotations.Expose;
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


    @Expose
    public List<OrderInfo> waitingList = Collections.synchronizedList(new ArrayList<>());
    @Expose
    private OrderInfo orderInfo = null;
    @Expose
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

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public DriverInfo getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(DriverInfo driverInfo) {
        this.driverInfo = driverInfo;
    }
}
