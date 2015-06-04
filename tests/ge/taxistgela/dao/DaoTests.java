package ge.taxistgela.dao;

import ge.taxistgela.bean.*;
import ge.taxistgela.helper.AdminDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
        assertEquals(company.getCompanyCode(),comp.getCompanyCode());
        assertEquals(company.getCompanyID(),comp.getCompanyID());
        assertEquals(company.getCompanyName(),comp.getCompanyName());
        assertEquals(company.getEmail(),comp.getEmail());
        assertEquals(company.getFacebookID(),comp.getFacebookID());
        assertEquals(company.getGoogleID(),comp.getGoogleID());
        assertEquals(company.getPhoneNumber(),comp.getPhoneNumber());
        assertEquals(company.getPassword(),comp.getPassword());
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
        assertEquals(company.getCompanyCode(),comp1.getCompanyCode());
        assertEquals(company.getCompanyID(),comp1.getCompanyID());
        assertEquals(company.getCompanyName(),comp1.getCompanyName());
        assertEquals(company.getEmail(),comp1.getEmail());
        assertEquals(company.getFacebookID(),comp1.getFacebookID());
        assertEquals(company.getGoogleID(),comp1.getGoogleID());
        assertEquals(company.getPhoneNumber(),comp1.getPhoneNumber());
        assertEquals(company.getPassword(),comp1.getPassword());
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
        Gender g = usr1.getGender();
        UserPreference pref = usr1.getPreference();
        assertEquals(usr1.getPassword(),usr.getPassword());
        assertEquals(usr1.getEmail(),usr.getEmail());
        assertEquals(usr1.getPhoneNumber(),usr.getPhoneNumber());
        assertEquals(usr1.getFacebookID(),usr.getFacebookID());
        assertEquals(usr1.getGoogleID(),usr.getGoogleID());
        assertEquals(usr1.getFirstName(),usr.getFirstName());
        assertEquals(usr1.getLastName(),usr.getLastName());
        assertEquals(usr1.getUserID(),usr.getUserID());
        assertTrue(usr1.getRating()==usr.getRating());//assertEquals(double,double) depricated
        assertEquals(pref.getCarYear(),usrp.getCarYear());
        assertTrue(pref.getMinimumDriverRating()==usrp.getMinimumDriverRating());
        assertEquals(pref.getPassengersCount(),usrp.getPassengersCount());
        assertEquals(pref.getTimeLimit(),usrp.getTimeLimit());
        //assertEquals(pref.getUserPreferenceID(),usrp.getUserPreferenceID());
        assertEquals(pref.isConditioning(),usrp.isConditioning());
        assertEquals(pref.isWantsAlone(),usrp.isWantsAlone());
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
        g = usr1.getGender();
        pref = usr1.getPreference();

        assertEquals(usr1.getPassword(),usr.getPassword());
        assertEquals(usr1.getEmail(),usr.getEmail());
        assertEquals(usr1.getPhoneNumber(),usr.getPhoneNumber());
        assertEquals(usr1.getFacebookID(),usr.getFacebookID());
        assertEquals(usr1.getGoogleID(),usr.getGoogleID());
        assertEquals(usr1.getFirstName(),usr.getFirstName());
        assertEquals(usr1.getLastName(),usr.getLastName());
        assertEquals(usr1.getUserID(),usr.getUserID());
        assertTrue(usr1.getRating()==usr.getRating());//assertEquals(double,double) depricated
        assertEquals(pref.getCarYear(),usrp.getCarYear());
        assertTrue(pref.getMinimumDriverRating()==usrp.getMinimumDriverRating());
        assertEquals(pref.getPassengersCount(),usrp.getPassengersCount());
        assertEquals(pref.getTimeLimit(),usrp.getTimeLimit());
        //assertEquals(pref.getUserPreferenceID(),usrp.getUserPreferenceID());
        assertEquals(pref.isConditioning(),usrp.isConditioning());
        assertEquals(pref.isWantsAlone(),usrp.isWantsAlone());

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
    @Test
    public void testDriverDao(){
        DriverDao dao = new DriverDao();
        Driver driver = Mockito.mock(Driver.class);
        Car car = Mockito.mock(Car.class);
        DriverPreference driverPreference = Mockito.mock(DriverPreference.class);
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