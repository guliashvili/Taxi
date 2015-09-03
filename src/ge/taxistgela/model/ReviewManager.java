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
    public boolean addReview(Review review) {
        return reviewDaoAPI.addReview(review);
    }

    @Override
    public boolean updateReview(Review review) {
        return reviewDaoAPI.updateReview(review);
    }

    @Override
    public Review getReviewByID(Integer reviewID) {
        return reviewDaoAPI.getReviewByID(reviewID);
    }

    @Override
    public List<Review> getReviewByUserID(Integer userID) {
        return reviewDaoAPI.getReviewByUserID(userID);
    }

    @Override
    public List<Review> getReviewByDriverID(Integer driverID) {
        return reviewDaoAPI.getReviewByDriverID(driverID);
    }

    @Override
    public List<Review> getReviewByCompanyID(Integer companyID) {
        return reviewDaoAPI.getReviewByCompanyID(companyID);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewDaoAPI.getAllReviews();
    }
}
