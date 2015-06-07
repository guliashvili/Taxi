package ge.taxistgela.dao;

import ge.taxistgela.bean.*;
import ge.taxistgela.helper.AdminDatabase;
import ge.taxistgela.helper.HashGenerator;
import ge.taxistgela.model.CompanyManager;
import ge.taxistgela.model.DriverManager;
import ge.taxistgela.model.ReviewManager;
import ge.taxistgela.model.UserManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Ratmach on 30/5/15.
 */
public class DaoTests {
    @Before
    public void setup(){
        /*AdminDatabase db = new AdminDatabase();
        try {
            db.recreateDatabase();
        }catch(Exception e){
            System.out.println(e.toString());
            assertTrue(false);
        }*/
    }
    @Test
    public void testCompanyDao(){
        //RegistrationTests
        CompanyDao man = new CompanyDao();
        Company comp = new Company(-1,"123456789","support@taxistgela.com","1234qwerTy","taxistGela","558677895","facebookIDmock","googleIDmock",true);
        assertEquals(0,man.registerCompany(comp));
        //assertEquals(-1,man.registerCompany(comp));
        //LoginTests
        Company company = man.loginCompany(comp.getEmail(),comp.getPassword());
        compareCompanies(comp,company);
        //CheckTests
        //CheckEmail
        assertFalse(man.checkEmail(""));
        assertTrue(man.checkEmail("support@taxistgela.com"));
        assertFalse(man.checkEmail("support1@taxistgela.com"));
        assertFalse(man.checkEmail("support2@taxistgela.com"));
        //CheckGoogleID
        assertFalse(man.checkGoogleID(""));
        assertTrue(man.checkGoogleID("googleIDmock"));
        assertFalse(man.checkGoogleID("asd"));
        assertTrue(man.checkGoogleID("googleIDmock"));
        assertFalse(man.checkGoogleID("a"));
        //CheckPhoneNumber
        assertFalse(man.checkPhoneNumber(""));
        assertTrue(man.checkPhoneNumber("558677895"));
        assertFalse(man.checkPhoneNumber("558677805"));
        assertTrue(man.checkPhoneNumber("558677895"));
        assertFalse(man.checkPhoneNumber("558677815"));
        //CheckCompanyID
        assertFalse(man.checkCompanyCode(""));
        assertTrue(man.checkCompanyCode("123456789"));
        assertFalse(man.checkPhoneNumber("123a5a78a"));
        assertTrue(man.checkCompanyCode("123456789"));
        assertFalse(man.checkPhoneNumber("123456780"));
        //CheckFacebook
        assertFalse(man.checkFacebookID(""));
        assertTrue(man.checkFacebookID("facebookIDmock"));
        assertFalse(man.checkFacebookID("asd"));
        assertTrue(man.checkFacebookID("facebookIDmock"));
        assertFalse(man.checkFacebookID("afk"));
        //UpdateTest
        comp.setCompanyName("taxistGela1");
        comp.setEmail("support1@taxistgela.com");
        comp.setCompanyCode("12345678a");
        comp.setFacebookID("asd");
        comp.setGoogleID("googleIDmock1");
        comp.setPhoneNumber("558677892");
        comp.setPassword("1234qwerTy3");
        man.updateCompany(comp);
        //LOGIN
        company = man.loginCompany(comp.getEmail(),comp.getPassword());
        compareCompanies(comp,company);
        assertFalse(man.checkEmail("support@taxistgela.com"));
        assertTrue(man.checkEmail("support1@taxistgela.com"));
        assertFalse(man.checkEmail("support2@taxistgela.com"));
        assertTrue(man.checkEmail("support1@taxistgela.com"));
        //CheckGoogleID
        assertFalse(man.checkGoogleID(""));
        assertFalse(man.checkGoogleID("googleIDmock"));
        assertTrue(man.checkGoogleID("googleIDmock1"));
        assertFalse(man.checkGoogleID("a"));
        assertTrue(man.checkGoogleID("googleIDmock1"));
        //CheckFacebook
        assertFalse(man.checkFacebookID(""));
        assertFalse(man.checkFacebookID("facebookIDmock"));
        assertTrue(man.checkFacebookID("asd"));
        assertFalse(man.checkFacebookID("afk"));
        assertTrue(man.checkFacebookID("asd"));
        //CheckPhoneNumber
        assertFalse(man.checkPhoneNumber(""));
        assertFalse(man.checkPhoneNumber("558677815"));
        assertTrue(man.checkPhoneNumber("558677892"));
        assertFalse(man.checkPhoneNumber("558677895"));
        assertTrue(man.checkPhoneNumber("558677892"));
        //CheckCompanyID
        assertFalse(man.checkPhoneNumber(""));
        assertFalse(man.checkPhoneNumber("1234567890a"));
        assertTrue(man.checkCompanyCode("12345678a"));
        assertFalse(man.checkPhoneNumber("gg wp"));
        assertTrue(man.checkCompanyCode("12345678a"));
    }
    private void compareCompanies(Company comp,Company comp1){
        assertEquals(comp.getCompanyCode(),comp1.getCompanyCode());
        assertEquals(comp.getCompanyID(),comp1.getCompanyID());
        assertEquals(comp.getCompanyName(),comp1.getCompanyName());
        assertEquals(comp.getEmail(),comp1.getEmail());
        assertEquals(comp.getFacebookID(),comp1.getFacebookID());
        assertEquals(comp.getGoogleID(),comp1.getGoogleID());
        assertEquals(comp.getPhoneNumber(),comp1.getPhoneNumber());
        assertEquals(HashGenerator.getSaltHash(comp.getPassword()), comp1.getPassword());
        //assertEquals();
    }
    @Test
    public void testUserDao(){
        UserDao man = new UserDao();
        UserPreference usrp = new UserPreference();
        usrp.setCarYear(2013);
        usrp.setMinimumDriverRating(2.3);
        usrp.setPassengersCount(2);
        usrp.setTimeLimit(30);
        usrp.setConditioning(true);
        usrp.setWantsAlone(false);
        User usr = new User(-1,"rmach13@freeuni.edu.ge","1234a","Rati","Matchavariani","558677895",Gender.MALE,"asdfa2d","asdsdafrk",4.3,usrp,false);
        man.insertUserPreference(usrp);
        //Register
        man.registerUser(usr);
        //Login
        User usr1 = man.loginUser(usr.getEmail(),usr.getPassword());
        compareUsers(usr,usr1);
        //CheckEmail
        assertFalse(man.checkEmail(""));
        assertTrue(man.checkEmail("rmach13@freeuni.edu.ge"));
        assertFalse(man.checkEmail("rmach12@freeuni.edu.ge"));
        assertTrue(man.checkEmail("rmach13@freeuni.edu.ge"));
        assertFalse(man.checkEmail("rmach12@freeuni.edu.ge"));
        //CheckPhoneNumber
        assertFalse(man.checkPhoneNumber(""));
        assertTrue(man.checkPhoneNumber("558677895"));
        assertFalse(man.checkPhoneNumber("+992358677895"));
        assertTrue(man.checkPhoneNumber("558677895"));
        assertFalse(man.checkPhoneNumber("+992358677895"));
        //CheckFacebookID
        assertFalse(man.checkFacebookID(""));
        assertTrue(man.checkFacebookID("asdfa2d"));
        assertFalse(man.checkFacebookID("asdfl2d"));
        assertTrue(man.checkFacebookID("asdfa2d"));
        assertFalse(man.checkFacebookID("asdfl2d"));
        //CheckGoogleID
        assertFalse(man.checkGoogleID(""));
        assertTrue(man.checkGoogleID("asdsdafrk"));
        assertFalse(man.checkGoogleID("asdfa3d"));
        assertTrue(man.checkGoogleID("asdsdafrk"));
        assertFalse(man.checkGoogleID("asdfa3d"));
        //Update
        usrp.setCarYear(2014);
        usrp.setMinimumDriverRating(2.3);
        usrp.setPassengersCount(2);
        usrp.setTimeLimit(30);
        usrp.setConditioning(true);
        usrp.setWantsAlone(false);
        User usr2 = new User(usr.getUserID(),"rmach12@freeuni.edu.ge","1234a","raTi","MatchavariaNi","+992358677895",Gender.MALE,"asdfa3d","asdfl2d",4.2,usrp,true);

        man.updateUser(usr2);
        man.updateUserPreference(usrp);
        usr1 = man.loginUser(usr2.getEmail(),usr2.getPassword());
        compareUsers(usr2,usr1);

        assertFalse(man.checkEmail(""));
        assertFalse(man.checkEmail("rmach13@freeuni.edu.ge"));
        assertTrue(man.checkEmail("rmach12@freeuni.edu.ge"));
        assertFalse(man.checkEmail("rmach13@freeuni.edu.ge"));
        assertTrue(man.checkEmail("rmach12@freeuni.edu.ge"));
        //CheckPhoneNumber
        assertFalse(man.checkPhoneNumber(""));
        assertFalse(man.checkPhoneNumber("558677895"));
        assertTrue(man.checkPhoneNumber("+992358677895"));
        assertFalse(man.checkPhoneNumber("558677895"));
        assertTrue(man.checkPhoneNumber("+992358677895"));
        //CheckGoogleID
        assertFalse(man.checkGoogleID(""));
        assertTrue(man.checkGoogleID("asdfl2d"));
        assertFalse(man.checkGoogleID("asdfa2d"));
        assertTrue(man.checkGoogleID("asdfl2d"));
        assertFalse(man.checkGoogleID("asdfa2d"));
        //CheckFacebookID
        assertFalse(man.checkFacebookID(""));
        assertTrue(man.checkFacebookID("asdfa3d"));
        assertFalse(man.checkFacebookID("asdsdafrk"));
        assertTrue(man.checkFacebookID("asdfa3d"));
        assertFalse(man.checkFacebookID("asdsdafrk"));
        //compareUsers(usr,man.getUserByID(usr.getUserID())); THATS NOT GOOD COMPARE
    }
    private void compareUsers(User user,User user1){
        Gender g = user.getGender();
        Gender g1 = user1.getGender();
        UserPreference pref = user.getPreference();
        UserPreference pref1 = user1.getPreference();
        assertEquals(HashGenerator.getSaltHash(user.getPassword()),user1.getPassword());
        assertEquals(user.getEmail(),user1.getEmail());
        assertEquals(user.getPhoneNumber(),user1.getPhoneNumber());
        assertEquals(user.getFacebookID(),user1.getFacebookID());
        assertEquals(user.getGoogleID(),user1.getGoogleID());
        assertEquals(user.getFirstName(),user1.getFirstName());
        assertEquals(user.getLastName(),user1.getLastName());
        assertEquals(user.getUserID(),user1.getUserID());
        assertEquals(user.getRating() , user1.getRating());//assertEquals(double,double) depricated
        assertEquals(pref.getCarYear(),pref1.getCarYear());
        assertEquals(pref.getMinimumDriverRating(), pref1.getMinimumDriverRating());
        assertEquals(pref.getPassengersCount(),pref1.getPassengersCount());
        assertEquals(pref.getTimeLimit(),pref1.getTimeLimit());
        //assertEquals(pref.getUserPreferenceID(),usrp.getUserPreferenceID());
        assertEquals(pref.isConditioning(),pref1.isConditioning());
        assertEquals(pref.isWantsAlone(),pref1.isWantsAlone());
        assertEquals(g.toString(),g1.toString());
    }
    @Test
    public void testDriverDao(){
        DriverDao man = new DriverDao();
        Car car = new Car();
        DriverPreference pref = new DriverPreference();

        car.setCarYear(1995);
        car.setCarDescription("junk");
        car.setCarID("69dota95");
        car.setNumPassengers(2);
        man.insertCar(car);

        pref.setCoefficientPer(0.69);
        pref.setDriverPreferenceID(-1);
        pref.setMinimumUserRating(0.0);//gela bizatkaznia
        man.insertDriverPreference(pref);

        Location l = new Location(new BigDecimal(2.234),new BigDecimal(3.1245));
        Driver driver = new Driver(-1,"01010101011","gela@taxistgela.ge","Madridista1",null,"gela","magaltadze",Gender.MALE,"555696996",car,"gelandara95","bozandara",l,2.2,pref,true,true);

        man.registerDriver(driver);
        Driver driver1=man.loginDriver(driver.getEmail(),driver.getPassword());
        compareDrivers(driver1,driver);
        //Register + login test
        comparePrefernces(man.getDriverPreferenceByID(pref.getDriverPreferenceID()),pref);
        /*Car car1 = man.getCarByID(car.getCarID());*/ //TODO NEEDS FIXING !!!!!!!!!!!!!!!!!!!GMERTCHEMAV!!!!!!!!!!!!!!!!
        //Update

        Driver driver2 = new Driver(-1,"01010101012","gelusi@taxistgela.ge","Madridista!",null,"Gela","Magaltadze",Gender.FEMALE,"555696997",car,"gelusi7","bozandara99",l,2.3,pref,false,false);
        car.setCarYear(1996);
        car.setCarDescription("trash");
        car.setCarID("69dota95");
        car.setNumPassengers(3);//adgili miasvarka
        man.updateCar(car);

        pref.setCoefficientPer(0.70);
        pref.setMinimumUserRating(0.1);//gelam standartebi awia
        man.updateDriverPreference(pref);

        man.updateDriver(driver2);
        //Login
        driver1=man.loginDriver(driver2.getEmail(),driver2.getPassword());
        compareDrivers(driver2,driver1);
        /*Car car1 = man.getCarByID(car.getCarID());*/ //TODO NEEDS FIXING !!!!!!!!!!!!!!!!!!!GMERTCHEMAV!!!!!!!!!!!!!!!!
        DriverPreference pref1 = man.getDriverPreferenceByID(pref.getDriverPreferenceID());
        comparePrefernces(pref,pref1);

        //license plate check
        assertTrue(man.checkCarID(car.getCarID()));
        assertFalse(man.checkCarID("00aaaa00"));
        assertFalse(man.checkCarID("01aaaa10"));
        //email check
        assertFalse(man.checkEmail(driver.getEmail()));
        assertTrue(man.checkEmail(driver2.getEmail()));
        //facebook id check
        assertTrue(man.checkFacebookID(driver2.getFacebookID()));
        assertFalse(man.checkFacebookID(driver.getFacebookID()));
        //google id check
        assertTrue(man.checkFacebookID(driver2.getGoogleID()));
        assertFalse(man.checkFacebookID(driver.getGoogleID()));
        //phone number check
        assertTrue(man.checkPhoneNumber(driver2.getGoogleID()));
        assertFalse(man.checkPhoneNumber(driver.getGoogleID()));
        //get driver by company
        List<Driver> drivers =man.getDriverByCompanyID(driver2.getCompanyID());
        for(Driver d:drivers){
            if(d.getDriverID().equals(driver2.getDriverID()))
                compareDrivers(d,driver2);
        }
        comparePrefernces(man.getDriverPreferenceByID(pref.getDriverPreferenceID()),pref);
    }
    private void compareCars(Car car1,Car car2){
        //car comparison
        assertEquals(car1.getCarDescription(),car2.getCarDescription());
        assertEquals(car1.getCarID(),car2.getCarID());
        assertEquals(car1.getCarYear(), car2.getCarYear());
        assertEquals(car1.getNumPassengers(),car2.getNumPassengers());
    }
    private void comparePrefernces(DriverPreference dp1,DriverPreference dp2){
        //preference comparison
        assertEquals(dp1.getCoefficientPer(), dp2.getCoefficientPer());
        assertEquals(dp1.getDriverPreferenceID(),dp2.getDriverPreferenceID());
        assertEquals(dp1.getMinimumUserRating(), dp2.getMinimumUserRating());
    }
    private void compareDrivers(Driver driver,Driver driver1){
        Car car = driver.getCar();
        DriverPreference pref = driver.getPreferences();
        assertEquals(driver1.getRating() , driver.getRating());
        assertEquals(driver1.getDriverID(),driver.getDriverID());
        assertEquals(driver1.getCompanyID(),driver.getCompanyID());
        assertEquals(driver1.getEmail(),driver.getEmail());
        assertEquals(driver1.getFacebookID(),driver.getFacebookID());
        assertEquals(driver1.getFirstName(),driver.getFirstName());
        assertEquals(driver1.getLastName(),driver.getLastName());
        assertEquals(driver1.getGender().toString(),driver.getGender().toString());
        assertEquals(driver1.getGoogleID(),driver.getGoogleID());
        assertEquals(HashGenerator.getSaltHash(driver1.getPassword()),driver.getPassword());
        assertEquals(driver1.getPersonalID(),driver.getPersonalID());
        assertEquals(driver1.getPhoneNumber(),driver.getPhoneNumber());
        assertEquals(driver1.isActive(),driver.isActive());
        assertEquals(driver1.isVerified(),driver.isVerified());
        Car c = driver1.getCar();
        DriverPreference p = driver1.getPreferences();
        compareCars(c,car);
        comparePrefernces(p,pref);
    }
    @Test
    public void testOrderDao(){

    }
    @Test
    public void testReviewDao(){
        /*ReviewManager man = new ReviewManager(new ReviewDao());
        Review rev = new Review()
        man.addReview();
        man.getReviewByDriverID();
        man.getReviewByUserID();
        man.getReviewByID();
        man.updateReview();
        man.getReviewByDriverID();
        man.getReviewByUserID();
        man.getReviewByID();*/
    }
    @After
    public void cleanup(){/*
        AdminDatabase db = new AdminDatabase();
        try {
            db.recreateDatabase();
        }catch(Exception e){
            System.out.println(e.toString());
            assertTrue(false);
        }*/
    }
}