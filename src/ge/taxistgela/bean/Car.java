/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

package ge.taxistgela.bean;

import com.google.gson.annotations.Expose;
import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class Car {
    @Expose
    private String carID;
    @Expose
    private String carDescription;
    @Expose
    private Integer carYear;
    @Expose
    private Boolean conditioning;
    @Expose
    private Integer numPassengers;

    public Car() {
    }

    public Car(Car car) {
        this(car.getCarID(), car.getCarDescription(), car.getCarYear(), car.hasConditioning(), car.getNumPassengers());
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
