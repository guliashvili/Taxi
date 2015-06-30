package ge.taxistgela.dao;

import ge.taxistgela.bean.Location;
import ge.taxistgela.bean.Order;
import ge.taxistgela.db.DBConnectionProvider;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.helper.PreparedStatementEnhanced;
import ge.taxistgela.helper.ResultSetEnhanced;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class OrderDao implements OrderDaoAPI {

    private static final String ADD_ORDER = "INSERT INTO Orders " +
            "(userID, driverID, numPassengers, startLocation_long, startLocation_lat, endLocation_long, " +
            "endLocation_lat, startTime, endTime, paymentAmount, callTime,revokedByUser,revokedByDriver) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

    private static final String UPDATE_ORDER = "UPDATE Orders SET " +
            "userID = ?, driverID = ?, numPassengers = ?, startLocation_long = ?, startLocation_lat = ?, " +
            "endLocation_long = ?, endLocation_lat = ?, startTime = ?, endTime = ?, paymentAmount = ?, callTime = ?, " +
            "revokedByUser=?, revokedByDriver=? " +
            "WHERE orderID = ?";
    private static final String GET_ORDER_BY_COMPANY_ID = "SELECT * FROM orders INNER JOIN drivers " +
            " ON drivers.driverID=orders.driverID AND drivers.companyID=?";

    private static final String GET_ORDER_BY_ID = "SELECT * FROM Orders WHERE orderID = ?";

    private static final String GET_ORDER_BY_USER_ID = "SELECT * FROM Orders WHERE userID = ?";

    private static final String GET_ORDER_BY_Driver_ID = "SELECT * FROM Orders WHERE driverID = ?";

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Factory method that generates Order object from ResultSet.
     *
     * @param rslt
     * @return Generated Order.
     * @throws SQLException
     * @throws ParseException
     */
    private static Order fetchOrder(ResultSetEnhanced rslt) {
        Order order = new Order();
        try {
            order.setOrderID(rslt.getInt("Orders.orderID"));
            order.setUserID(rslt.getInt("Orders.userID"));
            order.setDriverID(rslt.getInt("Orders.driverID"));
            order.setNumPassengers(rslt.getInt("Orders.numPassengers"));
            order.setStartLocation(new Location(
                    rslt.getDouble("Orders.startLocation_lat"),
                    rslt.getDouble("Orders.startLocation_long")));
            order.setEndLocation(new Location(
                    rslt.getDouble("Orders.endLocation_lat"),
                    rslt.getDouble("Orders.endLocation_long")));
            order.setStartTime(simpleDateFormat.parse(rslt.getString("Orders.startTime")));
            order.setEndTime(simpleDateFormat.parse(rslt.getString("Orders.endTime")));
            order.setPaymentAmount(rslt.getDouble("Orders.paymentAmount"));
            order.setCallTime(simpleDateFormat.parse(rslt.getString("Orders.callTime")));
            order.setRevokedByUser(rslt.getBoolean("Orders.revokedByUser"));
            order.setRevokedByDriver(rslt.getBoolean("Orders.revokedByDriver"));
        } catch (SQLException e) {
            order = null;
            ExternalAlgorithms.debugPrint(e);
        } catch (ParseException e) {
            order = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return order;
    }

    private boolean setStrings(PreparedStatementEnhanced st, Order order, boolean update) {
        boolean errorCode = false;
        try {
            st.setInt(1, order.getUserID());
            st.setInt(2, order.getDriverID());
            st.setInt(3, order.getNumPassengers());
            st.setDouble(4, order.getStartLocation().getLongitude());
            st.setDouble(5, order.getStartLocation().getLatitude());
            st.setDouble(6, order.getEndLocation().getLongitude());
            st.setDouble(7, order.getEndLocation().getLatitude());
            st.setString(8, (order.getStartTime() == null) ? null : simpleDateFormat.format(order.getStartTime()));
            st.setString(9, (order.getEndTime() == null) ? null : simpleDateFormat.format(order.getEndTime()));
            st.setDouble(10, order.getPaymentAmount());
            st.setString(11, (order.getCallTime() == null) ? null : simpleDateFormat.format(order.getCallTime()));
            st.setBoolean(12, order.getRevokedByUser());
            st.setBoolean(13, order.getRevokedByDriver());
            if (update)
                st.setInt(14, order.getOrderID());
        } catch (SQLException e) {
            ExternalAlgorithms.debugPrint(e);
            errorCode = true;
        }
        return errorCode;
    }

    @Override
    public boolean addOrder(Order order) {
        boolean errorCode = false;
        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS))) {
                errorCode |= setStrings(st, order, false);

                ExternalAlgorithms.debugPrintSelect("addOrder \n" + st.toString());

                st.executeUpdate();

                try (ResultSetEnhanced rslt = st.getGeneratedKeys()) {
                    if (rslt.next())
                        order.setOrderID(rslt.getInt(1));
                }
            }
        } catch (SQLException e) {
            ExternalAlgorithms.debugPrint(e);
            errorCode = true;
        }

        return errorCode;
    }

    @Override
    public boolean updateOrder(Order order) {
        boolean errorCode = false;
        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(UPDATE_ORDER))) {
                errorCode |= setStrings(st, order, true);

                ExternalAlgorithms.debugPrintSelect("updateOrder \n" + st.toString());

                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public Order getOrderByID(int orderID) {
        Order order = null;

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(GET_ORDER_BY_ID))) {
                st.setInt(1, orderID);

                ExternalAlgorithms.debugPrintSelect("getOrderByID \n" + st.toString());

                try (ResultSetEnhanced rslt = st.executeQuery()) {
                    if (rslt.next())
                        order = fetchOrder(rslt);
                }
            }
        } catch (SQLException e) {
            order = null;
            ExternalAlgorithms.debugPrint(e);
        }
        return order;
    }

    @Override
    public List<Order> getOrderByUserID(int userID) {
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(GET_ORDER_BY_USER_ID))) {
                st.setInt(1, userID);

                ExternalAlgorithms.debugPrintSelect("getOrderByUserID \n" + st.toString());

                try (ResultSetEnhanced rslt = st.executeQuery()) {
                    while (rslt.next())
                        orders.add(fetchOrder(rslt));
                }
            }
        } catch (SQLException e) {
            orders = null;
            ExternalAlgorithms.debugPrint(e);
        }

        return orders;
    }

    @Override
    public List<Order> getOrdersByCompanyID(Integer companyID) {
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(GET_ORDER_BY_COMPANY_ID))) {
                st.setInt(1, companyID);

                ExternalAlgorithms.debugPrintSelect("getOrdersByCompanyID \n" + st.toString());

                try (ResultSetEnhanced rslt = st.executeQuery()) {
                    while (rslt.next())
                        orders.add(fetchOrder(rslt));
                }
            }
        } catch (SQLException e) {
            orders = null;
            ExternalAlgorithms.debugPrint(e);
        }

        return orders;
    }

    @Override
    public List<Order> getOrdersByDriverID(int driverID) {
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(GET_ORDER_BY_Driver_ID))) {
                st.setInt(1, driverID);

                ExternalAlgorithms.debugPrintSelect("getOrdersByDriverID \n" + st.toString());

                try (ResultSetEnhanced rslt = st.executeQuery()) {
                    while (rslt.next())
                        orders.add(fetchOrder(rslt));
                }
            }
        } catch (SQLException e) {
            orders = null;
            ExternalAlgorithms.debugPrint(e);
        }

        return orders;
    }
}
