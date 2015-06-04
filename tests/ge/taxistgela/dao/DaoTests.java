package ge.taxistgela.dao;

import ge.taxistgela.bean.*;
import ge.taxistgela.helper.AdminDatabase;
import ge.taxistgela.model.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;

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
        CompanyDao dao = new CompanyDao();
        Company comp = Mockito.mock(Company.class);
        when(comp.getCompanyName()).thenReturn("taxistGela");
        when(comp.getEmail()).thenReturn("support@taxistgela.com");
        when(comp.getCompanyCode()).thenReturn("1234567890a");
        when(comp.getFacebookID()).thenReturn("facebookIDmock");
        when(comp.getGoogleID()).thenReturn("googleIDmock");
        when(comp.getPhoneNumber()).thenReturn("+995558677895");
        when(comp.getPassword()).thenReturn("1234qwerTy");
        when(comp.getCompanyID()).thenReturn(-1);
        dao.registerCompany(comp);
        dao.registerCompany(comp);
        //LoginTests
        Company company = dao.loginCompany(comp.getEmail(),comp.getPassword());
        compareCompanies(comp,company);
        //CheckTests
        //CheckEmail
        assertFalse(dao.checkEmail(""));
        assertTrue(dao.checkEmail("support@taxistgela.com"));
        assertFalse(dao.checkEmail("support1@taxistgela.com"));
        assertFalse(dao.checkEmail("support2@taxistgela.com"));
        //CheckGoogleID
        assertFalse(dao.checkGoogleID(""));
        assertTrue(dao.checkGoogleID("googleIDmock"));
        assertFalse(dao.checkGoogleID("asd"));
        assertTrue(dao.checkGoogleID("googleIDmock"));
        assertFalse(dao.checkGoogleID("a"));
        //CheckPhoneNumber
        assertFalse(dao.checkPhoneNumber(""));
        assertTrue(dao.checkPhoneNumber("+995558677895"));
        assertFalse(dao.checkPhoneNumber("+995558677805"));
        assertTrue(dao.checkPhoneNumber("+995558677895"));
        assertFalse(dao.checkPhoneNumber("+995558677815"));
        //CheckCompanyID
        assertFalse(dao.checkCompanyCode(""));
        assertTrue(dao.checkCompanyCode("1234567890a"));
        assertFalse(dao.checkPhoneNumber("123a5a7890a"));
        assertTrue(dao.checkCompanyCode("1234567890a"));
        assertFalse(dao.checkPhoneNumber("12345678900"));
        //CheckFacebook
        assertFalse(dao.checkFacebookID(""));
        assertTrue(dao.checkFacebookID("facebookIDmock"));
        assertFalse(dao.checkFacebookID("asd"));
        assertTrue(dao.checkFacebookID("facebookIDmock"));
        assertFalse(dao.checkFacebookID("afk"));
        //UpdateTest
        Company comp1 = Mockito.mock(Company.class);
        when(comp1.getCompanyName()).thenReturn("taxistGela1");
        when(comp1.getEmail()).thenReturn("support1@taxistgela.com");
        when(comp1.getCompanyCode()).thenReturn("1234567890a1");
        when(comp1.getFacebookID()).thenReturn("asd");
        when(comp1.getGoogleID()).thenReturn("googleIDmock1");
        when(comp1.getPhoneNumber()).thenReturn("+995558677892");
        when(comp1.getPassword()).thenReturn("1234qwerTy3");
        dao.updateCompany(comp1);
        //LOGIN
        company = dao.loginCompany(comp1.getEmail(),comp.getPassword());
        compareCompanies(comp,company);
        assertFalse(dao.checkEmail("support@taxistgela.com"));
        assertTrue(dao.checkEmail("support1@taxistgela.com"));
        assertFalse(dao.checkEmail("support2@taxistgela.com"));
        assertTrue(dao.checkEmail("support1@taxistgela.com"));
        //CheckGoogleID
        assertFalse(dao.checkGoogleID(""));
        assertFalse(dao.checkGoogleID("googleIDmock"));
        assertTrue(dao.checkGoogleID("googleIDmock1"));
        assertFalse(dao.checkGoogleID("a"));
        assertTrue(dao.checkGoogleID("googleIDmock1"));
        //CheckFacebook
        assertFalse(dao.checkFacebookID(""));
        assertFalse(dao.checkFacebookID("facebookIDmock"));
        assertTrue(dao.checkFacebookID("asd"));
        assertFalse(dao.checkFacebookID("afk"));
        assertTrue(dao.checkFacebookID("asd"));
        //CheckPhoneNumber
        assertFalse(dao.checkPhoneNumber(""));
        assertFalse(dao.checkPhoneNumber("+995558677815"));
        assertTrue(dao.checkPhoneNumber("+995558677892"));
        assertFalse(dao.checkPhoneNumber("+995558677895"));
        assertTrue(dao.checkPhoneNumber("+995558677892"));
        //CheckCompanyID
        assertFalse(dao.checkPhoneNumber(""));
        assertFalse(dao.checkPhoneNumber("1234567890a"));
        assertTrue(dao.checkCompanyCode("1234567890a1"));
        assertFalse(dao.checkPhoneNumber("gg wp"));
        assertTrue(dao.checkCompanyCode("1234567890a1"));
    }
    private void compareCompanies(Company comp,Company comp1){
        assertEquals(comp.getCompanyCode(),comp1.getCompanyCode());
        assertEquals(comp.getCompanyID(),comp1.getCompanyID());
        assertEquals(comp.getCompanyName(),comp1.getCompanyName());
        assertEquals(comp.getEmail(),comp1.getEmail());
        assertEquals(comp.getFacebookID(),comp1.getFacebookID());
        assertEquals(comp.getGoogleID(),comp1.getGoogleID());
        assertEquals(comp.getPhoneNumber(),comp1.getPhoneNumber());
        assertEquals(comp.getPassword(),comp1.getPassword());
    }
    @Test
    public void testUserDao(){
        UserDao dao = new UserDao();
        UserPreference usrp = Mockito.mock(UserPreference.class);
        when(usrp.getCarYear()).thenReturn(2013);
        when(usrp.getMinimumDriverRating()).thenReturn(2.3);
        when(usrp.getPassengersCount()).thenReturn(2);
        when(usrp.getTimeLimit()).thenReturn(30);
        when(usrp.isConditioning()).thenReturn(true);
        when(usrp.isWantsAlone()).thenReturn(false);
        usrp = Mockito.mock(UserPreference.class);
        User usr = Mockito.mock(User.class);
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
        when(driver.getGender()).thenReturn(gend);
        when(driver.getPreferences()).thenReturn(pref);
        when(pref.getCoefficientPer()).thenReturn(0.69);
        when(pref.getDriverPreferenceID()).thenReturn(-1);
        when(pref.getMinimumUserRating()).thenReturn(0.0);//gela bizatkaznia
        man.registerDriver(driver);
        Driver driver1=man.loginDriver(driver.getEmail(),driver.getPassword());
        compareDrivers(driver,driver1);
        //Update
        when(driver.getFirstName()).thenReturn("gelusi");
        when(driver.getRating()).thenReturn(1.3);
        when(driver.getLastName()).thenReturn("maghaltadze");
        when(driver.getPhoneNumber()).thenReturn("+995696991");
        when(driver.getCompanyID()).thenReturn(0);
        when(driver.getDriverID()).thenReturn(-1);
        when(driver.getEmail()).thenReturn("gela95@taxistgela.ge");
        when(driver.getFacebookID()).thenReturn("gelandara95");
        when(driver.getGoogleID()).thenReturn("bozandara");
        when(driver.getPassword()).thenReturn("Madridista!");//rom gaigo rom paroli vicodit mere amaze gadaaketa
        when(driver.getPersonalID()).thenReturn("01010101012");//gelam piradobis nomeri sheicvala B-)
        when(driver.getRating()).thenReturn(2.3);
        when(driver.isActive()).thenReturn(false);
        when(driver.isVerified()).thenReturn(false);
        when(driver.getLocation()).thenReturn(l);
        //Register + login test
        when(driver.getCar()).thenReturn(car);
        when(car.getCarYear()).thenReturn(1996);//sabutebi gaayalba manqanis uket gasasageblad
        when(car.getCarDescription()).thenReturn("???? :3");
        when(car.getCarID()).thenReturn("95dota69");//manqanis nomerbi shecvala
        when(car.getNumPassengers()).thenReturn(3);//adgili daasvarka
        gend=Gender.FEMALE;//wonders of today's surgeons
        when(driver.getGender()).thenReturn(gend);
        when(driver.getPreferences()).thenReturn(pref);
        when(pref.getCoefficientPer()).thenReturn(0.70);//gelam standartebi awia
        //when(pref.getDriverPreferenceID()).thenReturn(-1);
        when(pref.getMinimumUserRating()).thenReturn(0.1);//gela standartebi awia tqo
        man.updateDriver(driver);
        man.updateCar(car);
        man.updateDriverPreference(pref);
        //Login
        driver1=man.loginDriver(driver.getEmail(),driver.getPassword());
        compareDrivers(driver,driver1);
        //man.getCarByID(car.getCarID());
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