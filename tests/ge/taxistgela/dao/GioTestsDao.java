package ge.taxistgela.dao;

import ge.taxistgela.bean.*;
import ge.taxistgela.helper.AdminDatabase;
import ge.taxistgela.helper.HashGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by GIO on 6/25/2015.
 */
public class GioTestsDao {


    User user1, user2;
    Driver driver1, driver2;
    Company company1;

    UserPreference userPreference1, userPreference2;
    DriverPreference driverPreference1, driverPreference2;
    Location location1, location2;
    Car car1, car2;

    UserDao userDao;
    DriverDao driverDao;
    CompanyDao companyDao;


    @Before
    @After
    public void setup() {
        userDao = new UserDao();
        companyDao = new CompanyDao();
        driverDao = new DriverDao();

        AdminDatabase db = new AdminDatabase();
        try {
            db.recreateDatabase();
        } catch (Exception e) {
            System.out.println(e.toString());
            assertTrue(false);
        }

        userPreference1 = new UserPreference(-1, 4.0, true, 1995, 10, 2, true);
        userPreference2 = new UserPreference(-1, 3.9, false, 1990, 12, 1, true);

        driverPreference1 = new DriverPreference(-1, 2.0, 4.0);
        driverPreference2 = new DriverPreference(-1, 2.0, 3.0);

        location1 = new Location(41.745984, 44.775832);
        location2 = new Location(41.709599, 44.756885);

        car1 = new Car("JRJ-880", "jigri mdzgoli", 2000, true, 4);
        car2 = new Car("PK-990-AC", "mweveli", 1994, false, 4);

        driver1 = new Driver(-1, "01005030801", "vinmesmiti@gmail.com", "traki", null, "Giorgi", "Guliashvili", Gender.MALE, "557105511", car1,
                null, "googleIDFOr traki", location1, 4.0, driverPreference1, true, false, false);

        driver2 = new Driver(-1, "01005030666", "gguli13@freeuni.edu.ge", "evtanazia", null, "Gela", "Meyleve", Gender.FEMALE, "557105666", car2,
                "yleta jgro", null, location2, 4.0, driverPreference2, true, false, false);

        company1 = new Company(-1, "code", "email@email.com", "123456(dumbpwd)", "lucifer", "566666666", "fbid", "glid", false, false);

        user1 = new User(-1, "daltoniki@tvali.ge", "shavitetria", "mwvane", "yvitladze", "511123123", Gender.MALE, "fbb sun",
                null, 5.0, userPreference1, false, false);
        user2 = new User(-1, "uxucesi@mta.ge", "xmali", "mtian", "balaxadze", "511223344", Gender.MALE, null,
                "glid", 5.5, userPreference2, false, false);


    }


    public UserPreference insertUserPreference(UserPreference blank, UserPreference target) {
        UserPreference realUserPreference1 = new UserPreference(blank);
        UserPreference userPreference = new UserPreference(target);

        assertFalse(userDao.insertUserPreference(realUserPreference1));

        userPreference.setUserPreferenceID(realUserPreference1.getUserPreferenceID());

        realUserPreference1.setCarYear(userPreference.getCarYear());
        realUserPreference1.setMinimumDriverRating(userPreference.getMinimumDriverRating());
        realUserPreference1.setConditioning(userPreference.isConditioning());
        realUserPreference1.setPassengersCount(userPreference.getPassengersCount());
        realUserPreference1.setTimeLimit(userPreference.getTimeLimit());
        realUserPreference1.setWantsAlone(userPreference.isWantsAlone());

        assertTrue(realUserPreference1.equals(userPreference));

        assertFalse(userDao.updateUserPreference(realUserPreference1));

        realUserPreference1 = userDao.getUserPreferenceByID(userPreference.getUserPreferenceID());

        assertTrue(realUserPreference1.equals(userPreference));

        return realUserPreference1;
    }

