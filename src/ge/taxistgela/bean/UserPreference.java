package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class UserPreference {
    private Integer userPreferenceID;
    private Double minimumDriverRating;
    private Boolean conditioning; // true unda, false kidia
    private Integer carYear; // meti an toli gamoshvebis wliani manqanebi
    private Integer timeLimit; // maximum ra droshi(Call time idan) unda mimiyvanos danishnulebis punqtshi
    private Integer passengersCount; // ramdeni kaci midian
    private Boolean wantsAlone;

    public UserPreference(Integer userPreferenceID, Double minimumDriverRating, Boolean conditioning, Integer carYear, Integer timeLimit, Integer passengersCount, Boolean wantsAlone) {
        setUserPreferenceID(userPreferenceID);
        setMinimumDriverRating(minimumDriverRating);
        setConditioning(conditioning);
        setCarYear(carYear);
        setTimeLimit(timeLimit);
        setPassengersCount(passengersCount);
        setWantsAlone(wantsAlone);
    }

    public UserPreference(UserPreference userPreference) {
        this(userPreference.getUserPreferenceID(), userPreference.getMinimumDriverRating(), userPreference.isConditioning(),
                userPreference.getCarYear(), userPreference.getTimeLimit(), userPreference.getPassengersCount(),
                userPreference.isWantsAlone());
    }

    public UserPreference() {
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserPreference)) return false;
        UserPreference o = (UserPreference) obj;
        return ExternalAlgorithms.equalsNull(getUserPreferenceID(), o.getUserPreferenceID()) &&
                ExternalAlgorithms.equalsNull(getMinimumDriverRating(), o.getMinimumDriverRating()) &&
                ExternalAlgorithms.equalsNull(isConditioning(), o.isConditioning()) &&
                ExternalAlgorithms.equalsNull(getCarYear(), o.getCarYear()) &&
                ExternalAlgorithms.equalsNull(getTimeLimit(), o.getTimeLimit()) &&
                ExternalAlgorithms.equalsNull(getPassengersCount(), o.getPassengersCount()) &&
                ExternalAlgorithms.equalsNull(isWantsAlone(), o.isWantsAlone());
    }

    @Override
    public int hashCode() {
        return getUserPreferenceID();
    }

    public Integer getUserPreferenceID() {
        return userPreferenceID;
    }

    public void setUserPreferenceID(Integer userPreferenceID) {
        this.userPreferenceID = userPreferenceID;
    }

    public Double getMinimumDriverRating() {
        return minimumDriverRating;
    }

    public void setMinimumDriverRating(Double minimumDriverRating) {
        this.minimumDriverRating = minimumDriverRating;
    }

    public Boolean isConditioning() {
        return conditioning;
    }

    public void setConditioning(Boolean conditioning) {
        this.conditioning = conditioning;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(Integer passengersCount) {
        this.passengersCount = passengersCount;
    }

    public Boolean isWantsAlone() {
        return wantsAlone;
    }

    public void setWantsAlone(Boolean wantsAlone) {
        this.wantsAlone = wantsAlone;
    }
}
