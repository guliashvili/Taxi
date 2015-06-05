package ge.taxistgela.dao;

import ge.taxistgela.bean.*;
import ge.taxistgela.helper.AdminDatabase;
import ge.taxistgela.helper.HashGenerator;
import ge.taxistgela.model.CompanyManager;
import ge.taxistgela.model.DriverManager;
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
        CompanyManager man = new CompanyManager(new CompanyDao());
        Company comp = new Company(-1,"123456789","support@taxistgela.com","1234qwerTy","taxistGela","+995558677895","facebookIDmock","googleIDmock",true);
        assertEquals(0,man.registerCompany(comp));
        assertEquals(-1,man.registerCompany(comp));
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
        assertTrue(man.checkPhoneNumber("+995558677895"));
        assertFalse(man.checkPhoneNumber("+995558677805"));
        assertTrue(man.checkPhoneNumber("+995558677895"));
        assertFalse(man.checkPhoneNumber("+995558677815"));
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
        comp.setPhoneNumber("+995558677892");
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
        assertFalse(man.checkPhoneNumber("+995558677815"));
        assertTrue(man.checkPhoneNumber("+995558677892"));
        assertFalse(man.checkPhoneNumber("+995558677895"));
        assertTrue(man.checkPhoneNumber("+995558677892"));
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
        UserDao dao = new UserDao();
        UserPreference usrp = new UserPreference();
        usrp.setCarYear(2013);
        usrp.setMinimumDriverRating(2.3);
        usrp.setPassengersCount(2);
        usrp.setTimeLimit(30);
        usrp.setConditioning(true);
        usrp.setWantsAlone(false);

        User usr = new User();
        when(usr.getFirstName()).thenReturn("Rati");
        when(usr.getEmail()).thenReturn("rmach13@freeuni.edu.ge");
        when(usr.getGoogleID()).thenReturn("asdsdafrk");
        when(usr.getPassword()).thenReturn("1234a");
        when(usr.getFacebookID()).thenReturn("asdfa2d");
        when(usr.getPhoneNumber()).thenReturn("+995558677895");
        when(usr.getLastName()).thenReturn("Matchavariani");
        when(usr.getRating()).thenReturn(4.3);
        when(usr.getGender()).thenReturn(Gender.MALE);
        when(usr.getPreference()).thenReturn(usrp);
        when(usr.getUserID()).thenReturn(-1);
        //Register
        dao.registerUser(usr);
        //Login
        User usr1 = dao.loginUser(usr.getEmail(),usr.getPassword());
        compareUsers(usr,usr1);
        //CheckEmail
        assertFalse(dao.checkEmail(""));
        assertTrue(dao.checkEmail("rmach13@freeuni.edu.ge"));
        assertFalse(dao.checkEmail("rmach12@freeuni.edu.ge"));
        assertTrue(dao.checkEmail("rmach13@freeuni.edu.ge"));
        assertFalse(dao.checkEmail("rmach12@freeuni.edu.ge"));
        //CheckPhoneNumber
        assertFalse(dao.checkPhoneNumber(""));
        assertTrue(dao.checkPhoneNumber("+995558677895"));
        assertFalse(dao.checkPhoneNumber("+992358677895"));
        assertTrue(dao.checkPhoneNumber("+995558677895"));
        assertFalse(dao.checkPhoneNumber("+992358677895"));
        //CheckFacebookID
        assertFalse(dao.checkFacebookID(""));
        assertTrue(dao.checkFacebookID("asdfa2d"));
        assertFalse(dao.checkFacebookID("asdfl2d"));
        assertTrue(dao.checkFacebookID("asdfa2d"));
        assertFalse(dao.checkFacebookID("asdfl2d"));
        //CheckGoogleID
        assertFalse(dao.checkGoogleID(""));
        assertTrue(dao.checkGoogleID("asdsdafrk"));
        assertFalse(dao.checkGoogleID("asdfa3d"));
        assertTrue(dao.checkGoogleID("asdsdafrk"));
        assertFalse(dao.checkGoogleID("asdfa3d"));
        //Update
        usrp = Mockito.mock(UserPreference.class);
        when(usrp.getCarYear()).thenReturn(2013);
        when(usrp.getMinimumDriverRating()).thenReturn(2.3);
        when(usrp.getPassengersCount()).thenReturn(2);
        when(usrp.getTimeLimit()).thenReturn(30);
        when(usrp.isConditioning()).thenReturn(true);
        when(usrp.isWantsAlone()).thenReturn(false);
        usrp = Mockito.mock(UserPreference.class);
        usr = Mockito.mock(User.class);
        when(usr.getFirstName()).thenReturn("raTi");
        when(usr.getEmail()).thenReturn("rmach12@freeuni.edu.ge");
        when(usr.getGoogleID()).thenReturn("asdfa3d");
        when(usr.getPassword()).thenReturn("1234a");
        when(usr.getFacebookID()).thenReturn("asdfl2d");
        when(usr.getPhoneNumber()).thenReturn("+992358677895");
        when(usr.getLastName()).thenReturn("MatchavariaNi");
        when(usr.getRating()).thenReturn(4.2);
        when(usr.getGender()).thenReturn(Gender.MALE);
        when(usr.getPreference()).thenReturn(usrp);
        when(usr.getUserID()).thenReturn(-1);

        dao.updateUser(usr);
        usr1 = dao.loginUser(usr.getEmail(),usr.getPassword());
        compareUsers(usr,usr1);

        assertFalse(dao.checkEmail(""));
        assertFalse(dao.checkEmail("rmach13@freeuni.edu.ge"));
        assertTrue(dao.checkEmail("rmach12@freeuni.edu.ge"));
        assertFalse(dao.checkEmail("rmach13@freeuni.edu.ge"));
        assertTrue(dao.checkEmail("rmach12@freeuni.edu.ge"));
        //CheckPhoneNumber
        assertFalse(dao.checkPhoneNumber(""));
        assertFalse(dao.checkPhoneNumber("+995558677895"));
        assertTrue(dao.checkPhoneNumber("+992358677895"));
        assertFalse(dao.checkPhoneNumber("+995558677895"));
        assertTrue(dao.checkPhoneNumber("+992358677895"));
        //CheckFacebookID
        assertFalse(dao.checkFacebookID(""));
        assertTrue(dao.checkFacebookID("asdfl2d"));
        assertFalse(dao.checkFacebookID("asdfa2d"));
        assertTrue(dao.checkFacebookID("asdfl2d"));
        assertFalse(dao.checkFacebookID("asdfa2d"));
        //CheckGoogleID
        assertFalse(dao.checkGoogleID(""));
        assertTrue(dao.checkGoogleID("asdfa3d"));
        assertFalse(dao.checkGoogleID("asdsdafrk"));
        assertTrue(dao.checkGoogleID("asdfa3d"));
        assertFalse(dao.checkGoogleID("asdsdafrk"));
    }
    private void compareUsers(User user,User user1){
        Gender g = user.getGender();
        Gender g1 = user1.getGender();
        UserPreference pref = user.getPreference();
        UserPreference pref1 = user1.getPreference();
        assertEquals(user.getPassword(),user1.getPassword());
        assertEquals(user.getEmail(),user1.getEmail());
        assertEquals(user.getPhoneNumber(),user1.getPhoneNumber());
        assertEquals(user.getFacebookID(),user1.getFacebookID());
        assertEquals(user.getGoogleID(),user1.getGoogleID());
        assertEquals(user.getFirstName(),user1.getFirstName());
        assertEquals(user.getLastName(),user1.getLastName());
        assertEquals(user.getUserID(),user1.getUserID());
        assertTrue(user.getRating()==user1.getRating());//assertEquals(double,double) depricated
        assertEquals(pref.getCarYear(),pref1.getCarYear());
        assertTrue(pref.getMinimumDriverRating()==pref1.getMinimumDriverRating());
        assertEquals(pref.getPassengersCount(),pref1.getPassengersCount());
        assertEquals(pref.getTimeLimit(),pref1.getTimeLimit());
        //assertEquals(pref.getUserPreferenceID(),usrp.getUserPreferenceID());
        assertEquals(pref.isConditioning(),pref1.isConditioning());
        assertEquals(pref.isWantsAlone(),pref1.isWantsAlone());
        assertEquals(g.toString(),g1.toString());
    }
    @Test
    public void testDriverDao(){
        DriverManager man = new DriverManager(new DriverDao());
        Driver driver = Mockito.mock(Driver.class);
        Car car = Mockito.mock(Car.class);
        DriverPreference pref = Mockito.mock(DriverPreference.class);
        Location l = new Location(new BigDecimal(2.234),new BigDecimal(3.1245));

        Gender gend = Gender.MALE;//mgoni
        when(driver.getFirstName()).thenReturn("gela");
        when(driver.getRating()).thenReturn(1.2);
        when(driver.getLastName()).thenReturn("magaltadze");
        when(driver.getPhoneNumber()).thenReturn("+995696996");
        when(driver.getCompanyID()).thenReturn(0);
        when(driver.getDriverID()).thenReturn(-1);
        when(driver.getEmail()).thenReturn("gela@taxistgela.ge");
        when(driver.getFacebookID()).thenReturn("gelandara95");
        when(driver.getGoogleID()).thenReturn("bozandara");
        when(driver.getPassword()).thenReturn("Madridista1");//actually gela-s parolia fbze(iyo adre :D)
        when(driver.getPersonalID()).thenReturn("01010101011");
        when(driver.getRating()).thenReturn(2.2);
        when(driver.isActive()).thenReturn(true);
        when(driver.isVerified()).thenReturn(true);
        when(driver.getLocation()).thenReturn(l);
        //Register + login test
        when(driver.getCar()).thenReturn(car);
        when(car.getCarYear()).thenReturn(1995);
        when(car.getCarDescription()).thenReturn("????");
        when(car.getCarID()).thenReturn("69dota95");
        when(car.getNumPassengers()).thenReturn(2);
        man.insertCar(car);
        when(driver.getGender()).thenReturn(gend);
        when(driver.getPreferences()).thenReturn(pref);
        when(pref.getCoefficientPer()).thenReturn(0.69);
        when(pref.getDriverPreferenceID()).thenReturn(-1);
        when(pref.getMinimumUserRating()).thenReturn(0.0);//gela bizatkaznia
        man.insertDriverPreference(pref);
        man.registerDriver(driver);
        Driver driver1=man.loginDriver(driver.getEmail(),driver.getPassword());
        compareDrivers(driver,driver1);
        //Register + login test
        comparePrefernces(man.getDriverPreferenceByID(pref.getDriverPreferenceID()),pref);
        /*Car car1 = man.getCarByID(car.getCarID());*/ //TODO NEEDS FIXING !!!!!!!!!!!!!!!!!!!GMERTCHEMAV!!!!!!!!!!!!!!!!
        //Update
        Driver driver2=Mockito.mock(Driver.class);
        when(driver2.getFirstName()).thenReturn("gelusi");
        when(driver2.getRating()).thenReturn(1.3);
        when(driver2.getLastName()).thenReturn("maghaltadze");
        when(driver2.getPhoneNumber()).thenReturn("+995696991");
        when(driver2.getCompanyID()).thenReturn(0);
        when(driver2.getDriverID()).thenReturn(-1);
        when(driver2.getEmail()).thenReturn("gela95@taxistgela.ge");
        when(driver2.getFacebookID()).thenReturn("gelandara95");
        when(driver2.getGoogleID()).thenReturn("bozandara");
        when(driver2.getPassword()).thenReturn("Madridista!");//rom gaigo rom paroli vicodit mere amaze gadaaketa
        when(driver2.getPersonalID()).thenReturn("01010101012");//gelam piradobis nomeri sheicvala B-)
        when(driver2.getRating()).thenReturn(2.3);
        when(driver2.isActive()).thenReturn(false);
        when(driver2.isVerified()).thenReturn(false);
        when(driver2.getLocation()).thenReturn(l);
        when(driver2.getCar()).thenReturn(car);
        when(car.getCarYear()).thenReturn(1996);//sabutebi gaayalba manqanis uket gasasageblad
        when(car.getCarDescription()).thenReturn("???? :3");
        when(car.getCarID()).thenReturn("95dota69");//manqanis nomerbi shecvala
        when(car.getNumPassengers()).thenReturn(3);//adgili daasvarka
        man.updateCar(car);
        gend=Gender.FEMALE;//wonders of today's surgeons
        when(driver2.getGender()).thenReturn(gend);
        when(driver2.getPreferences()).thenReturn(pref);
        when(pref.getCoefficientPer()).thenReturn(0.70);//gelam standartebi awia
        //when(pref.getDriverPreferenceID()).thenReturn(-1);
        when(pref.getMinimumUserRating()).thenReturn(0.1);//gela standartebi awia tqo
        man.updateDriverPreference(pref);
        man.updateDriver(driver2);
        man.updateCar(car);
        man.updateDriverPreference(pref);
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
            if(d.getDriverID()==driver2.getDriverID())
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
        assertTrue(dp1.getCoefficientPer() == dp2.getCoefficientPer());
        assertEquals(dp1.getDriverPreferenceID(),dp2.getDriverPreferenceID());
        assertTrue(dp1.getMinimumUserRating() == dp2.getMinimumUserRating());
    }
    private void compareDrivers(Driver driver,Driver driver1){
        Car car = driver.getCar();
        DriverPreference pref = driver.getPreferences();
        assertTrue(driver1.getRating() == driver.getRating());
        assertEquals(driver1.getDriverID(),driver.getDriverID());
        assertEquals(driver1.getCompanyID(),driver.getCompanyID());
        assertEquals(driver1.getEmail(),driver.getEmail());
        assertEquals(driver1.getFacebookID(),driver.getFacebookID());
        assertEquals(driver1.getFirstName(),driver.getFirstName());
        assertEquals(driver1.getLastName(),driver.getLastName());
        assertEquals(driver1.getGender().toString(),driver.getGender().toString());
        assertEquals(driver1.getGoogleID(),driver.getGoogleID());
        assertEquals(driver1.getPassword(),driver.getPassword());
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

    }
    @After
    public void cleanup(){
        /*AdminDatabase db = new AdminDatabase();
        try {
            db.recreateDatabase();
        }catch(Exception e){
            System.out.println(e.toString());
            assertTrue(false);
        }*/
    }
}