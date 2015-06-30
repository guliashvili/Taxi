package ge.taxistgela.bean;

import com.google.gson.annotations.Expose;
import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class DriverPreference {
    @Expose
    private Integer driverPreferenceID;
    @Expose
    private Double minimumUserRating;
    @Expose
    private Double coefficientPer; //yovel wutze an mandzilze ra tarifi aqvs. amas mere shevxedevat riti gvirchevnia davtvalot

    public DriverPreference(Integer driverPreferenceID, Double minimumUserRating, Double coefficientPer) {        setDriverPreferenceID(driverPreferenceID);
        setMinimumUserRating(minimumUserRating);
        setCoefficientPer(coefficientPer);
    }

    public DriverPreference(DriverPreference driverPreference) {
        this(driverPreference.getDriverPreferenceID(), driverPreference.getMinimumUserRating(), driverPreference.getCoefficientPer());
    }

    public DriverPreference() {
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DriverPreference)) return false;
        DriverPreference o = (DriverPreference) obj;
        return ExternalAlgorithms.equalsNull(getDriverPreferenceID(), o.getDriverPreferenceID()) &&
                ExternalAlgorithms.equalsNull(getMinimumUserRating(), o.getMinimumUserRating()) &&
                ExternalAlgorithms.equalsNull(getCoefficientPer(), o.getCoefficientPer());
    }

    @Override
    public int hashCode() {
        return getDriverPreferenceID();
    }

    public Integer getDriverPreferenceID() {
        return driverPreferenceID;
    }

    public void setDriverPreferenceID(Integer driverPreferenceID) {
        this.driverPreferenceID = driverPreferenceID;
    }

    public Double getMinimumUserRating() {
        return minimumUserRating;
    }

    public void setMinimumUserRating(Double minimumUserRating) {
        this.minimumUserRating = minimumUserRating;
    }

    public Double getCoefficientPer() {
        return coefficientPer;
    }

    public void setCoefficientPer(Double coefficientPer) {
        this.coefficientPer = coefficientPer;
    }
}
