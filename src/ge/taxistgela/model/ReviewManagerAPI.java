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
