package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class Review {
    private int reviewID;
    private int orderID;
    private boolean orientationFlag; // if its true then User scored driver else driver scored user
    private double rating;
    private String description;

    public Review(int reviewID, int orderID, boolean orientationFlag, double rating, String description) {
        setReviewID(reviewID);
        setOrderID(orderID);
        setOrientationFlag(orientationFlag);
        setRating(rating);
        setDescription(description);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Review)) return  false;
        Review o = (Review)obj;
        return getReviewID() == o.getReviewID() &&
                getOrderID() == o.getOrderID() &&
                isOrientationFlag() == o.isOrientationFlag() &&
                getRating() == o.getRating() &&
                ExternalAlgorithms.equalsNull(getDescription(),o.getDescription());
    }

    @Override
    public int hashCode() {
        return getReviewID();
    }


    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOrientationFlag() {
        return orientationFlag;
    }

    public void setOrientationFlag(boolean orientationFlag) {
        this.orientationFlag = orientationFlag;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
