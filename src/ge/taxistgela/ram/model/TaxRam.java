package ge.taxistgela.ram.model;

import ge.taxistgela.bean.Location;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.dao.OrderDao;
import ge.taxistgela.dao.UserDao;
import ge.taxistgela.ram.bean.driverInfo;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by GIO on 6/26/2015.
 */
public class TaxRam implements TaxRamAPI {
    OrderDao orderDao;
    UserDao userDao;
    DriverDao driverDao;


    private ConcurrentHashMap<Integer, driverInfo> drivers = new ConcurrentHashMap<>();

    public TaxRam(OrderDao orderDao, UserDao userDao, DriverDao driverDao) {

    }

    public void updateDriverLocation(int driverID, Location location) {
        if (!driverDao.checkDriverID(driverID)) {
            System.err.println("updateDriverLocation wrong driverID");
        } else {
            drivers.putIfAbsent(driverID, new driverInfo());

            drivers.get(driverID).setLocation(location);

        }
    }

    public Location getDriverLocation(int driverID) {
        if (!driverDao.checkDriverID(driverID)) {
            System.err.println("getDriverLocation wrong driverID");
            return null;
        } else if (!drivers.containsKey(driverID)) {
            System.err.println("we have no information about the driver");
            return null;
        } else {
            return drivers.get(driverID).getLocation();
        }

    }

    public void driverPickedUser(int driverID, int userID) {
        if (!driverDao.checkDriverID(driverID)) {
            System.err.println("driverPickedUser wrong driverID");
        } else if (!userDao.checkUserID(userID)) {
            System.err.println("driverPickedUser wrong userID");
        } else {
            drivers.putIfAbsent(driverID, new driverInfo());
//TODO


        }

    }

    public void driverLeftUser(int driverID, int userID) {
        if (!driverDao.checkDriverID(driverID)) {
            System.err.println("driverPickedUser wrong driverID");
        } else if (!userDao.checkUserID(userID)) {
            System.err.println("driverPickedUser wrong userID");
        } else {
            if (drivers.containsKey(driverID)) {
                drivers.get(driverID).timetable.remove(new Integer(userID));
                drivers.get(driverID).timetable.remove(new Integer(-userID));
                drivers.get(driverID).inTheCar.remove(new Integer(userID));
            } else {
                System.err.println("Driver does not exists but he left someone? Oo");
            }
        }

    }

    public List<Object> getUserChoices() {
        return null;
    }



}
