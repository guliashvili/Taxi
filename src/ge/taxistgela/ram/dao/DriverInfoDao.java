package ge.taxistgela.ram.dao;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.ram.bean.DriverInfo;
import ge.taxistgela.ram.bean.UserInfo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by GIO on 6/28/2015.
 */
public class DriverInfoDao {
    private DriverDao driverDao;
    private ConcurrentHashMap<Integer, DriverInfo> drivers = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, UserInfo> users = new ConcurrentHashMap<>();

    public DriverInfoDao(DriverDao driverDao,ConcurrentHashMap<Integer, DriverInfo> drivers,ConcurrentHashMap<Integer, UserInfo> users){
        this.driverDao = driverDao;
        this.drivers  = drivers;
        this.users  = users;
    }

    public DriverInfo getDriverInfoByID(int driverID){
        Driver driver = driverDao.getDriverByID(driverID);
        if(driver == null || !driver.isVerified() || !driver.getIsActive()) return  null;
        DriverInfo driverInfo = new DriverInfo(driver);

        return  driverInfo;
    }

}
