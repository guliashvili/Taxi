package ge.taxistgela.bean;

/**
 * Created by Alex on 5/25/2015.
 */
public class DriverPreferences {
    private double rating;
    private boolean conditioning;
    private int timeLimit; // maximum ra dros daxarjavs klienttan misvlaze
    private int passengersLimit;

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
    
    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getPassengersLimit() {
        return passengersLimit;
    }

    public void setPassengersLimit(int passengersLimit) {
        this.passengersLimit = passengersLimit;
    }


}
