package ge.taxistgela.ram.dao;

import ge.taxistgela.bean.User;
import ge.taxistgela.dao.UserDao;
import ge.taxistgela.ram.bean.UserInfo;

/**
 * Created by GIO on 6/28/2015.
 */
public class UserInfoDao {
    private UserDao userDao;

    public UserInfoDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserInfo getUserInfoByID(int userID){
        User user = userDao.getUserByID(userID);
        if(user == null || !user.isVerified()) return  null;
        UserInfo userInfo = new UserInfo(user);

        return  userInfo;
    }

}
