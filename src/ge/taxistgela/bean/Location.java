package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

import java.math.BigDecimal;

/**
 * Created by Alex on 5/25/2015.
 */
public class Location {
    private BigDecimal latitude;
    private BigDecimal longitude;
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Location)) return  false;
        Location o = (Location)obj;
        return  ExternalAlgorithms.equalsNull(getLatitude() , o.getLatitude()) &&
                ExternalAlgorithms.equalsNull(getLongitude(), o.getLongitude());
    }

    @Override
    public int hashCode() {
        return getLatitude().multiply( getLongitude()).hashCode();
    }
    public Location(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
