package ge.taxistgela.bean;

/**
 * Created by Alex on 5/25/2015.
 */
public class DriverPreferences {
    private int DriverPreferenceID;
    private double minimumUserRating;
    private double coefficientPer; //yovel wutze an mandzilze ra tarifi aqvs. amas mere shevxedevat riti gvirchevnia davtvalot

    public int getDriverPreferenceID() {
        return DriverPreferenceID;
    }

    public void setDriverPreferenceID(int driverPreferenceID) {
        DriverPreferenceID = driverPreferenceID;
    }

    public double getMinimumUserRating() {
        return minimumUserRating;
    }

    public void setMinimumUserRating(double minimumUserRating) {
        this.minimumUserRating = minimumUserRating;
    }

    public double getCoefficientPer() {
        return coefficientPer;
    }

    public void setCoefficientPer(double coefficientPer) {
        this.coefficientPer = coefficientPer;
    }
}
