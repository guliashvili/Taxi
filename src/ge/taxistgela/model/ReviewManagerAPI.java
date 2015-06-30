package ge.taxistgela.model;

import ge.taxistgela.bean.Review;
import ge.taxistgela.dao.ReviewDaoAPI;

import java.util.List;

/**
 * Created by GIO on 6/4/2015.
 */
public abstract class ReviewManagerAPI {
    ReviewDaoAPI reviewDaoAPI;
    public  ReviewManagerAPI(ReviewDaoAPI reviewDaoAPI){this.reviewDaoAPI = reviewDaoAPI;}
    /**
     * Adds new review into database.
     *
     * @param review
     * @return operationCode
     */
    public abstract boolean addReview(Review review);

    /**
     * Updates the certain review with the new data.
     *
     * @param review
     * @return operationCode
     */
    public abstract boolean updateReview(Review review);

    /**
     * Returns Review selected by the certain reviewID.
     *
     * @param reviewID
     * @return
     */
    public abstract Review getReviewByID(Integer reviewID);

    /**
     * Returns list of reviews selected by the certain userID.
     *
     * @param userID
     * @return List of reviews.
     */
    public abstract List<Review> getReviewByUserID(Integer userID);

    /**
     * Returns list of reviews selected by the certain driverID.
     *
     * @param driverID
     * @return List of reviews.
     */
    public abstract List<Review> getReviewByDriverID(Integer driverID);

    /**
     * Returns list of reviews selected by the certain companyID.
     *
     * @param companyID
     * @return List of reviews.
     */
    public abstract List<Review> getReviewByCompanyID(Integer companyID);

    public abstract List<Review> getAllReviews();
}
