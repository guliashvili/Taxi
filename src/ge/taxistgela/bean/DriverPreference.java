package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class DriverPreference {
    private int DriverPreferenceID;
    private double minimumUserRating;
    private double coefficientPer; //yovel wutze an mandzilze ra tarifi aqvs. amas mere shevxedevat riti gvirchevnia davtvalot

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  DriverPreference)) return  false;
        DriverPreference o = (DriverPreference)obj;
        return  getDriverPreferenceID() == o.getDriverPreferenceID() &&
                getMinimumUserRating() == o.getMinimumUserRating() &&
                getCoefficientPer() == o.getCoefficientPer();
    }

    @Override
    public int hashCode() {
        return getDriverPreferenceID();
    }

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
