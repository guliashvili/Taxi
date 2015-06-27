package ge.taxistgela.model;

import ge.taxistgela.bean.Location;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by GIO on 6/26/2015.
 */
public class TaxRam implements TaxRamAPI {
    private Map<Integer, Location> driverIDLocation = new ConcurrentHashMap<>();

    public void updateDriverLocation(int driverID, Location location) {
        driverIDLocation.put(driverID, location);
    }

    public Location getDriverLocation(int driverID) {
        return driverIDLocation.get(driverID);
    }


}
