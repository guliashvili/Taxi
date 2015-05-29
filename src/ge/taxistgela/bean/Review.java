package ge.taxistgela.bean;

/**
 * Created by Alex on 5/25/2015.
 */
public class Review {
    private int reviewID;
    private int orderID;
    private boolean orientationFlag; // if its true then User scored driver else driver scored user
    private double rating;
    private String description;

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
