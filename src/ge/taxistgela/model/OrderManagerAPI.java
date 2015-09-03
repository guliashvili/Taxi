/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

package ge.taxistgela.model;

import ge.taxistgela.bean.Order;
import ge.taxistgela.dao.OrderDaoAPI;

import java.util.List;

/**
 * Created by GIO on 6/5/2015.
 */
public abstract class OrderManagerAPI {
    OrderDaoAPI orderDaoAPI;
    public OrderManagerAPI(OrderDaoAPI orderDaoAPI){
        this.orderDaoAPI = orderDaoAPI;
    }
    /**
     * Adds new order into database (still pending status).
     *
     * @param order
     * @return operationCode
     */
    public abstract boolean addOrder(Order order);

    public abstract List<Order> getOrdersByCompanyID(Integer companyID);

    public abstract List<Order> getOpenOrder(int userID);

    /**
     * Updates the certain order with the new data. (status active).
     *
     * @param order
     * @return operationCode
     */
    public abstract boolean updateOrder(Order order);

    /**
     * Returns Order selected by the certain orderID.
     *
     * @param orderID
     * @return Order generated from database.
     */
    public abstract Order getOrderByID(Integer orderID);

    /**
     * Returns list of order selected by the certain userID.
     *
     * @param userID
     * @return List of orders.
     */
    public abstract List<Order> getOrderByUserID(Integer userID);

    /**
     * Returns list of orders selected by the certain driverID.
     *
     * @param driverID
     * @return List of orders.
     */
    public abstract List<Order> getOrdersByDriverID(Integer driverID);

    public abstract List<Order> getAllOrders();
}
