package ge.taxistgela.dao;

import ge.taxistgela.bean.Review;
import ge.taxistgela.db.DBConnectionProvider;
import ge.taxistgela.helper.ExternalAlgorithms;

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
            "FROM Reviews r INNER JOIN Orders o ON o.userID = ?";

    private static final String GET_REVIEW_BY_DRIVER_ID = "SELECT r.reviewID, r.orderID, r.orientationFlag, r.rating, r.description " +
            "FROM Reviews r INNER JOIN Orders o ON o.driverID = ?";

    @Override
    public int addReview(Review review) {
        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(ADD_REVIEW, Statement.RETURN_GENERATED_KEYS)) {
                st.setInt(1, review.getOrderID());
                st.setBoolean(2, review.isOrientationFlag());
                st.setDouble(3, review.getRating());
                st.setString(4, review.getDescription());

                ExternalAlgorithms.debugPrintSelect("addReview \n" + st.toString());

                st.executeUpdate();

                try (ResultSet rslt = st.getGeneratedKeys()) {
                    if (rslt.next())
                        review.setReviewID(rslt.getInt(1));
                }
            }
        } catch (SQLException e) {

            ExternalAlgorithms.debugPrint(e);
        }
        return 0;
    }

    @Override
    public int updateReview(Review review) {
        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(UPDATE_REVIEW)) {
                st.setInt(1, review.getOrderID());
                st.setBoolean(2, review.isOrientationFlag());
                st.setDouble(3, review.getRating());
                st.setString(4, review.getDescription());
                st.setInt(5, review.getReviewID());

                ExternalAlgorithms.debugPrintSelect("updateReview \n" + st.toString());

                st.executeUpdate();
            }
        } catch (SQLException e) {

            ExternalAlgorithms.debugPrint(e);
        }
        return 0;
    }

    @Override
    public Review getReviewByID(int reviewID) {
        Review review = null;

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(GET_REVIEW_BY_ID)) {
                st.setInt(1, reviewID);

                ExternalAlgorithms.debugPrintSelect("getReviewByID \n" + st.toString());

                try (ResultSet rslt = st.executeQuery()) {
                    if (rslt.next())
                        review = fetchReview(rslt);
                }
            }
        } catch (SQLException e) {

            ExternalAlgorithms.debugPrint(e);
        }

        return review;
    }

    @Override
    public List<Review> getReviewByUserID(int userID) {
        List<Review> reviews = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(GET_REVIEW_BY_USER_ID)) {
                st.setInt(1, userID);

                ExternalAlgorithms.debugPrintSelect("getReviewByUserID \n" + st.toString());


                try (ResultSet rslt = st.executeQuery()) {
                    while (rslt.next())
                        reviews.add(fetchReview(rslt));
                }
            }
        } catch (SQLException e) {

            ExternalAlgorithms.debugPrint(e);
        }

        return reviews;
    }

    @Override
    public List<Review> getReviewByDriverID(int driverID) {
        List<Review> reviews = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(GET_REVIEW_BY_DRIVER_ID)) {
                st.setInt(1, driverID);

                ExternalAlgorithms.debugPrintSelect("getReviewByDriverID \n" + st.toString());


                try (ResultSet rslt = st.executeQuery()) {
                    while (rslt.next())
                        reviews.add(fetchReview(rslt));
                }
            }
        } catch (SQLException e) {

            ExternalAlgorithms.debugPrint(e);
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
