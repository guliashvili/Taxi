package ge.taxistgela.model;

import ge.taxistgela.bean.Company;
import ge.taxistgela.dao.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 * Created by Ratmach on 7/6/15.
 */
public class ManagerTests extends TestCase {
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
        assertTrue(man.checkCompanyCode("123456789"));
        assertTrue(man.checkCompanyCode("123456790"));
        assertFalse(man.checkCompanyCode("123456791"));
        assertTrue(man.checkCompanyCode("123456777"));
        //Email
        when(dao.checkEmail("asd@asd.com")).thenReturn(true);
        when(dao.checkEmail("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkEmail("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkEmail("123456777@gmail.com")).thenReturn(true);
        assertTrue(man.checkEmail("asd@asd.com"));
        assertTrue(man.checkEmail("gelaasd@gela.ge"));
        assertFalse(man.checkEmail("asdfkj@kasdasd.cz"));
        assertTrue(man.checkEmail("123456777@gmail.com"));
        //FaceBookID
        when(dao.checkFacebookID("asd@asd.com")).thenReturn(true);
        when(dao.checkFacebookID("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkFacebookID("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkFacebookID("123456777@gmail.com")).thenReturn(true);
        assertTrue(man.checkFacebookID("asd@asd.com"));
        assertTrue(man.checkFacebookID("gelaasd@gela.ge"));
        assertFalse(man.checkFacebookID("asdfkj@kasdasd.cz"));
        assertTrue(man.checkFacebookID("123456777@gmail.com"));
        //googleID
        when(dao.checkGoogleID("asd@asd.com")).thenReturn(true);
        when(dao.checkGoogleID("gelaasd@gela.ge")).thenReturn(true);
        when(dao.checkGoogleID("asdfkj@kasdasd.cz")).thenReturn(false);
        when(dao.checkGoogleID("123456777@gmail.com")).thenReturn(true);
        assertTrue(man.checkGoogleID("asd@asd.com"));
        assertTrue(man.checkGoogleID("gelaasd@gela.ge"));
        assertFalse(man.checkGoogleID("asdfkj@kasdasd.cz"));
        assertTrue(man.checkGoogleID("123456777@gmail.com"));
        //CheckPhoneNumber
        when(dao.checkPhoneNumber("123456789")).thenReturn(true);
        when(dao.checkPhoneNumber("123456790")).thenReturn(true);
        when(dao.checkPhoneNumber("123456791")).thenReturn(false);
        when(dao.checkPhoneNumber("123456777")).thenReturn(true);
        assertTrue(man.checkPhoneNumber("123456789"));
        assertTrue(man.checkPhoneNumber("123456790"));
        assertFalse(man.checkPhoneNumber("123456791"));
        assertTrue(man.checkPhoneNumber("123456777"));
        //login check
        Company comp = new Company(-1,"123456789","support@taxistgela.com","1234qwerTy","taxistGela","558677895","facebookIDmock","googleIDmock",true);
        when(dao.loginCompany("123456789", "asdf")).thenReturn(comp);
        assertEquals(comp,dao.loginCompany("123456789", "asdf"));
        //register check?
        when(dao.registerCompany(comp)).thenReturn(69);
        assertEquals(man.registerCompany(comp),69);
    }
    @Test
    public void driverManagerTests(){
        DriverDao dao = Mockito.mock(DriverDao.class);
        DriverManager man = new DriverManager(dao);
    }
    @Test
    public void orderManagerTests(){
        OrderDao dao = Mockito.mock(OrderDao.class);
        OrderManager man = new OrderManager(dao);
    }
    @Test
    public void reviewManagerTests(){
        ReviewDao dao = Mockito.mock(ReviewDao.class);
        ReviewManager man = new ReviewManager(dao);
    }
    @Test
    public void SessionManagerTests(){

    }
    @Test
    public void taxUserQueueTests(){

    }
    @Test
    public void userManagerTests(){
        UserDao dao = Mockito.mock(UserDao.class);
        UserManager man = new UserManager(dao);
    }
    @After
    public void cleanUp(){

    }
}