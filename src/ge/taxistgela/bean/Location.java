package ge.taxistgela.bean;

import java.math.BigDecimal;

/**
 * Created by Alex on 5/25/2015.
 */
public class Location {
    private BigDecimal latitude;
    private BigDecimal longitute;

    public Location(BigDecimal latitude, BigDecimal longitute) {
        this.latitude = latitude;
        this.longitute = longitute;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitute() {
        return longitute;
    }

    public void setLongitute(BigDecimal longitute) {
        this.longitute = longitute;
    }
}
