package ge.taxistgela.bean;

/**
 * Created by Alex on 5/25/2015.
 */
public class Review {
    private int orderID;
    private boolean orientationFlag; // if its true then User scored driver else driver scored user
    private double timeRating;
    private double comfortRating;
    private String description;

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

    public double getTimeRating() {
        return timeRating;
    }

    public void setTimeRating(double timeRating) {
        this.timeRating = timeRating;
    }

    public double getComfortRating() {
        return comfortRating;
    }

    public void setComfortRating(double comfortRating) {
        this.comfortRating = comfortRating;
    }

}
