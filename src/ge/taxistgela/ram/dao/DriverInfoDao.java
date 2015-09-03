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

package ge.taxistgela.ram.dao;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.Location;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.helper.GoogleMapUtils;
import ge.taxistgela.ram.bean.DriverInfo;
import ge.taxistgela.ram.bean.OrderInfo;
import ge.taxistgela.ram.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;
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
        if (driver == null || !driver.isVerified()) return null;
        DriverInfo driverInfo = new DriverInfo(driver, users, drivers);

        return  driverInfo;
    }

    public List<DriverInfo> getDriversByUserPreference(UserInfo userInfo,OrderInfo orderInfo){
        List<DriverInfo> ret = new ArrayList<>();

        List<Driver> work = driverDao.getDriverByPreferences(userInfo);


        for(Driver elem : work){
            if(!elem.isVerified()) continue;
            if(!drivers.containsKey(elem.getDriverID())) continue;
            DriverInfo driverInfo = drivers.get(elem.getDriverID());
            driverInfo.update(elem);

            Location curLocation = driverInfo.getLocation();
            if(curLocation == null) continue;

            if((GoogleMapUtils.getRoad(curLocation,orderInfo.getStart()).duration.inSeconds +
                    GoogleMapUtils.getRoad(orderInfo.getStart(), orderInfo.getEnd()).duration.inSeconds) / 60 > orderInfo.getUser().getPreference().getTimeLimit())
                continue;

            if((userInfo.getPreference().isWantsAlone() && driverInfo.getnPassengers() > 0) ||
                    userInfo.getPreference().getPassengersCount() + driverInfo.getnPassengers() > driverInfo.getCar().getNumPassengers())
                    continue;

            ret.add(driverInfo);


        }



        return  ret;
    }

}
