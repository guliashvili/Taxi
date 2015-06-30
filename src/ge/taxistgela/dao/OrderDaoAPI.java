package ge.taxistgela.dao;

import ge.taxistgela.bean.Order;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public interface OrderDaoAPI {

    List<Order> getOrdersByCompanyID(Integer companyID);

    /**
     * Adds new order into database (still pending status).
     *
     * @param order
     * @return operationCode
     */
    boolean addOrder(Order order);

    /**
     * Updates the certain order with the new data. (status active).
     *
     * @param order
     * @return operationCode
     */
    boolean updateOrder(Order order);

    /**
     * Returns Order selected by the certain orderID.
     *
     * @param orderID
     * @return Order generated from database.
     */
    Order getOrderByID(int orderID);

    /**
     * Returns list of order selected by the certain userID.
     *
     * @param userID
     * @return List of orders.
     */
    List<Order> getOrderByUserID(int userID);

    /**
     * Returns list of orders selected by the certain driverID.
     *
     * @param driverID
     * @return List of orders.
     */
    List<Order> getOrdersByDriverID(int driverID);

    List<Order> getAllOrders();
}
