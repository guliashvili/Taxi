package ge.taxistgela.model;

import ge.taxistgela.bean.Location;

/**
 * Created by GIO on 6/27/2015.
 */
public interface TaxRamAPI {
    void updateDriverLocation(int driverID, Location location);

    Location getDriverLocation(int driverID);

}
