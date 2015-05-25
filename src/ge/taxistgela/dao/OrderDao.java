package ge.taxistgela.dao;

import ge.taxistgela.bean.Order;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class OrderDao implements OrderDaoAPI, OperationCodes {

    @Override
    public int addOrder(Order order) {
        return 0;
    }

    @Override
    public int updateOrder(Order order) {
        return 0;
    }

    @Override
    public Order getOrderByID(int orderID) {
        return null;
    }

    @Override
    public List<Order> getOrderByUserID(int userID) {
        return null;
    }

    @Override
    public List<Order> getOrdersByDriverID(int driverID) {
        return null;
    }
}