    public User insertUser(User blank, User target, UserPreference userPreference) {
        User tmp = new User(blank);
        User to = new User(target);

        tmp.setPreference(userPreference);
        to.setPreference(userPreference);

        assertFalse(userDao.registerUser(tmp));

        tmp.setIsVerifiedPhone(to.getIsVerifiedPhone());
        tmp.setIsVerifiedEmail(to.getIsVerifiedEmail());
        tmp.setEmail(to.getEmail());
        tmp.setFacebookID(to.getFacebookID());
        tmp.setGoogleID(to.getGoogleID());
        tmp.setPassword(to.getPassword());
        tmp.setFirstName(to.getFirstName());
        tmp.setLastName(to.getLastName());
        tmp.setPhoneNumber(to.getPhoneNumber());
        tmp.setGender(to.getGender());
        tmp.setRating(to.getRating());
        tmp.setToken(to.getToken());


        assertFalse(userDao.updateUser(tmp));

        to.setUserID(tmp.getUserID());
        assertEquals(tmp, to);


        tmp = userDao.getUserByEmail(tmp.getEmail());
        tmp.setPassword(to.getPassword());
        assertFalse(userDao.changePassword(tmp));
        tmp = userDao.getUserByEmail(tmp.getEmail());

        to.setPassword(HashGenerator.getSaltHash(to.getPassword()));
        assertEquals(to, tmp);


        assertFalse(userDao.verifyUserPhoneNumber(tmp.getPhoneNumber()));
        tmp = userDao.getUserByEmail(tmp.getEmail());
        assertTrue(tmp.getIsVerifiedPhone());


        assertFalse(userDao.verifyUserEmail(tmp.getEmail()));
        tmp = userDao.getUserByPhoneNumber(tmp.getPhoneNumber());
        assertTrue(tmp.getIsVerifiedPhone());

        assertEquals(tmp, userDao.getUserByEmail(tmp.getEmail()));
        if (tmp.getFacebookID() != null)
            assertEquals(tmp, userDao.getUserByFacebookID(tmp.getFacebookID()));
        if (tmp.getGoogleID() != null)
            assertEquals(tmp, userDao.getUserByGoogleID(tmp.getGoogleID()));
        assertEquals(tmp, userDao.getUserByID(tmp.getUserID()));
        assertEquals(tmp, userDao.getUserByPhoneNumber(tmp.getPhoneNumber()));
        assertEquals(tmp, userDao.loginUser(target.getEmail(), target.getPassword()));

        return tmp;
    }

    public void checksUsers(User[] users) {
        for (User elem : users) {
            assertTrue(userDao.checkEmail(elem.getEmail()));
            assertTrue(userDao.checkPhoneNumber(elem.getPhoneNumber()));
            if (elem.getGoogleID() != null)
                assertTrue(userDao.checkGoogleID(elem.getGoogleID()));
            if (elem.getFacebookID() != null)
                assertTrue(userDao.checkFacebookID(elem.getFacebookID()));
            assertEquals(userDao.getUserTokenByID(elem.getUserID()), elem.getToken());
            assertEquals(userDao.getUserIDByToken(elem.getToken()), elem.getUserID());
        }
    }


    public void testUsers() {
        UserPreference blankPreference = new UserPreference(-1, 0.0, false, 0, 0, 0, false);

        userPreference1 = insertUserPreference(blankPreference, userPreference1);
        userPreference2 = insertUserPreference(blankPreference, userPreference2);

        User blankUser = new User(-1, "a@a.ge", "a", "a", "a", "555555555", Gender.MALE, null,
                null, 0.0, userPreference2, false, false);
        user1 = insertUser(blankUser, user1, userPreference1);
        assertTrue(userDao.registerUser(user1));

        user2 = insertUser(blankUser, user2, userPreference2);
        assertTrue(userDao.registerUser(user2));

        checksUsers(new User[]{user1, user2});

        user1 = verifyUser(user1);
        user2 = verifyUser(user2);
    }

    public DriverPreference insertDriverPreference(DriverPreference blank,DriverPreference target){
        DriverPreference tmp = new DriverPreference(blank);
        DriverPreference to = new DriverPreference(target);

        assertFalse(driverDao.insertDriverPreference(tmp));

        tmp.setMinimumUserRating(to.getMinimumUserRating());
        tmp.setCoefficientPer(to.getCoefficientPer());

        assertFalse(driverDao.updateDriverPreference(tmp));
        to.setDriverPreferenceID(tmp.getDriverPreferenceID());

        assertEquals(to, tmp);

        tmp = driverDao.getDriverPreferenceByID(tmp.getDriverPreferenceID());

        assertEquals(to, tmp);

        return  tmp;
    }

