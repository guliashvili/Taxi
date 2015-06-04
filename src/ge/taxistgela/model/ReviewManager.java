package ge.taxistgela.model;

import ge.taxistgela.bean.Review;
import ge.taxistgela.dao.ReviewDaoAPI;

import java.util.List;

/**
 * Created by GIO on 6/5/2015.
 */
public class ReviewManager extends  ReviewManagerAPI {
    public ReviewManager(ReviewDaoAPI reviewDaoAPI){super(reviewDaoAPI);}

    @Override
    public int addReview(Review review) {
        return reviewDaoAPI.addReview(review);
    }

    @Override
    public int updateReview(Review review) {
        return reviewDaoAPI.updateReview(review);
    }

    @Override
    public Review getReviewByID(int reviewID) {
        return reviewDaoAPI.getReviewByID(reviewID);
    }

    @Override
    public List<Review> getReviewByUserID(int userID) {
        return reviewDaoAPI.getReviewByUserID(userID);
    }

    @Override
    public List<Review> getReviewByDriverID(int driverID) {
        return reviewDaoAPI.getReviewByDriverID(driverID);
    }
}
