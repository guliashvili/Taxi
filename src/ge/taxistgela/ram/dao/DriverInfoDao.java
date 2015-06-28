package ge.taxistgela.ram.dao;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.Location;
import ge.taxistgela.bean.Order;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.helper.GoogleMapUtils;
import ge.taxistgela.ram.bean.DriverInfo;
import ge.taxistgela.ram.bean.OrderInfo;
import ge.taxistgela.ram.bean.UserInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

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

    public List<DriverInfo> getDriversByUserPreference(UserInfo userInfo,OrderInfo orderInfo){
        List<DriverInfo> ret = new ArrayList<>();

        List<Driver> work = driverDao.getDriverByPreferences(userInfo);
        Long curMinute = TimeUnit.MILLISECONDS.toMinutes(new Date().getTime());


        for(Driver elem : work){
            if(!elem.isVerified()) continue;

            if(GoogleMapUtils.getRoad(orderInfo.getStart(),orderInfo.getEnd()).duration.inSeconds/60 > orderInfo.getTimeLimit())
                continue;



        }



        return  ret;
    }

}