    public Car insertCar(Car blank,Car target){
        Car tmp = new Car(blank);
        Car to = new Car(target);
        tmp.setCarID(target.getCarID());

        assertFalse(driverDao.insertCar(tmp));

        tmp.setConditioning(to.hasConditioning());
        tmp.setCarDescription(to.getCarDescription());
        tmp.setCarYear(to.getCarYear());
        tmp.setNumPassengers(to.getNumPassengers());

        assertFalse(driverDao.updateCar(tmp));

        to.setCarID(tmp.getCarID());

        assertEquals(to,tmp);

        tmp = driverDao.getCarByID(tmp.getCarID());

        assertEquals(to, tmp);

        return  tmp;
    }


    public Driver insertDriver(Driver blank,Driver target,DriverPreference pf,Car cr){
        Driver tmp = new Driver(blank);
        Driver to = new Driver(target);


        assertFalse(driverDao.registerDriver(tmp));

        to.setDriverID(tmp.getDriverID());
        to.setPreferences(pf);
        to.setCar(cr);


        tmp.setPersonalID(to.getPersonalID());
        tmp.setEmail(to.getEmail());

        tmp.setCompanyID(to.getCompanyID());
        tmp.setFirstName(to.getFirstName());
        tmp.setLastName(to.getLastName());
        tmp.setGender(to.getGender());
        tmp.setPhoneNumber(to.getPhoneNumber());
        tmp.setCar(to.getCar());
        tmp.setFacebookID(to.getFacebookID());
        tmp.setGoogleID(to.getGoogleID());
        tmp.setLocation(to.getLocation());
        tmp.setRating(to.getRating());
        tmp.setPreferences(to.getPreferences());
        tmp.setIsActive(to.getIsActive());
        tmp.setIsVerifiedEmail(to.getIsVerifiedEmail());
        tmp.setIsVerifiedPhone(to.getIsVerifiedPhone());
        tmp.setToken(to.getToken());

        assertFalse(driverDao.updateDriver(tmp));

        tmp.setPassword(to.getPassword());
        driverDao.changePassword(tmp);

        to.setDriverID(tmp.getDriverID());

        assertEquals(tmp, to);
        to.setPassword(HashGenerator.getSaltHash(to.getPassword()));
        tmp = driverDao.getDriverByID(tmp.getDriverID());

        assertEquals(tmp,to);


        assertEquals(tmp, driverDao.getDriverByEmail(tmp.getEmail()));
        if (tmp.getFacebookID() != null)
            assertEquals(tmp, driverDao.getDriverByFacebookID(tmp.getFacebookID()));
        if (tmp.getGoogleID() != null)
            assertEquals(tmp, driverDao.getDriverByGoogleID(tmp.getGoogleID()));
        assertEquals(tmp, driverDao.getDriverByID(tmp.getDriverID()));
        assertEquals(tmp, driverDao.getDriverByPhoneNumber(tmp.getPhoneNumber()));
        assertEquals(tmp, driverDao.loginDriver(target.getEmail(), target.getPassword()));

        return tmp;
    }

    public void checkDrivers(Driver[] drivers) {
        for (Driver elem : drivers) {
            if (elem.getFacebookID() != null)
                assertTrue(driverDao.checkFacebookID(elem.getFacebookID()));
            if (elem.getGoogleID() != null)
                assertTrue(driverDao.checkGoogleID(elem.getGoogleID()));
            assertTrue(driverDao.checkPhoneNumber(elem.getPhoneNumber()));
            assertTrue(driverDao.checkCarID(elem.getCar().getCarID()));
            assertTrue(driverDao.checkEmail(elem.getEmail()));

            assertEquals(driverDao.getDriverTokenByID(elem.getDriverID()), elem.getToken());
            assertEquals(driverDao.getDriverIDByToken(elem.getToken()), elem.getDriverID());
        }
    }
    public  void testDrivers(){
        DriverPreference blankPreference = new DriverPreference(-1,-1.0,-1.0);

        driverPreference1 = insertDriverPreference(blankPreference, driverPreference1);
        driverPreference2 = insertDriverPreference(blankPreference, driverPreference2);

        Car blank = new Car("JJJ-666","blanki var",0,false,-1);
        car1 = insertCar(blank,car1);
        car2 = insertCar(blank,car2);

        Location blankLocation = new Location(0.0,0.0);
        Driver blankDriver = new Driver(-1,"66666669999","blank@blank.ge","blankpwd",null,"blank","blankiashvili",
                Gender.MALE,"556667776",car2,null,null,blankLocation,-1.0,driverPreference2,false,false,false);
        driver1 = insertDriver(blankDriver,driver1,driverPreference1,car1);
        assertTrue(driverDao.registerDriver(driver1));
        driver2 = insertDriver(blankDriver,driver2,driverPreference2,car2);
        assertTrue(driverDao.registerDriver(driver2));

        checkDrivers(new Driver[]{driver1, driver2});

        driver1 = verifyDriver(driver1);
        driver2 = verifyDriver(driver2);
    }

