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

package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class Review {
    private Integer reviewID;
    private Integer orderID;
    private Boolean orientationFlag; // if its true then User scored driver else driver scored user
    private Double rating;
    private String description;

    public Review() {
    }

    public Review(Integer reviewID, Integer orderID, Boolean orientationFlag, Double rating, String description) {
        setReviewID(reviewID);
        setOrderID(orderID);
        setOrientationFlag(orientationFlag);
        setRating(rating);
        setDescription(description);
    }

    public Review(Review review) {
        this(review.getReviewID(), review.getOrderID(), review.isOrientationFlag(), review.getRating(), review.getDescription());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Review)) return false;
        Review o = (Review) obj;
        return ExternalAlgorithms.equalsNull(getReviewID(), o.getReviewID()) &&
                ExternalAlgorithms.equalsNull(getOrderID(), o.getOrderID()) &&
                ExternalAlgorithms.equalsNull(isOrientationFlag(), o.isOrientationFlag()) &&
                ExternalAlgorithms.equalsNull(getRating(), o.getRating()) &&
                ExternalAlgorithms.equalsNull(getDescription(), o.getDescription());
    }

    @Override
    public int hashCode() {
        return getReviewID();
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Boolean isOrientationFlag() {
        return orientationFlag;
    }

    public void setOrientationFlag(Boolean orientationFlag) {
        this.orientationFlag = orientationFlag;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
