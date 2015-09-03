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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GIO on 6/5/2015.
 */
public class OrderManager extends OrderManagerAPI {
    public OrderManager(OrderDaoAPI orderDaoAPI){super(orderDaoAPI);}

    public List<Order> getOpenOrder(int userID) {
        List<Order> ls = orderDaoAPI.getOrderByUserID(userID);
        List<Order> ret = new ArrayList<>();
        for (Order elem : ls) {
            if (!(elem.getRevokedByDriver() ||
                    elem.getRevokedByUser() ||
                    elem.getEndTime() != null))
                ret.add(elem);
        }
        return ret;

    }

    @Override
    public boolean addOrder(Order order) {
        if (getOpenOrder(order.getUserID()).size() > 0) return true;
        return orderDaoAPI.addOrder(order);
    }

    @Override
    public List<Order> getOrdersByCompanyID(Integer companyID) {
        return orderDaoAPI.getOrdersByCompanyID(companyID);
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

    @Override
    public List<Order> getAllOrders() {
        return orderDaoAPI.getAllOrders();
    }
}
