package ge.taxistgela.dao;

import ge.taxistgela.bean.Review;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public interface ReviewDaoAPI {

    /**
     * Adds new review into database.
     *
     * @param review
     * @return operationCode
     */
    boolean addReview(Review review);

    /**
     * Updates the certain review with the new data.
     *
     * @param review
     * @return operationCode
     */
    boolean updateReview(Review review);

    /**
     * Returns Review selected by the certain reviewID.
     *
     * @param reviewID
     * @return
     */
    Review getReviewByID(int reviewID);

    /**
     * Returns list of reviews selected by the certain userID.
     *
     * @param userID
     * @return List of reviews.
     */
    List<Review> getReviewByUserID(int userID);

    /**
     * Returns list of reviews selected by the certain driverID.
     *
     * @param driverID
     * @return List of reviews.
     */
    List<Review> getReviewByDriverID(int driverID);

    List<Review> getReviewByOrderID(int orderID);

    List<Review> getReviewByCompanyID(int companyID);

    List<Review> getAllReviews();
}
