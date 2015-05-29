package ge.taxistgela.dao;

import ge.taxistgela.bean.Review;
import ge.taxistgela.db.DBConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class ReviewDao implements ReviewDaoAPI, OperationCodes {

    private static final String ADD_REVIEW = "INSERT INTO Reviews " +
            "(orderID, orientationFlag, rating, description)" +
            "VALUES (?, ?, ?, ?)";

    private static final String UPDATE_REVIEW = "UPDATE Reviews SET " +
            "orderID = ?, orientationFlag = ?, rating = ?, description = ?" +
            "WHERE orderID = ?";

    private static final String GET_REVIEW_BY_ID = "SELECT * FROM Reviews WHERE reviewID = ?";

    private static final String GET_REVIEW_BY_USER_ID = "SELECT r.reviewID, r.orderID, r.orientationFlag, r.rating, r.description " +
            "FROM Reviews r INNER JOIN Orders o on o.userID = ?";

    private static final String GET_REVIEW_BY_DRIVER_ID = "SELECT r.reviewID, r.orderID, r.orientationFlag, r.rating, r.description " +
            "FROM Reviews r INNER JOIN Orders o on o.driverID = ?";

    @Override
    public int addReview(Review review) {
        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(ADD_REVIEW, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, review.getOrderID());
                stmt.setBoolean(2, review.isOrientationFlag());
                stmt.setDouble(3, review.getRating());
                stmt.setString(4, review.getDescription());

                System.out.println(stmt.toString());

                stmt.executeUpdate();

                try (ResultSet rslt = stmt.getGeneratedKeys()) {
                    if (rslt.next())
                        review.setReviewID(rslt.getInt(1));
                }
            }
        } catch (SQLException ex) {

        }
        return 0;
    }

    @Override
    public int updateReview(Review review) {
        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(UPDATE_REVIEW)) {
                stmt.setInt(1, review.getOrderID());
                stmt.setBoolean(2, review.isOrientationFlag());
                stmt.setDouble(3, review.getRating());
                stmt.setString(4, review.getDescription());
                stmt.setInt(5, review.getReviewID());

                System.out.println(stmt.toString());

                stmt.executeUpdate();
            }
        } catch (SQLException ex) {

        }
        return 0;
    }

    @Override
    public Review getReviewByID(int reviewID) {
        Review review = null;

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(GET_REVIEW_BY_ID)) {
                stmt.setInt(1, reviewID);

                System.out.println(stmt.toString());

                try (ResultSet rslt = stmt.executeQuery()) {
                    if (rslt.next())
                        review = fetchReview(rslt);
                }
            }
        } catch (SQLException ex) {

        }

        return review;
    }

    @Override
    public List<Review> getReviewByUserID(int userID) {
        List<Review> reviews = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(GET_REVIEW_BY_USER_ID)) {
                stmt.setInt(1, userID);

                System.out.println(stmt.toString());

                try (ResultSet rslt = stmt.executeQuery()) {
                    while (rslt.next())
                        reviews.add(fetchReview(rslt));
                }
            }
        } catch (SQLException ex) {

        }

        return reviews;
    }

    @Override
    public List<Review> getReviewByDriverID(int driverID) {
        List<Review> reviews = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(GET_REVIEW_BY_USER_ID)) {
                stmt.setInt(1, driverID);

                System.out.println(stmt.toString());

                try (ResultSet rslt = stmt.executeQuery()) {
                    while (rslt.next())
                        reviews.add(fetchReview(rslt));
                }
            }
        } catch (SQLException ex) {

        }

        return reviews;
    }

    /**
     * Factory method that generates Review object from ResultSet.
     *
     * @param rslt
     * @return Generated Review.
     * @throws SQLException
     */
    private Review fetchReview(ResultSet rslt) throws SQLException {
        Review review = new Review();

        review.setReviewID(rslt.getInt(1));
        review.setOrderID(rslt.getInt(2));
        review.setOrientationFlag(rslt.getBoolean(3));
        review.setRating(rslt.getDouble(4));
        review.setDescription(rslt.getString(5));

        return review;
    }
}
