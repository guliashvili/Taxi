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
