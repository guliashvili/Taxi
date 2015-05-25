package ge.taxistgela.bean;

/**
 * Created by Alex on 5/25/2015.
 */
public class Preferences {
    private double rating;
    private int numPassengers;
    private boolean conditioning;
    private int carYear;
    private int timeLimit;
    private int passengersLimit;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }

    public boolean hasConditioning() {
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

    public int getPassengersLimit() {
        return passengersLimit;
    }

    public void setPassengersLimit(int passengersLimit) {
        this.passengersLimit = passengersLimit;
    }
}
