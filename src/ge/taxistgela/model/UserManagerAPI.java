package ge.taxistgela.model;

import ge.taxistgela.dao.UserDaoAPI;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class UserManagerAPI {
    private UserDaoAPI userDao;
    public  UserManagerAPI(UserDaoAPI userDao){
        this.userDao = userDao;
    }

}
