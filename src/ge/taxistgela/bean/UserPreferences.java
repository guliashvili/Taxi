package ge.taxistgela.bean;

/**
 * Created by Alex on 5/25/2015.
 */
public class UserPreferences {
    private double minimumDriverRaiting;
    private boolean conditioning; // true unda, false kidia
    private int carYear; // meti an toli gamoshvebis wliani manqanebi
    private int timeLimit; // maximum ra droshi(Call time idan) unda mimiyvanos danishnulebis punqtshi
    private int passengersCount; // ramdeni kaci midian
    private boolean wantsAlone;

    public boolean isWantsAlone() {
        return wantsAlone;
    }

    public void setWantsAlone(boolean wantsAlone) {
        this.wantsAlone = wantsAlone;
    }

    public double getMinimumDriverRaiting() {
        return minimumDriverRaiting;
    }

    public void setMinimumDriverRaiting(double minimumDriverRaiting) {
        this.minimumDriverRaiting = minimumDriverRaiting;
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
