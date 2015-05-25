package ge.taxistgela.dao;

import ge.taxistgela.bean.Review;

import java.util.List;

/**
 * Created by Alex on 5/25/2015.
 */
public class ReviewDao implements ReviewDaoAPI, OperationCodes {

    @Override
    public int addReview(Review review) {
        return 0;
    }

    @Override
    public int updateReview(Review review) {
        return 0;
    }

    @Override
    public Review getReviewByID(int reviewID) {
        return null;
    }

    @Override
    public List<Review> getReviewByUserID(int userID) {
        return null;
    }

    @Override
    public List<Review> getReviewByDriverID(int driverID) {
        return null;
    }
}
