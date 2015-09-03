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
