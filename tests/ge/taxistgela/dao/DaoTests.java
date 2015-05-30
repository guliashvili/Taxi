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
        //CheckTests
        //CheckEmail
        //CheckGoogleID
        //CheckFacebook
        //UpdateTest
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