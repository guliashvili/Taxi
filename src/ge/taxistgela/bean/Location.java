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
public class Location {
    @Expose
    private Double latitude;
    @Expose
    private Double longitude;

    public Location(Location loc) {
        this(loc.getLatitude(), loc.getLongitude());
    }
    public Location(Double latitude, Double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Location() {
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) return false;
        Location o = (Location) obj;
        return ExternalAlgorithms.equalsNull(getLatitude(), o.getLatitude()) &&
                ExternalAlgorithms.equalsNull(getLongitude(), o.getLongitude());
    }

    @Override
    public int hashCode() {
        return (int) (getLatitude() * getLongitude());
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
