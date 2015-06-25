package ge.taxistgela.model;

import ge.taxistgela.bean.*;
import ge.taxistgela.dao.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

/**
 * Created by Ratmach on 7/6/15.
 */
public class ManagerTests{
    @Before
    public void setUp(){

    }
    @Test
    public void companyManagerTests(){
        CompanyDao dao = Mockito.mock(CompanyDao.class);
        CompanyManager man = new CompanyManager(dao);
        //CompanyCode
        when(dao.checkCompanyCode("123456789")).thenReturn(true);
        when(dao.checkCompanyCode("123456790")).thenReturn(true);
        when(dao.checkCompanyCode("123456791")).thenReturn(false);
        when(dao.checkCompanyCode("123456777")).thenReturn(true);
        when(dao.checkCompanyCode(null)).thenReturn(true);
        assertTrue(man.checkCompanyCode("123456789"));
        assertTrue(man.checkCompanyCode("123456790"));
        assertFalse(man.checkCompanyCode("123456791"));
        assertTrue(man.checkCompanyCode("123456777"));
        assertTrue(man.checkCompanyCode(null));
        //Email
        when(dao.checkEmail("asd@asd.com")).thenReturn(true);
        when(dao.checkEmail("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkEmail("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkEmail("123456777@gmail.com")).thenReturn(true);
        when(dao.checkEmail(null)).thenReturn(true);
        assertTrue(man.checkEmail(null));
        assertTrue(man.checkEmail("asd@asd.com"));
        assertTrue(man.checkEmail("gelaasd@gela.ge"));
        assertFalse(man.checkEmail("asdfkj@kasdasd.cz"));
        assertTrue(man.checkEmail("123456777@gmail.com"));
        //FaceBookID
        when(dao.checkFacebookID("asd@asd.com")).thenReturn(true);
        when(dao.checkFacebookID("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkFacebookID("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkFacebookID("123456777@gmail.com")).thenReturn(true);
        when(dao.checkFacebookID(null)).thenReturn(false);
        assertFalse(man.checkFacebookID(null));
        assertTrue(man.checkFacebookID("asd@asd.com"));
        assertTrue(man.checkFacebookID("gelaasd@gela.ge"));
        assertFalse(man.checkFacebookID("asdfkj@kasdasd.cz"));
        assertTrue(man.checkFacebookID("123456777@gmail.com"));
        //googleID
        when(dao.checkGoogleID("asd@asd.com")).thenReturn(true);
        when(dao.checkGoogleID("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkGoogleID("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkGoogleID("123456777@gmail.com")).thenReturn(true);
        when(dao.checkGoogleID(null)).thenReturn(false);
        assertTrue(man.checkGoogleID("asd@asd.com"));
        assertTrue(man.checkGoogleID("gelaasd@gela.ge"));
        assertFalse(man.checkGoogleID("asdfkj@kasdasd.cz"));
        assertTrue(man.checkGoogleID("123456777@gmail.com"));
        assertFalse(man.checkGoogleID(null));
        //CheckPhoneNumber
        when(dao.checkPhoneNumber("123456789")).thenReturn(true);
        when(dao.checkPhoneNumber("123456790")).thenReturn(true);
        when(dao.checkPhoneNumber("123456791")).thenReturn(false);
        when(dao.checkPhoneNumber("123456777")).thenReturn(true);
        when(dao.checkPhoneNumber(null)).thenReturn(false);
        assertFalse(man.checkPhoneNumber(null));
        assertTrue(man.checkPhoneNumber("123456789"));
        assertTrue(man.checkPhoneNumber("123456790"));
        assertFalse(man.checkPhoneNumber("123456791"));
        assertTrue(man.checkPhoneNumber("123456777"));
        //login check
        Company comp = new Company(-1, "123456789", "support@taxistgela.com", "1234qwerTy", "taxistGela", "558677895", "facebookIDmock", "googleIDmock", true, true);
        when(dao.loginCompany("123456789", "asdf")).thenReturn(comp);
        assertEquals(comp,dao.loginCompany("123456789", "asdf"));
        //register check?
        when(dao.registerCompany(comp)).thenReturn(false);
        assertEquals(man.register(comp).errorNotAccrued(), true);
        when(dao.registerCompany(comp)).thenReturn(true);
        assertEquals(man.register(comp).errorNotAccrued(), false);
        when(dao.registerCompany(anyObject())).thenReturn(false);
        comp.setEmail("hophophopla");
        when(dao.registerCompany(comp)).thenReturn(false);
        assertFalse(man.register(comp).errorNotAccrued());

        comp.setEmail("rame@mail.ru");
        assertFalse(man.register(null).errorNotAccrued());
        // update company
        when(dao.updateCompany(comp)).thenReturn(true);
        assertEquals(man.update(comp), true);
        when(dao.updateCompany(comp)).thenReturn(false);
        assertEquals(man.update(comp), false);
        when(dao.updateCompany(anyObject())).thenReturn(false);
        comp.setEmail("hophophopla");
        when(dao.updateCompany(comp)).thenReturn(false);
        assertFalse(man.update(comp).errorNotAccrued());
        assertTrue(man.update(null).errorNotAccrued());
    }
    @Test
    public void driverManagerTests(){
        DriverDao dao = Mockito.mock(DriverDao.class);
        DriverManager man = new DriverManager(dao);
        //Email
        when(dao.checkEmail("asd@asd.com")).thenReturn(true);
        when(dao.checkEmail("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkEmail("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkEmail("123456777@gmail.com")).thenReturn(true);
        when(dao.checkEmail(null)).thenReturn(true);
        assertTrue(man.checkEmail(null));
        assertTrue(man.checkEmail("asd@asd.com"));
        assertTrue(man.checkEmail("gelaasd@gela.ge"));
        assertFalse(man.checkEmail("asdfkj@kasdasd.cz"));
        assertTrue(man.checkEmail("123456777@gmail.com"));
        //FaceBookID
        when(dao.checkFacebookID("asd@asd.com")).thenReturn(true);
        when(dao.checkFacebookID("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkFacebookID("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkFacebookID("123456777@gmail.com")).thenReturn(true);
        when(dao.checkFacebookID(null)).thenReturn(false);
        assertFalse(man.checkFacebookID(null));
        assertTrue(man.checkFacebookID("asd@asd.com"));
        assertTrue(man.checkFacebookID("gelaasd@gela.ge"));
        assertFalse(man.checkFacebookID("asdfkj@kasdasd.cz"));
        assertTrue(man.checkFacebookID("123456777@gmail.com"));
        //googleID
        when(dao.checkGoogleID("asd@asd.com")).thenReturn(true);
        when(dao.checkGoogleID("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkGoogleID("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkGoogleID("123456777@gmail.com")).thenReturn(true);
        when(dao.checkGoogleID(null)).thenReturn(false);
        assertFalse(man.checkGoogleID(null));
        assertTrue(man.checkGoogleID("asd@asd.com"));
        assertTrue(man.checkGoogleID("gelaasd@gela.ge"));
        assertFalse(man.checkGoogleID("asdfkj@kasdasd.cz"));
        assertTrue(man.checkGoogleID("123456777@gmail.com"));
        //CheckPhoneNumber
        when(dao.checkPhoneNumber("123456789")).thenReturn(true);
        when(dao.checkPhoneNumber(null)).thenReturn(false);
        when(dao.checkPhoneNumber("123456790")).thenReturn(true);
        when(dao.checkPhoneNumber("123456791")).thenReturn(false);
        when(dao.checkPhoneNumber("123456777")).thenReturn(true);
        assertFalse(man.checkPhoneNumber(null));
        assertTrue(man.checkPhoneNumber("123456789"));
        assertTrue(man.checkPhoneNumber("123456790"));
        assertFalse(man.checkPhoneNumber("123456791"));
        assertTrue(man.checkPhoneNumber("123456777"));
        //Car plate check
        when(dao.checkCarID("aaa000aaa")).thenReturn(true);
        when(dao.checkCarID("aaa010aaa")).thenReturn(true);
        when(dao.checkCarID("aaa020aaa")).thenReturn(false);
        when(dao.checkCarID("aaa030aaa")).thenReturn(true);
        when(dao.checkCarID(null)).thenReturn(true);
        assertTrue(man.checkCarID(null));
        assertTrue(man.checkCarID("aaa000aaa"));
        assertTrue(man.checkCarID("aaa010aaa"));
        assertFalse(man.checkCarID("aaa020aaa"));
        assertTrue(man.checkCarID("aaa030aaa"));
        //getDriverByPreferences check
        when(dao.getDriverByPreferences(null)).thenReturn(null);
        assertNull(man.getDriverByPreferences(null));
        ArrayList<Driver> list = new ArrayList<Driver>();
        list.add(new Driver());
        list.add(new Driver());
        when(dao.getDriverByPreferences(anyObject())).thenReturn(list);
        User user = new User();
        assertEquals(list, man.getDriverByPreferences(user));
        assertEquals(list, man.getDriverByPreferences(null));
        verify(dao, times(1)).getDriverByPreferences(user);
        when(dao.getDriverByPreferences(anyObject())).thenReturn(null);
        assertNull(man.getDriverByPreferences(null));
        List list2 = new ArrayList<Driver>();
        User user2 = new User();
        user2.setEmail("gela@gmail.com");
        list.add(new Driver());
        when(dao.getDriverByPreferences(user)).thenReturn(list);
        when(dao.getDriverByPreferences(user2)).thenReturn(list2);
        assertEquals(list, man.getDriverByPreferences(user));
        assertEquals(list2, man.getDriverByPreferences(user2));
        assertNotEquals(list, man.getDriverByPreferences(user2));
        assertNotEquals(list2, man.getDriverByPreferences(user));
        //updateDriverPreference
        when(dao.updateDriverPreference(null)).thenReturn(false);
        assertFalse(man.updateDriverPreference(null).errorNotAccrued());
        DriverPreference dp1 = new DriverPreference();
        dp1.setCoefficientPer(1.78);
        DriverPreference dp2 = new DriverPreference();
        dp2.setDriverPreferenceID(1);
        when(dao.updateDriverPreference(anyObject())).thenReturn(false);
        assertFalse(man.updateDriverPreference(dp1).errorNotAccrued());
        assertFalse(man.updateDriverPreference(dp2).errorNotAccrued());
        when(dao.updateDriverPreference(anyObject())).thenReturn(true);
        assertTrue(man.updateDriverPreference(dp1).errorNotAccrued());
        assertTrue(man.updateDriverPreference(dp2).errorNotAccrued());
        when(dao.updateDriverPreference(dp1)).thenReturn(true);
        when(dao.updateDriverPreference(dp2)).thenReturn(false);
        assertTrue(man.updateDriverPreference(dp1).errorNotAccrued());
        assertFalse(man.updateDriverPreference(dp2).errorNotAccrued());
        //insertDriverPreference
        when(dao.insertDriverPreference(anyObject())).thenReturn(true);
        assertTrue(man.insertDriverPreference(new DriverPreference()).errorNotAccrued());
        assertTrue(man.insertDriverPreference(null).errorNotAccrued());
        when(dao.insertDriverPreference(anyObject())).thenReturn(false);
        assertFalse(man.insertDriverPreference(new DriverPreference()).errorNotAccrued());
        assertFalse(man.insertDriverPreference(null).errorNotAccrued());
        //getDriverPreferenceByIDs
        DriverPreference dp = new DriverPreference();
        when(dao.getDriverPreferenceByID(anyInt())).thenReturn(dp);
        assertEquals(dp, man.getDriverPreferenceByID(1));
        DriverPreference DP1 = new DriverPreference();
        DP1.setDriverPreferenceID(1);
        DriverPreference DP2 = new DriverPreference();
        DP2.setDriverPreferenceID(2);
        DriverPreference DP3 = new DriverPreference();
        DP3.setDriverPreferenceID(3);
        when(dao.getDriverPreferenceByID(1)).thenReturn(DP1);
        when(dao.getDriverPreferenceByID(2)).thenReturn(DP2);
        when(dao.getDriverPreferenceByID(3)).thenReturn(DP3);
        assertEquals(DP1, man.getDriverPreferenceByID(1));
        assertEquals(DP2, man.getDriverPreferenceByID(2));
        assertEquals(DP3, man.getDriverPreferenceByID(3));
        //updateCar
        when(dao.updateCar(anyObject())).thenReturn(false);
        assertFalse(man.updateCar(new Car()).errorNotAccrued());
        assertFalse(man.updateCar(null).errorNotAccrued());
        when(dao.updateCar(anyObject())).thenReturn(true);
        assertTrue(man.updateCar(new Car()).errorNotAccrued());
        assertTrue(man.updateCar(null).errorNotAccrued());
        //insertCar
        when(dao.insertCar(anyObject())).thenReturn(false);
        assertFalse(man.insertCar(new Car()).errorNotAccrued());
        assertFalse(man.insertCar(null).errorNotAccrued());
        when(dao.insertCar(anyObject())).thenReturn(true);
        assertTrue(man.insertCar(new Car()).errorNotAccrued());
        assertTrue(man.insertCar(null).errorNotAccrued());
        //getCarByID
        Car c1 = new Car();
        c1.setCarID("1");
        Car c2 = new Car();
        c1.setCarID("2");
        Car c3 = new Car();
        c1.setCarID("3");
        when(dao.getCarByID("1")).thenReturn(c1);
        when(dao.getCarByID("2")).thenReturn(c2);
        when(dao.getCarByID("3")).thenReturn(c3);
        assertEquals(c1, man.getCarByID("1"));
        assertEquals(c2, man.getCarByID("2"));
        assertEquals(c3, man.getCarByID("3"));
        when(dao.getCarByID("1")).thenReturn(c1);
        when(dao.getCarByID("2")).thenReturn(c1);
        when(dao.getCarByID("3")).thenReturn(c1);
        assertEquals(c1, man.getCarByID("1"));
        assertEquals(c1, man.getCarByID("2"));
        assertNotEquals(c3, man.getCarByID("3"));
        //updateDriver
        Driver d = new Driver();
        d.setEmail("x@gmail.com");
        d.setPhoneNumber("599029302");
        d.setPassword("huhu");
        when(dao.updateDriver(anyObject())).thenReturn(false);
        assertTrue(man.update(null).errorNotAccrued());
        assertFalse(man.update(d).errorNotAccrued());
        when(dao.updateDriver(anyObject())).thenReturn(true);
        assertTrue(man.update(d).errorNotAccrued());
        d.setEmail("mail.ru");
        when(dao.updateDriver(d)).thenReturn(false);
        assertTrue(man.update(d).errorNotAccrued());
        //registerDriver
        when(dao.registerDriver(anyObject())).thenReturn(false);
        Driver driver = new Driver();
        driver.setEmail("tornikeman@gmail.com");
        driver.setPhoneNumber("593499023");
        driver.setPassword("gela");
        assertTrue(man.register(null).errorNotAccrued());
        assertFalse(man.register(driver).errorNotAccrued());
        when(dao.registerDriver(anyObject())).thenReturn(true);
        assertTrue(man.register(driver).errorNotAccrued());
        when(dao.registerDriver(anyObject())).thenReturn(false);
        driver.setPassword("");
        when(dao.registerDriver(driver)).thenReturn(false);
        assertTrue(man.register(driver).errorNotAccrued());
        assertTrue(man.register(null).errorNotAccrued());
        //loginDriver
        when(dao.loginDriver(null, null)).thenReturn(null);
        assertNull(dao.loginDriver(null, null));
        Driver driver1 = new Driver();
        driver1.setEmail("geluka@gmail.com");
        driver1.setFacebookID("gelucha@gmail.com");
        Driver driver2 = new Driver();
        driver2.setEmail("tornikeman@gmail.com");
        driver2.setFacebookID("tornike_mandzulashvili");
        when(dao.loginDriver("gelanasha@gmail.com", "gelaSuntqavs")).thenReturn(driver1);
        when(dao.loginDriver("tornikeman@gmail.com", "araashenisaqme")).thenReturn(driver2);
        assertEquals(driver1, man.login("gelanasha@gmail.com", "gelaSuntqavs"));
        assertEquals(driver2, man.login("tornikeman@gmail.com", "araashenisaqme"));
        when(dao.loginDriver(anyObject(), anyObject())).thenReturn(driver1);
        when(dao.loginDriver("tornikeman@gmail.com" , "jemali")).thenReturn(driver2);
        assertEquals(driver1, man.login("gelanasha@gmail.com", "avoeeeee"));
        assertEquals(driver2, man.login("tornikeman@gmail.com", "jemali"));
        assertEquals(driver1, man.login("tornikeman@gmail.com", "jemalii"));
        //getDriverByCompanyID
        ArrayList<Driver> arr1 = new ArrayList<>();
        arr1.add(new Driver());
        ArrayList<Driver> arr2 = new ArrayList<>();
        arr2.add(new Driver());
        arr2.add(new Driver());
        ArrayList<Driver> arr3 = new ArrayList<>();
        arr3.add(new Driver());
        arr3.add(new Driver());
        arr3.add(new Driver());
        when(dao.getDriverByCompanyID(1)).thenReturn(arr1);
        when(dao.getDriverByCompanyID(2)).thenReturn(arr2);
        when(dao.getDriverByCompanyID(3)).thenReturn(arr3);
        assertEquals(arr1, man.getDriverByCompanyID(1));
        assertEquals(arr2, man.getDriverByCompanyID(2));
        assertEquals(arr3, man.getDriverByCompanyID(3));
        when(dao.getDriverByCompanyID(1)).thenReturn(arr3);
        when(dao.getDriverByCompanyID(2)).thenReturn(arr3);
        when(dao.getDriverByCompanyID(3)).thenReturn(arr3);
        assertEquals(arr3, man.getDriverByCompanyID(1));
        assertNotEquals(arr2, man.getDriverByCompanyID(2));
        assertEquals(arr3, man.getDriverByCompanyID(3));
        when(dao.getDriverByCompanyID(anyInt())).thenReturn(arr1);
        assertEquals(arr1, man.getDriverByCompanyID(10000));
        assertNotEquals(arr2, man.getDriverByCompanyID(2));
        assertEquals(arr1, man.getDriverByCompanyID(-5));
        //getDriverByID
        Driver d1 = new Driver();
        d1.setFirstName("gela");
        Driver d2 = new Driver();
        d2.setFirstName("tornike");
        Driver d3 = new Driver();
        d2.setFirstName("zoro");
        Driver d4 = new Driver();
        d2.setFirstName("dimaria");
        when(dao.getDriverByID(1)).thenReturn(d1);
        when(dao.getDriverByID(2)).thenReturn(d2);
        when(dao.getDriverByID(3)).thenReturn(d3);
        when(dao.getDriverByID(4)).thenReturn(d4);
        assertEquals(d1, man.getByID(1));
        assertEquals(d2, man.getByID(2));
        assertEquals(d3, man.getByID(3));
        assertEquals(d4, man.getByID(4));
        when(dao.getDriverByID(anyInt())).thenReturn(d1, d2);
        assertEquals(d1, man.getByID(1));
        assertEquals(d2, man.getByID(2));
        assertEquals(d2, man.getByID(3));
        assertEquals(d2, man.getByID(1));
    }
    @Test
    public void orderManagerTests(){
        OrderDao dao = Mockito.mock(OrderDao.class);
        OrderManager man = new OrderManager(dao);
        Order order1, order2, order3;
        // addOrder
        order1 = new Order();
        order2 = new Order();
        order3 = new Order();
        order1.setUserID(1);
        order2.setUserID(2);
        order3.setUserID(3);
        when(dao.addOrder(order1)).thenReturn(true);
        assertTrue(man.addOrder(order1));
        when(dao.addOrder(order2)).thenReturn(false);
        assertFalse(man.addOrder(order2));
        when(dao.addOrder(null)).thenReturn(false);
        assertFalse(man.addOrder(null));
        //updateOrder
        when(dao.updateOrder(order1)).thenReturn(true);
        assertTrue(man.updateOrder(order1));
        when(dao.updateOrder(order2)).thenReturn(false);
        assertFalse(man.updateOrder(order2));
        when(dao.updateOrder(null)).thenReturn(false);
        assertFalse(man.updateOrder(null));
        //getOrderByID
        when(dao.getOrderByID(1)).thenReturn(order1);
        when(dao.getOrderByID(2)).thenReturn(order2);
        when(dao.getOrderByID(3)).thenReturn(order3);
        assertEquals(order1, man.getOrderByID(1));
        assertEquals(order2, man.getOrderByID(2));
        assertEquals(order3, man.getOrderByID(3));
        when(dao.getOrderByID(anyInt())).thenReturn(null, order1, order2, order3);
        assertNull(man.getOrderByID(3));
        assertEquals(order1, man.getOrderByID(3));
        assertEquals(order2, man.getOrderByID(2));
        assertEquals(order3, man.getOrderByID(1));
        assertEquals(order3, man.getOrderByID(-1));
        //getOrderByUserID
        ArrayList<Order> arr1 = new ArrayList<>();
        arr1.add(order1);
        ArrayList<Order> arr2 = new ArrayList<>();
        arr2.add(order1);
        arr2.add(order2);
        ArrayList<Order> arr3 = new ArrayList<>();
        arr3.add(order1);
        arr3.add(order2);
        arr3.add(order3);
        when(dao.getOrderByUserID(1)).thenReturn(arr1);
        when(dao.getOrderByUserID(10)).thenReturn(arr2);
        when(dao.getOrderByUserID(100)).thenReturn(arr3);
        assertEquals(arr3, man.getOrderByUserID(100));
        assertEquals(arr2, man.getOrderByUserID(10));
        assertEquals(arr1, man.getOrderByUserID(1));
        when(dao.getOrderByUserID(anyInt())).thenReturn(null, arr1, arr2, arr3);
        assertNull(man.getOrderByUserID(1));
        assertEquals(arr1, man.getOrderByUserID(1000));
        assertEquals(arr2, man.getOrderByUserID(-1));
        assertEquals(arr3, man.getOrderByUserID(10));
        assertEquals(arr3, man.getOrderByUserID(1));
        //getOrdersByDriverID
        when(dao.getOrdersByDriverID(1)).thenReturn(arr1);
        when(dao.getOrdersByDriverID(10)).thenReturn(arr2);
        when(dao.getOrdersByDriverID(100)).thenReturn(arr3);
        assertEquals(arr3, man.getOrdersByDriverID(100));
        assertEquals(arr2, man.getOrdersByDriverID(10));
        assertEquals(arr1, man.getOrdersByDriverID(1));
        when(dao.getOrdersByDriverID(anyInt())).thenReturn(null, arr1, arr2, arr3);
        assertNull(man.getOrdersByDriverID(1));
        assertEquals(arr1, man.getOrdersByDriverID(1000));
        assertEquals(arr2, man.getOrdersByDriverID(-1));
        assertEquals(arr3, man.getOrdersByDriverID(10));
        assertEquals(arr3, man.getOrdersByDriverID(1));
    }
    @Test
    public void reviewManagerTests(){
        ReviewDao dao = Mockito.mock(ReviewDao.class);
        ReviewManager man = new ReviewManager(dao);
        //addReview
        Review r1 = new Review();
        r1.setOrderID(1);
        Review r2 = new Review();
        r1.setOrderID(10);
        when(dao.addReview(r1)).thenReturn(true);
        assertTrue(man.addReview(r1));
        when(dao.addReview(r2)).thenReturn(false);
        assertFalse(man.addReview(r2));
        when(dao.addReview(null)).thenReturn(true);
        assertTrue(man.addReview(null));
        //updateReview
        when(dao.updateReview(r1)).thenReturn(true);
        assertTrue(man.updateReview(r1));
        when(dao.updateReview(r2)).thenReturn(false);
        assertFalse(man.updateReview(r2));
        when(dao.updateReview(null)).thenReturn(false);
        assertFalse(dao.updateReview(null));
        //getReviewByID
        when(dao.getReviewByID(1000)).thenReturn(r1);
        when(dao.getReviewByID(2000)).thenReturn(r2);
        assertEquals(r2, man.getReviewByID(2000));
        assertEquals(r1, man.getReviewByID(1000));
        when(dao.getReviewByID(anyInt())).thenReturn(r1, r2);
        assertEquals(r1, man.getReviewByID(2000));
        assertEquals(r2, man.getReviewByID(1000));
        assertEquals(r2, man.getReviewByID(2000));
        //getReviewByUserID
        Review r3 = new Review();
        r3.setOrderID(3);
        ArrayList<Review> arr3 = new ArrayList<>();
        ArrayList<Review> arr2 = new ArrayList<>();
        ArrayList<Review> arr1 = new ArrayList<>();
        arr3.add(r1);
        arr3.add(r2);
        arr3.add(r3);
        arr2.add(r1);
        arr2.add(r2);
        arr1.add(r1);
        when(dao.getReviewByUserID(3)).thenReturn(arr1);
        when(dao.getReviewByUserID(2)).thenReturn(arr2);
        when(dao.getReviewByUserID(1)).thenReturn(arr3);
        assertEquals(arr3, man.getReviewByUserID(1));
        assertEquals(arr2, man.getReviewByUserID(2));
        assertEquals(arr1, man.getReviewByUserID(3));
        when(dao.getReviewByUserID(anyInt())).thenReturn(arr1, arr2, null);
        assertEquals(arr1, man.getReviewByUserID(-1));
        assertEquals(arr2, man.getReviewByUserID(-2));
        assertNull(man.getReviewByUserID(3));
        assertNull(man.getReviewByUserID(-3));
        //getReviewByDriverID
        when(dao.getReviewByDriverID(3)).thenReturn(arr1);
        when(dao.getReviewByDriverID(2)).thenReturn(arr2);
        when(dao.getReviewByDriverID(1)).thenReturn(arr3);
        assertEquals(arr3, man.getReviewByDriverID(1));
        assertEquals(arr2, man.getReviewByDriverID(2));
        assertEquals(arr1, man.getReviewByDriverID(3));
        when(dao.getReviewByDriverID(anyInt())).thenReturn(arr1, arr2, null);
        assertEquals(arr1, man.getReviewByDriverID(-1));
        assertEquals(arr2, man.getReviewByDriverID(-2));
        assertNull(man.getReviewByDriverID(3));
        assertNull(man.getReviewByDriverID(-3));
    }
    @Test
    public void sessionManagerTests() {
        SessionManagerAPI sm = new SessionManager();

        // test add
        Session session1 = mock(Session.class);
        RemoteEndpoint.Async remote1 = mock(RemoteEndpoint.Async.class);
        when(session1.getAsyncRemote()).thenReturn(remote1);
        when(session1.isOpen()).thenReturn(true);

        sm.addSession(SessionManager.USER_SESSION, "geloidi", session1);

        sm.sendMessage(SessionManager.USER_SESSION, "geloidi", "gela var");
        verify(remote1).sendText("gela var");

        Session session2 = mock(Session.class);
        RemoteEndpoint.Async remote2 = mock(RemoteEndpoint.Async.class);
        when(session2.getAsyncRemote()).thenReturn(remote2);
        when(session2.isOpen()).thenReturn(true);

        sm.sendMessage(SessionManager.DRIVER_SESSION, "taxisti geloidi", "taxisti var");
        verify(remote2, never()).sendText("taxisti var");

        sm.addSession(SessionManager.DRIVER_SESSION, "taxisti geloidi", session2);

        sm.sendMessage(SessionManager.DRIVER_SESSION, "taxisti geloidi", "taxisti var");
        verify(remote2).sendText("taxisti var");

        // adding invalid remote.
        // checking closed session.
        Session session3 = mock(Session.class);
        RemoteEndpoint.Async remote3 = mock(RemoteEndpoint.Async.class);
        when(session3.getAsyncRemote()).thenReturn(remote3);
        when(session3.isOpen()).thenReturn(false);

        sm.addSession(10, "ar var gela", session3);

        sm.sendMessage(10, "ar var gela", "haha");
        verify(remote3, never()).sendText(anyString());

        sm.addSession(SessionManager.USER_SESSION, "ar var gela", session3);

        sm.sendMessage(SessionManager.USER_SESSION, "ar var gela", "haha");
        verify(remote3, never()).sendText(anyString());

        // test remove
        sm.removeSession(SessionManager.USER_SESSION, "geloidi");

        sm.sendMessage(SessionManager.USER_SESSION, "geloidi", "gela var");
        verify(remote1).sendText("gela var");

        sm.removeSession(SessionManager.DRIVER_SESSION, "taxisti geloidi");

        sm.sendMessage(SessionManager.DRIVER_SESSION, "taxisti geloidi", "taxisti var");
        verify(remote2).sendText("taxisti var");

        // removing invalid remote.
        sm.removeSession(10, "ar var gela");

        sm.sendMessage(10, "ar var gela", "haha");
        verify(remote3, never()).sendText(anyString());
    }

    @Test
    public void taxUserQueueTests(){
        TaxUserQueue queue = new TaxUserQueue(); // Same Goes Here
        // TODO
    }
    @Test
    public void userManagerTests(){
        UserDao dao = Mockito.mock(UserDao.class);
        UserManagerManager man = new UserManagerManager(dao);
        //Email
        when(dao.checkEmail("asd@asd.com")).thenReturn(true);
        when(dao.checkEmail("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkEmail("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkEmail("123456777@gmail.com")).thenReturn(true);
        when(dao.checkEmail(null)).thenReturn(false);
        assertFalse(man.checkEmail(null));
        assertTrue(man.checkEmail("asd@asd.com"));
        assertTrue(man.checkEmail("gelaasd@gela.ge"));
        assertFalse(man.checkEmail("asdfkj@kasdasd.cz"));
        assertTrue(man.checkEmail("123456777@gmail.com"));
        //FaceBookID
        when(dao.checkFacebookID("asd@asd.com")).thenReturn(true);
        when(dao.checkFacebookID("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkFacebookID("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkFacebookID("123456777@gmail.com")).thenReturn(true);
        when(dao.checkFacebookID(null)).thenReturn(false);
        assertFalse(man.checkFacebookID(null));
        assertTrue(man.checkFacebookID("asd@asd.com"));
        assertTrue(man.checkFacebookID("gelaasd@gela.ge"));
        assertFalse(man.checkFacebookID("asdfkj@kasdasd.cz"));
        assertTrue(man.checkFacebookID("123456777@gmail.com"));
        //googleID
        when(dao.checkGoogleID("asd@asd.com")).thenReturn(true);
        when(dao.checkGoogleID("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkGoogleID("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkGoogleID("123456777@gmail.com")).thenReturn(true);
        when(dao.checkGoogleID(null)).thenReturn(true);
        assertTrue(man.checkGoogleID(null));
        assertTrue(man.checkGoogleID("asd@asd.com"));
        assertTrue(man.checkGoogleID("gelaasd@gela.ge"));
        assertFalse(man.checkGoogleID("asdfkj@kasdasd.cz"));
        assertTrue(man.checkGoogleID("123456777@gmail.com"));
        //CheckPhoneNumber
        when(dao.checkPhoneNumber("123456789")).thenReturn(true);
        when(dao.checkPhoneNumber("123456790")).thenReturn(true);
        when(dao.checkPhoneNumber("123456791")).thenReturn(false);
        when(dao.checkPhoneNumber("123456777")).thenReturn(true);
        when(dao.checkPhoneNumber(null)).thenReturn(true);
        assertTrue(man.checkPhoneNumber(null));
        assertTrue(man.checkPhoneNumber("123456789"));
        assertTrue(man.checkPhoneNumber("123456790"));
        assertFalse(man.checkPhoneNumber("123456791"));
        assertTrue(man.checkPhoneNumber("123456777"));
        //getUsersByPreferences
        // TODO
        //updateUserPreference
        UserPreference up1 = new UserPreference();
        up1.setCarYear(2015);
        UserPreference up2 = new UserPreference();
        up2.setCarYear(2012);
        when(dao.updateUserPreference(up1)).thenReturn(false);
        assertFalse(man.updateUserPreference(up1).errorNotAccrued());
        when(dao.updateUserPreference(up2)).thenReturn(true);
        assertTrue(man.updateUserPreference(up2).errorNotAccrued());
        when(dao.updateUserPreference(anyObject())).thenReturn(true);
        assertTrue(man.updateUserPreference(null).errorNotAccrued());
        //insertUserPreference
        when(dao.insertUserPreference(up1)).thenReturn(false);
        assertFalse(man.insertUserPreference(up1).errorNotAccrued());
        when(dao.insertUserPreference(up2)).thenReturn(true);
        assertTrue(man.insertUserPreference(up2).errorNotAccrued());
        when(dao.insertUserPreference(null)).thenReturn(true);
        assertTrue(man.insertUserPreference(null).errorNotAccrued());
        //getUserPreferenceByID
        when(dao.getUserPreferenceByID(1)).thenReturn(up1);
        when(dao.getUserPreferenceByID(2)).thenReturn(up2);
        when(dao.getUserPreferenceByID(3)).thenReturn(null);
        assertEquals(up1, man.getUserPreferenceByID(1));
        assertEquals(up2, man.getUserPreferenceByID(2));
        assertNull(man.getUserPreferenceByID(3));
        when(dao.getUserPreferenceByID(anyInt())).thenReturn(up1, up1, up2);
        assertEquals(up1, man.getUserPreferenceByID(10));
        assertEquals(up1, man.getUserPreferenceByID(20));
        assertEquals(up2, man.getUserPreferenceByID(10));
        assertEquals(up2, man.getUserPreferenceByID(20));
        //updateUser
        User u1 = new User();
        u1.setFirstName("gela");
        u1.setPassword("u");
        u1.setEmail("buzuzu@gmail.com");
        u1.setPhoneNumber("599999900");
        User u2 = new User();
        u2.setFirstName("gelovichi");
        u2.setPassword("uff");
        u2.setEmail("buzuzuuu@gmail.com");
        u2.setPhoneNumber("598999900");
        User u3 = new User();
        u3.setPassword("");
        u3.setEmail("buzuzuuu@gmail.com");
        u3.setPhoneNumber("598999900");
        when(dao.updateUser(u1)).thenReturn(true);
        when(dao.updateUser(u2)).thenReturn(false);
        when(dao.updateUser(u3)).thenReturn(false);
        assertTrue(man.update(u1).errorNotAccrued());
        assertTrue(man.update(u3).errorNotAccrued());
        assertFalse(man.update(u2).errorNotAccrued());
        when(dao.updateUser(anyObject())).thenReturn(false);
        assertTrue(man.update(null).errorNotAccrued());
        //registerUser
        u3 = new User();
        u3.setPassword("gela");
        u3.setEmail("dafuq@gmail.com");
        u3.setPhoneNumber("5550304011");
        when(dao.registerUser(u1)).thenReturn(true);
        when(dao.registerUser(u2)).thenReturn(false);
        when(dao.registerUser(u3)).thenReturn(false);
        assertTrue(man.register(u3).errorNotAccrued());
        assertTrue(man.register(u1).errorNotAccrued());
        assertFalse(man.register(u2).errorNotAccrued());
        when(dao.registerUser(anyObject())).thenReturn(false);
        assertTrue(man.register(null).errorNotAccrued());
        //loginUser
        when(dao.loginUser("tornikeman@gmail.com","yey")).thenReturn(u2);
        when(dao.loginUser("tmand13@freeuni.edu.ge","something")).thenReturn(u1);
        assertEquals(u1, man.login("tmand13@freeuni.edu.ge", "something"));
        assertEquals(u2, man.login("tornikeman@gmail.com", "yey"));
        when(dao.loginUser(null, null)).thenReturn(null);
        assertNull(man.login(null, null));
        //getUserByID
        when(dao.getUserByID(1)).thenReturn(u1);
        when(dao.getUserByID(2)).thenReturn(u2);
        when(dao.getUserByID(3)).thenReturn(u2);
        assertEquals(u2, man.getByID(2));
        assertEquals(u2, man.getByID(3));
        assertEquals(u1, man.getByID(1));
        when(dao.getUserByID(anyInt())).thenReturn(u1, u2);
        assertEquals(u1, man.getByID(10));
        assertEquals(u2, man.getByID(20));
        assertEquals(u2, man.getByID(30));
    }

    @After
    public void cleanUp(){

    }
}
