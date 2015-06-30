package ge.taxistgela.model;

import ge.taxistgela.bean.Order;
import ge.taxistgela.dao.OrderDaoAPI;

import java.util.List;

/**
 * Created by GIO on 6/5/2015.
 */
public class OrderManager extends OrderManagerAPI {
    public OrderManager(OrderDaoAPI orderDaoAPI){super(orderDaoAPI);}

    private boolean hasOpenOrder(int userID) {
        List<Order> ls = orderDaoAPI.getOrderByUserID(userID);

        for (Order elem : ls) {
            if (elem.getRevokedByDriver() ||
                    elem.getRevokedByDriver() ||
                    elem.getEndLocation() != null)
                return true;
        }
        return false;

    }

    @Override
    public boolean addOrder(Order order) {
        if (hasOpenOrder(order.getUserID())) return true;
        return orderDaoAPI.addOrder(order);
    }

    @Override
    public List<Order> getOrdersByCompanyID(Integer companyID) {
        return orderDaoAPI.getOrdersByDriverID(companyID);
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDaoAPI.updateOrder(order);
    }

    @Override
    public Order getOrderByID(Integer orderID) {
        return orderDaoAPI.getOrderByID(orderID);
    }

    @Override
    public List<Order> getOrderByUserID(Integer userID) {
        return orderDaoAPI.getOrderByUserID(userID);
    }

    @Override
    public List<Order> getOrdersByDriverID(Integer driverID) {
        return orderDaoAPI.getOrdersByDriverID(driverID);
    }
}