    public Company insertCompany(Company blank, Company target) {
        Company tmp = new Company(blank);
        Company to = new Company(target);

        assertFalse(companyDao.registerCompany(tmp));

        to.setCompanyID(tmp.getCompanyID());


        tmp.setCompanyCode(to.getCompanyCode());
        tmp.setEmail(to.getEmail());
        tmp.setPassword(to.getPassword());
        tmp.setCompanyName(to.getCompanyName());
        tmp.setPhoneNumber(to.getPhoneNumber());
        tmp.setFacebookID(to.getFacebookID());
        tmp.setGoogleID(to.getGoogleID());
        tmp.setIsVerifiedEmail(to.getIsVerifiedEmail());
        tmp.setIsVerifiedPhone(to.getIsVerifiedPhone());

        assertEquals(tmp, to);

        assertFalse(companyDao.updateCompany(tmp));
        tmp.setPassword(to.getPassword());
        assertFalse(companyDao.changePassword(tmp));
        tmp = companyDao.getCompanyByID(tmp.getCompanyID());
        to.setPassword(HashGenerator.getSaltHash(to.getPassword()));
        assertEquals(tmp, to);


        assertEquals(tmp, companyDao.getCompanyByEmail(tmp.getEmail()));
        if (tmp.getFacebookID() != null)
            assertEquals(tmp, companyDao.getCompanyByFacebookID(tmp.getFacebookID()));
        if (tmp.getGoogleID() != null)
            assertEquals(tmp, companyDao.getCompanyByGoogleID(tmp.getGoogleID()));
        assertEquals(tmp, companyDao.getCompanyByID(tmp.getCompanyID()));
        assertEquals(tmp, companyDao.getCompanyByPhoneNumber(tmp.getPhoneNumber()));
        assertEquals(tmp, companyDao.loginCompany(target.getEmail(), target.getPassword()));


        return tmp;
    }

    public void checkCompanies(Company[] companies) {
        for (Company elem : companies) {
            if (elem.getFacebookID() != null)
                assertTrue(companyDao.checkFacebookID(elem.getFacebookID()));
            if (elem.getGoogleID() != null)
                assertTrue(companyDao.checkGoogleID(elem.getGoogleID()));
            assertTrue(companyDao.checkPhoneNumber(elem.getPhoneNumber()));
            assertTrue(companyDao.checkCompanyCode(elem.getCompanyCode()));
            assertTrue(companyDao.checkEmail(elem.getEmail()));
            assertEquals(companyDao.getCompanyIDByCode(elem.getCompanyCode()), elem.getCompanyID());
        }
    }

    public User verifyUser(User user) {
        user.setIsVerifiedEmail(true);
        assertFalse(userDao.updateUser(user));
        user = userDao.getUserByID(user.getUserID());
        assertTrue(user.getIsVerifiedEmail());


        user.setIsVerifiedPhone(true);
        assertFalse(userDao.updateUser(user));
        user = userDao.getUserByID(user.getUserID());
        assertTrue(user.getIsVerifiedPhone());


        user.setIsVerifiedEmail(false);
        user.setIsVerifiedPhone(false);
        assertFalse(userDao.updateUser(user));

        user = userDao.getUserByID(user.getUserID());

        assertFalse(userDao.verifyUserEmail(user.getEmail()));
        assertFalse(userDao.verifyUserPhoneNumber(user.getPhoneNumber()));

        user = userDao.getUserByID(user.getUserID());

        assertTrue(user.getIsVerifiedEmail());
        assertTrue(user.getIsVerifiedPhone());

        return user;
    }

