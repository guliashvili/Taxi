package ge.taxistgela.model;

import ge.taxistgela.dao.DriverDaoAPI;

/**
 * Created by GIO on 5/25/2015.
 */
public class DriverManagerAPI {
    private DriverDaoAPI driverDao;
    public  DriverManagerAPI(DriverDaoAPI driverDao){
        this.driverDao = driverDao;
    }

}
