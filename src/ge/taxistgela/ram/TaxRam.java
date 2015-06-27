package ge.taxistgela.ram;

import ge.taxistgela.bean.Location;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.dao.OrderDao;
import ge.taxistgela.dao.UserDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by GIO on 6/26/2015.
 */
public class TaxRam implements TaxRamAPI {
    OrderDao orderDao;
    UserDao userDao;
    DriverDao driverDao;
    private ConcurrentHashMap<Integer, Location> driverIDLocation = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, List<Integer>> driverTODO = new ConcurrentHashMap<>();

    public TaxRam(OrderDao orderDao, UserDao userDao, DriverDao driverDao) {

    }

    public void updateDriverLocation(int driverID, Location location) {
        if (!driverDao.checkDriverID(driverID)) {
            System.err.println("updateDriverLocation wrong driverID");
        } else {
            driverIDLocation.put(driverID, location);
        }
    }

    public Location getDriverLocation(int driverID) {
        if (!driverDao.checkDriverID(driverID)) {
            System.err.println("getDriverLocation wrong driverID");
            return null;
        } else {
            return driverIDLocation.get(driverID);
        }

    }

    public void driverPickedUser(int driverID, int userID) {
        if (!driverDao.checkDriverID(driverID)) {
            System.err.println("driverPickedUser wrong driverID");
        } else if (!userDao.checkUserID(userID)) {
            System.err.println("driverPickedUser wrong userID");
        } else {
            driverTODO.putIfAbsent(driverID, Collections.synchronizedList(new ArrayList<Integer>()));
//TODO


        }

    }

    public void driverLeftUser(int driverID, int userID) {
        if (!driverDao.checkDriverID(driverID)) {
            System.err.println("driverPickedUser wrong driverID");
        } else if (!userDao.checkUserID(userID)) {
            System.err.println("driverPickedUser wrong userID");
        } else {
            if (driverTODO.containsKey(driverID)) {
                driverTODO.get(driverID).remove(new Integer(userID));
                driverTODO.get(driverID).remove(new Integer(-userID));
            }
        }

    }

    public List<Objects> getUserChoices() {
        return null;
    }

    private static class optimizedDbQueries {

    }

}
