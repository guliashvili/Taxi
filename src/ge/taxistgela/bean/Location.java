package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class Location {
    private Double latitude;
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
