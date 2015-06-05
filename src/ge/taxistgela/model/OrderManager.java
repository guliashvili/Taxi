package ge.taxistgela.model;

import ge.taxistgela.bean.Order;
import ge.taxistgela.dao.OrderDaoAPI;

import java.util.List;

/**
 * Created by GIO on 6/5/2015.
 */
public class OrderManager extends OrderManagerAPI {
    public OrderManager(OrderDaoAPI orderDaoAPI){super(orderDaoAPI);}
    @Override
    public int addOrder(Order order) {
        return orderDaoAPI.addOrder(order);
    }

    @Override
    public int updateOrder(Order order) {
        return orderDaoAPI.updateOrder(order);
    }

    @Override
    public Order getOrderByID(int orderID) {
        return orderDaoAPI.getOrderByID(orderID);
    }

    @Override
    public List<Order> getOrderByUserID(int userID) {
        return orderDaoAPI.getOrderByUserID(userID);
    }

    @Override
    public List<Order> getOrdersByDriverID(int driverID) {
        return orderDaoAPI.getOrdersByDriverID(driverID);
    }
}
