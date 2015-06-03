package ge.taxistgela.bean;

/**
 * Created by Alex on 5/25/2015.
 */
public class UserPreference {
    private int userPreferenceID;
    private double minimumDriverRating;
    private boolean conditioning; // true unda, false kidia
    private int carYear; // meti an toli gamoshvebis wliani manqanebi
    private int timeLimit; // maximum ra droshi(Call time idan) unda mimiyvanos danishnulebis punqtshi
    private int passengersCount; // ramdeni kaci midian
    private boolean wantsAlone;

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  UserPreference)) return  false;
        UserPreference o = (UserPreference)obj;
        return  getUserPreferenceID() == o.getUserPreferenceID() &&
                getMinimumDriverRating() == o.getMinimumDriverRating() &&
                isConditioning() == o.isConditioning() &&
                getCarYear() == o.getCarYear() &&
                getTimeLimit() == o.getTimeLimit() &&
                getPassengersCount() == o.getPassengersCount() &&
                isWantsAlone() == o.isWantsAlone();
    }

    @Override
    public int hashCode() {
        return getUserPreferenceID();
    }

    public int getUserPreferenceID() {
        return userPreferenceID;
    }

    public void setUserPreferenceID(int userPreferenceID) {
        this.userPreferenceID = userPreferenceID;
    }

    public boolean isWantsAlone() {
        return wantsAlone;
    }

    public void setWantsAlone(boolean wantsAlone) {
        this.wantsAlone = wantsAlone;
    }

    public double getMinimumDriverRating() {
        return minimumDriverRating;
    }

    public void setMinimumDriverRating(double minimumDriverRating) {
        this.minimumDriverRating = minimumDriverRating;
    }

    public boolean isConditioning() {
        return conditioning;
    }

    public void setConditioning(boolean conditioning) {
        this.conditioning = conditioning;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }
}