    public Driver verifyDriver(Driver driver) {
        driver.setIsVerifiedEmail(true);
        assertFalse(driverDao.updateDriver(driver));
        driver = driverDao.getDriverByID(driver.getDriverID());
        assertTrue(driver.getIsVerifiedEmail());


        driver.setIsVerifiedPhone(true);
        assertFalse(driverDao.updateDriver(driver));
        driver = driverDao.getDriverByID(driver.getDriverID());
        assertTrue(driver.getIsVerifiedPhone());

        driver.setIsVerifiedEmail(false);
        driver.setIsVerifiedPhone(false);
        assertFalse(driverDao.updateDriver(driver));

        driver = driverDao.getDriverByID(driver.getDriverID());

        assertFalse(driverDao.verifyDriverEmail(driver.getEmail()));
        assertFalse(driverDao.verifyDriverPhoneNumber(driver.getPhoneNumber()));

        driver = driverDao.getDriverByID(driver.getDriverID());

        assertTrue(driver.getIsVerifiedEmail());
        assertTrue(driver.getIsVerifiedPhone());
        return driver;
    }

    public Company verifyCompany(Company company) {
        company.setIsVerifiedEmail(true);
        assertFalse(companyDao.updateCompany(company));
        company = companyDao.getCompanyByID(company.getCompanyID());
        assertTrue(company.getIsVerifiedEmail());


        company.setIsVerifiedPhone(true);
        assertFalse(companyDao.updateCompany(company));
        company = companyDao.getCompanyByID(company.getCompanyID());
        assertTrue(company.getIsVerifiedPhone());

        company.setIsVerifiedEmail(false);
        company.setIsVerifiedPhone(false);
        assertFalse(companyDao.updateCompany(company));

        company = companyDao.getCompanyByID(company.getCompanyID());

        assertFalse(companyDao.verifyCompanyEmail(company.getEmail()));
        assertFalse(companyDao.verifyCompanyPhoneNumber(company.getPhoneNumber()));

        company = companyDao.getCompanyByID(company.getCompanyID());

        assertTrue(company.getIsVerifiedEmail());
        assertTrue(company.getIsVerifiedPhone());


        return company;
    }

    public void testCompanies() {
        Company blank = new Company(-1, "blankcode", "blank.company.ge", "blankPWd", "blankGmert", "559101010", "fb comp blank", "gl comp blank",
                false, false);
        company1 = insertCompany(blank, company1);
        assertTrue(companyDao.registerCompany(company1));

        checkCompanies(new Company[]{company1});

        company1 = verifyCompany(company1);

    }

    public void interaction() {
        List<Driver> ls = driverDao.getDriverByCompanyID(company1.getCompanyID());
        ls.sort((o1, o2) -> o1.getDriverID() - o2.getDriverID());
        List<Driver> cmp1 = new ArrayList<>();
        cmp1.add(driver1);
        assertEquals(ls, cmp1);


        List<Driver> ls2 = driverDao.getDriverByPreferences(user1);
        ls2.sort((o1, o2) -> o1.getDriverID() - o2.getDriverID());

        List<Driver> cmp2 = new ArrayList<>();
        cmp2.add(driver1);
        assertEquals(ls2, cmp2);

        List<Driver> ls3 = driverDao.getDriverByPreferences(user2);
        ls3.sort((o1, o2) -> o1.getDriverID() - o2.getDriverID());

        List<Driver> cmp3 = new ArrayList<>();
        cmp3.add(driver1);
        cmp3.add(driver2);
        assertEquals(ls3, cmp3);


        List<User> us1 = userDao.getUsersByPreferences(driver1);
        List<User> us2 = userDao.getUsersByPreferences(driver2);

        us1.sort((o1, o2) -> o1.getUserID() - o2.getUserID());
        us2.sort((o1, o2) -> o1.getUserID() - o2.getUserID());

        List<User> tar1 = new ArrayList<>();
        List<User> tar2 = new ArrayList<>();
        tar1.add(user1);
        tar1.add(user2);
        tar2.add(user2);

        assertEquals(us1, tar1);
        assertEquals(us2, tar2);

    }


    @Test
    public void superTest() {
        testCompanies();
        driver1.setCompanyID(company1.getCompanyID());
        testUsers();

        testDrivers();

        interaction();


    }


}
