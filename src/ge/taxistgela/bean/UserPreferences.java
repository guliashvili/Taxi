package ge.taxistgela.bean;

/**
 * Created by Alex on 5/25/2015.
 */
public class UserPreferences {
    private double rating;
    private boolean conditioning;
    private int carYear; // meti an toli gamoshvebis wliani manqanebi
    private int timeLimit; // maximum ra dro daelodeba
    private int passengersCount; // ramdeni kaci midian

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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
