package ge.taxistgela.ram.dao;

import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.dao.UserDao;
import ge.taxistgela.ram.bean.DriverInfo;
import ge.taxistgela.ram.bean.UserInfo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by GIO on 6/28/2015.
 */
public class UserInfoDao {
    private UserDao userDao;
    private ConcurrentHashMap<Integer, DriverInfo> drivers = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, UserInfo> users = new ConcurrentHashMap<>();

    public UserInfoDao(UserDao userDao,ConcurrentHashMap<Integer, DriverInfo> drivers,ConcurrentHashMap<Integer, UserInfo> users){
        this.userDao = userDao;
        this.drivers  = drivers;
        this.users  = users;
    }


}
