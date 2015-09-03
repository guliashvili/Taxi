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
            "orderID = ?, orientationFlag = ?, rating = ?, description = ? " +
            "WHERE reviewID = ?";

    private static final String GET_REVIEW_BY_ID = "SELECT * FROM Reviews WHERE reviewID = ?";

    private static final String GET_REVIEW_BY_USER_ID = "SELECT r.reviewID, r.orderID, r.orientationFlag, r.rating, r.description " +
            "FROM Reviews r INNER JOIN Orders o ON o.orderID=r.orderID AND o.userID = ?";

    private static final String GET_REVIEW_BY_DRIVER_ID = "SELECT r.reviewID, r.orderID, r.orientationFlag, r.rating, r.description " +
            "FROM Reviews r INNER JOIN Orders o ON o.orderID=r.orderID AND o.driverID = ?";

    private static final String GET_REVIEW_BY_COMPANY_ID = "SELECT r.reviewID, r.orderID, r.orientationFlag, r.rating, r.description " +
            "FROM Reviews r INNER JOIN Orders o ON o.orderID=r.orderID INNER JOIN Drivers d ON o.driverID = d.driverID AND d.companyID=?";

    private static final String GET_REVIEW_BY_ORDER_ID = "SELECT * " +
            "FROM Reviews WHERE Reviews.orderID = ?";

    private static final String GET_ALL_REVIEWS = "SELECT * FROM Reviews";

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
    public List<Review> getReviewByOrderID(int orderID) {
        List<Review> reviews = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(GET_REVIEW_BY_ORDER_ID))) {

                st.setInt(1, orderID);

                ExternalAlgorithms.debugPrintSelect("getReviewByOrderID \n" + st.toString());


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

    @Override
    public List<Review> getReviewByCompanyID(int companyID) {
        List<Review> reviews = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(GET_REVIEW_BY_COMPANY_ID))) {

                st.setInt(1, companyID);

                ExternalAlgorithms.debugPrintSelect("getReviewByCompanyID \n" + st.toString());


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
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();

        try (Connection conn = DBConnectionProvider.getConnection()) {
            try (PreparedStatementEnhanced st = new PreparedStatementEnhanced(conn.prepareStatement(GET_ALL_REVIEWS))) {
                ExternalAlgorithms.debugPrintSelect("getAllReviews \n" + st.toString());


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
