package ge.taxistgela.bean;

/**
 * Created by Alex on 5/25/2015.
 */
public class DriverPreferences {
    private double rating;
    private int timeLimit; // maximum ra dros daxarjavs klienttan misvlaze
    private double coefficientPer; //yovel wutze an mandzilze ra tarifi aqvs. amas mere shevxedevat riti gvirchevnia davtvalot

    public double getCoefficientPer() {
        return coefficientPer;
    }

    public void setCoefficientPer(double coefficientPer) {
        this.coefficientPer = coefficientPer;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

}
