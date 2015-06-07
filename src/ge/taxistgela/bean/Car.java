package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class Car {
    private String carID;
    private String carDescription;
    private Integer carYear;
    private Boolean conditioning;
    private Integer numPassengers;

    public Car() {
    }


    public Car(String carID, String carDescription, Integer carYear, Boolean conditioning, Integer numPassengers) {
        setCarID(carID);
        setCarDescription(carDescription);
        setCarYear(carYear);
        setConditioning(conditioning);
        setNumPassengers(numPassengers);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Car)) return false;
        Car o = (Car) obj;
        return ExternalAlgorithms.equalsNull(getCarDescription(), o.getCarDescription()) &&
                ExternalAlgorithms.equalsNull(getCarID(), o.getCarID()) &&
                ExternalAlgorithms.equalsNull(getCarYear() , o.getCarYear()) &&
                ExternalAlgorithms.equalsNull(getNumPassengers(), o.getNumPassengers()) &&
                ExternalAlgorithms.equalsNull(hasConditioning(), o.hasConditioning());
    }

    @Override
    public int hashCode() {
        return getCarID().hashCode();
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public Boolean hasConditioning() {
        return conditioning;
    }

    public void setConditioning(Boolean conditioning) {
        this.conditioning = conditioning;
    }

    public Integer getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(Integer numPassengers) {
        this.numPassengers = numPassengers;
    }
}
