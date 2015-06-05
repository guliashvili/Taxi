package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class Car {
    private String carID;
    private String carDescription;
    private int carYear;
    private boolean conditioning;
    private int numPassengers;

    public Car(){
    }

    public Car(String carID,String carDescription,int carYear,boolean conditioning,int numPassengers){
        setCarID(carID);
        setCarDescription(carDescription);
        setCarYear(carYear);
        setConditioning(conditioning);
        setNumPassengers(numPassengers);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Car)) return  false;
        Car o = (Car)obj;
        return  ExternalAlgorithms.equalsNull(getCarDescription(), o.getCarDescription()) &&
                ExternalAlgorithms.equalsNull(getCarID(),o.getCarID()) &&
                getCarYear() == o.getCarYear() &&
                getNumPassengers() == o.getNumPassengers() &&
                hasConditioning() == o.hasConditioning();
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

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public boolean hasConditioning() {
        return conditioning;
    }

    public void setConditioning(boolean conditioning) {
        this.conditioning = conditioning;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }



}
