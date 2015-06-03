package ge.taxistgela.dao;

import ge.taxistgela.bean.Company;
import ge.taxistgela.helper.AdminDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Ratmach on 30/5/15.
 */
public class DaoTests {
    @Before
    public void setup(){
        AdminDatabase db = new AdminDatabase();
        try {
            db.recreateDatabase();
        }catch(Exception e){
            System.out.println(e.toString());
            assertTrue(false);
        }
    }
    @Test
    public void testCompanyDao(){
        //RegistrationTests
        CompanyDao dao = new CompanyDao();
        Company comp = mock(Company.class);
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
        assertTrue(dao.checkEmail("support@taxistgela.com"));
        assertFalse(dao.checkEmail("support1@taxistgela.com"));
        assertFalse(dao.checkEmail("support2@taxistgela.com"));
        //CheckGoogleID
        assertTrue(dao.checkGoogleID("googleIDmock"));
        assertFalse(dao.checkGoogleID("asd"));
        assertFalse(dao.checkGoogleID("a"));
        //CheckFacebook
        assertTrue(dao.checkFacebookID("facebookIDmock"));
        assertFalse(dao.checkFacebookID("asd"));
        assertFalse(dao.checkFacebookID("afk"));
        //UpdateTest
        Company comp1 = mock(Company.class);
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
        //CheckGoogleID
        assertFalse(dao.checkGoogleID("googleIDmock"));
        assertFalse(dao.checkGoogleID("googleIDmock1"));
        assertFalse(dao.checkGoogleID("a"));
        //CheckFacebook
        assertFalse(dao.checkFacebookID("facebookIDmock"));
        assertTrue(dao.checkFacebookID("asd"));
        assertFalse(dao.checkFacebookID("afk"));
    }
    @Test
    public void testUserDao(){

    }
    @Test
    public void testDriverDao(){

    }
    @Test
    public void testOrderDao(){

    }
    @Test
    public void testReviewDao(){

    }
    @After
    public void cleanup(){
        AdminDatabase db = new AdminDatabase();
        try {
            db.recreateDatabase();
        }catch(Exception e){
            System.out.println(e.toString());
            assertTrue(false);
        }
    }
}