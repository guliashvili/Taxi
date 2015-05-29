package ge.taxistgela.dao;

import ge.taxistgela.bean.Location;
import ge.taxistgela.bean.Order;
import ge.taxistgela.db.DBConnectionProvider;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class OrderDao implements OrderDaoAPI, OperationCodes {

    private static final String ADD_ORDER = "INSERT INTO Orders " +
            "(userID, driverID, numPassengers, startLocation_long, startLocation_lat, endLocation_long, " +
            "endLocation_lat, startTime, endTime, paymentAmount, callTime) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_ORDER = "UPDATE Orders SET " +
            "userID = ?, driverID = ?, numPassengers = ?, startLocation_long = ?, startLocation_lat = ?, " +
            "endLocation_long = ?, endLocation_lat = ?, startTime = ?, endTime = ?, paymentAmount = ?, callTime = ?" +
            "WHERE orderID = ?";

    private static final String GET_ORDER_BY_ID = "SELECT * FROM Orders WHERE orderID = ?";

    private static final String GET_ORDER_BY_USER_ID = "SELECT * FROM Orders WHERE userID = ?";

    private static final String GET_ORDER_BY_Driver_ID = "SELECT * FROM Orders WHERE driverID = ?";

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public int addOrder(Order order) throws ParseException {
        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(ADD_ORDER)) {
                stmt.setInt(1, order.getUserID());
                stmt.setInt(2, order.getDriverID());
                stmt.setInt(3, order.getNumPassengers());
                stmt.setBigDecimal(4, order.getStartLocation().getLongitute());
                stmt.setBigDecimal(5, order.getStartLocation().getLatitude());
                stmt.setBigDecimal(6, order.getEndLocation().getLongitute());
                stmt.setBigDecimal(7, order.getEndLocation().getLatitude());
                stmt.setString(8, simpleDateFormat.format(order.getStartTime()));
                stmt.setString(9, simpleDateFormat.format(order.getEndTime()));
                stmt.setBigDecimal(10, order.getPaymentAmount());
                stmt.setString(11, simpleDateFormat.format(order.getCallTime()));

                stmt.executeUpdate();
            }
        } catch (SQLException ex) {

        }
        return 0;
    }

    @Override
    public int updateOrder(Order order) {
        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(UPDATE_ORDER, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, order.getUserID());
                stmt.setInt(2, order.getDriverID());
                stmt.setInt(3, order.getNumPassengers());
                stmt.setBigDecimal(4, order.getStartLocation().getLongitute());
                stmt.setBigDecimal(5, order.getStartLocation().getLatitude());
                stmt.setBigDecimal(6, order.getEndLocation().getLongitute());
                stmt.setBigDecimal(7, order.getEndLocation().getLatitude());
                stmt.setString(8, simpleDateFormat.format(order.getStartTime()));
                stmt.setString(9, simpleDateFormat.format(order.getEndTime()));
                stmt.setBigDecimal(10, order.getPaymentAmount());
                stmt.setString(11, simpleDateFormat.format(order.getCallTime()));
                stmt.setInt(12, order.getOrderID());

                stmt.executeUpdate();

                try (ResultSet rslt = stmt.getGeneratedKeys()) {
                    if (rslt.next())
                        order.setOrderID(rslt.getInt(1));
                }
            }
        } catch (SQLException ex) {

        }
        return 0;
    }

    @Override
    public Order getOrderByID(int orderID) {
        Order order = null;

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(GET_ORDER_BY_ID)) {
                stmt.setInt(1, orderID);

                try (ResultSet rslt = stmt.executeQuery()) {
                    if (rslt.next())
                        order = fetchOrder(rslt);
                }
            }
        } catch (SQLException ex) {

        } catch (ParseException ex) {

        }

        return order;
    }

    @Override
    public List<Order> getOrderByUserID(int userID) {
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(GET_ORDER_BY_USER_ID)) {
                stmt.setInt(1, userID);

                try (ResultSet rslt = stmt.executeQuery()) {
                    while (rslt.next())
                        orders.add(fetchOrder(rslt));
                }
            }
        } catch (SQLException ex) {

        } catch (ParseException ex) {

        }

        return orders;
    }

    @Override
    public List<Order> getOrdersByDriverID(int driverID) {
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(GET_ORDER_BY_Driver_ID)) {
                stmt.setInt(1, driverID);

                try (ResultSet rslt = stmt.executeQuery()) {
                    while (rslt.next())
                        orders.add(fetchOrder(rslt));
                }
            }
        } catch (SQLException ex) {

        } catch (ParseException ex) {

        }

        return orders;
    }

    /**
     * Factory method that generates Order object from ResultSet.
     *
     * @param rslt
     * @return Generated Order.
     * @throws SQLException
     * @throws ParseException
     */
    private static Order fetchOrder(ResultSet rslt) throws SQLException, ParseException {
        Order order = new Order();

        order.setOrderID(rslt.getInt(1));
        order.setUserID(rslt.getInt(2));
        order.setDriverID(rslt.getInt(3));
        order.setNumPassengers(rslt.getInt(4));
        order.setStartLocation(new Location(rslt.getBigDecimal(6), rslt.getBigDecimal(5)));
        order.setEndLocation(new Location(rslt.getBigDecimal(8), rslt.getBigDecimal(7)));
        order.setStartTime(simpleDateFormat.parse(rslt.getString(9)));
        order.setEndTime(simpleDateFormat.parse(rslt.getString(10)));
        order.setPaymentAmount(rslt.getBigDecimal(11));
        order.setCallTime(simpleDateFormat.parse(rslt.getString(12)));

        return order;
    }
}
