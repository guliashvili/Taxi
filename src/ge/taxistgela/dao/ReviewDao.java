package ge.taxistgela.dao;

import ge.taxistgela.bean.Review;
import ge.taxistgela.db.DBConnectionProvider;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.helper.PreparedStatementEnhanced;
import ge.taxistgela.helper.ResultSetEnhanced;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class ReviewDao implements ReviewDaoAPI {

    private static final String ADD_REVIEW = "INSERT INTO Reviews " +
            "(orderID, orientationFlag, rating, description)" +
            "VALUES (?, ?, ?, ?)";

    private static final String UPDATE_REVIEW = "UPDATE Reviews SET " +
            "orderID = ?, orientationFlag = ?, rating = ?, description = ?" +
            "WHERE orderID = ?";

    private static final String GET_REVIEW_BY_ID = "SELECT * FROM Reviews WHERE reviewID = ?";

    private static final String GET_REVIEW_BY_USER_ID = "SELECT r.reviewID, r.orderID, r.orientationFlag, r.rating, r.description " +
            "FROM Reviews r INNER JOIN Orders o ON o.orderID=r.orderID AND o.userID = ?";

    private static final String GET_REVIEW_BY_DRIVER_ID = "SELECT r.reviewID, r.orderID, r.orientationFlag, r.rating, r.description " +
            "FROM Reviews r INNER JOIN Orders o ON o.orderID=r.orderID AND o.driverID = ?";


    private boolean setStrings(PreparedStatementEnhanced st, Review review, boolean update) {
        boolean errorCode = false;
        try {
            st.setInt(1, review.getOrderID());

            st.setBoolean(2, review.isOrientationFlag());
            st.setDouble(3, review.getRating());
            st.setString(4, review.getDescription());
            if (update)
                st.setInt(5, review.getReviewID());
        } catch (SQLException e) {
            errorCode = true;
        }
        return errorCode;
    }

    @Override
    public boolean addReview(Review review) {
        boolean errorCode = false;
        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st =
                         new PreparedStatementEnhanced(conn.prepareStatement(ADD_REVIEW, Statement.RETURN_GENERATED_KEYS))) {

                errorCode |= setStrings(st, review, false);

                ExternalAlgorithms.debugPrintSelect("addReview \n" + st.toString());

                st.executeUpdate();

                try (ResultSetEnhanced rslt = st.getGeneratedKeys()) {
                    if (rslt.next())
                        review.setReviewID(rslt.getInt(1));
                }
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public boolean updateReview(Review review) {
        boolean errorCode = false;
        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(UPDATE_REVIEW))) {

                errorCode |= setStrings(st, review, true);


                ExternalAlgorithms.debugPrintSelect("updateReview \n" + st.toString());

                st.executeUpdate();
            }
        } catch (SQLException e) {
            errorCode = true;
            ExternalAlgorithms.debugPrint(e);
        }
        return errorCode;
    }

    @Override
    public Review getReviewByID(int reviewID) {
        Review review = null;

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(GET_REVIEW_BY_ID))) {
                
                st.setInt(1, reviewID);

                ExternalAlgorithms.debugPrintSelect("getReviewByID \n" + st.toString());

                try (ResultSetEnhanced rslt = st.executeQuery()) {
                    if (rslt.next())
                        review = fetchReview(rslt);
                }
            }
        } catch (SQLException e) {
            review = null;
            ExternalAlgorithms.debugPrint(e);
        }

        return review;
    }

    @Override
    public List<Review> getReviewByUserID(int userID) {
        List<Review> reviews = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(GET_REVIEW_BY_USER_ID))) {
                
                st.setInt(1, userID);

                ExternalAlgorithms.debugPrintSelect("getReviewByUserID \n" + st.toString());


                try (ResultSetEnhanced rslt = st.executeQuery()) {
                    while (rslt.next())
                        reviews.add(fetchReview(rslt));
                }
            }
        } catch (SQLException e) {
            reviews = null;
            ExternalAlgorithms.debugPrint(e);
        }

        return reviews;
    }

    @Override
    public List<Review> getReviewByDriverID(int driverID) {
        List<Review> reviews = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(GET_REVIEW_BY_DRIVER_ID))) {
                
                st.setInt(1, driverID);

                ExternalAlgorithms.debugPrintSelect("getReviewByDriverID \n" + st.toString());


                try (ResultSetEnhanced rslt = st.executeQuery()) {
                    while (rslt.next())
                        reviews.add(fetchReview(rslt));
                }
            }
        } catch (SQLException e) {
            reviews = null;
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
    private Review fetchReview(ResultSetEnhanced rslt) {
        Review review = new Review();
        try {
            review.setReviewID(rslt.getInt(1));
            review.setOrderID(rslt.getInt(2));
            review.setOrientationFlag(rslt.getBoolean(3));
            review.setRating(rslt.getDouble(4));
            review.setDescription(rslt.getString(5));
        } catch (SQLException e) {
            review = null;
        }
        return review;
    }
}
