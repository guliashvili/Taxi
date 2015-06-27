package ge.taxistgela.manager;

import ge.taxistgela.bean.*;
import ge.taxistgela.dao.CompanyDao;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.dao.UserDao;
import ge.taxistgela.helper.AdminDatabase;
import ge.taxistgela.helper.HashGenerator;
import ge.taxistgela.model.CompanyManager;
import ge.taxistgela.model.DriverManager;
import ge.taxistgela.model.UserManager;
import ge.taxistgela.ram.model.TaxRam;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by GIO on 6/26/2015.
 */
public class GioTestManager {


    User user1, user2;
    Driver driver1, driver2;
    Company company1;


    UserPreference userPreference1, userPreference2;
    DriverPreference driverPreference1, driverPreference2;
    // Location location1, location2;
    Car car1, car2;
    DriverManager driverManager;
    UserManager userManager;
    CompanyManager companyManager;
    CompanyDao companyDao;
    DriverDao driverDao;


    @Before
    //@After
    public void setup() {
        TaxRam taxRam = new TaxRam();
        driverManager = new DriverManager(new DriverDao(), taxRam);
        userManager = new UserManager(new UserDao(), taxRam);
        companyManager = new CompanyManager(new CompanyDao(), taxRam);

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

        //location1 = new Location(41.745984, 44.775832);
        //location2 = new Location(41.709599, 44.756885);

        car1 = new Car("JRJ-880", "jigri mdzgoli", 2000, true, 4);
        car2 = new Car("PK-990-AC", "mweveli", 1994, false, 4);

        driver1 = new Driver(-1, "01005030801", "vinmesmiti@gmail.com", "traki", null, "Giorgi", "Guliashvili", Gender.MALE, "557105511", car1,
                null, "googleIDFOr traki", 4.0, driverPreference1, true, false, false);

        driver2 = new Driver(-1, "01005030666", "gguli13@freeuni.edu.ge", "evtanazia", null, "Gela", "Meyleve", Gender.FEMALE, "557105666", car2,
                "yleta jgro", null, 4.0, driverPreference2, true, false, false);

        company1 = new Company(-1, "code", "email@email.com", "123456(dumbpwd)", "lucifer", "566666666", "fbid", "glid", false, false);

        user1 = new User(-1, "daltoniki@tvali.ge", "shavitetria", "mwvane", "yvitladze", "511123123", Gender.MALE, "fbb sun",
                null, 5.0, userPreference1, false, false);
        user2 = new User(-1, "uxucesi@mta.ge", "xmali", "mtian", "balaxadze", "511223344", Gender.MALE, null,
                "glid", 5.5, userPreference2, false, false);

    }

    public Company addCompany(Company blankI, Company toI) {
        Company blank = new Company(blankI);
        Company company = new Company(toI);

        Company bla = new Company(blank);
        bla.setEmail("cudi email");
        assertEquals(true, companyManager.register(bla).errorAccrued());

        bla = new Company(blank);
        bla.setCompanyCode(null);
        assertTrue(companyManager.register(bla).errorAccrued());

        bla = new Company(blank);
        bla.setPhoneNumber("557");
        assertTrue(companyManager.register(bla).errorAccrued());

        assertTrue(companyManager.register(blank).errorNotAccrued());
        String oldpwd = blank.getPassword();

        blank.setCompanyCode(company.getCompanyCode());
        blank.setEmail(company.getEmail());
        blank.setPassword(company.getPassword());
        blank.setCompanyName(company.getCompanyName());
        blank.setPhoneNumber(company.getPhoneNumber());
        blank.setFacebookID(company.getFacebookID());
        blank.setGoogleID(company.getGoogleID());
        blank.setIsVerifiedEmail(company.getIsVerifiedEmail());
        blank.setIsVerifiedPhone(company.getIsVerifiedPhone());

        company.setCompanyID(blank.getCompanyID());

        assertEquals(blank, company);

        assertTrue(companyManager.update(blank).errorNotAccrued());

        blank.setPassword(toI.getPassword());
        assertTrue(companyManager.changePassword(blank, oldpwd).errorNotAccrued());

        blank = companyManager.login(blank.getEmail(), company.getPassword());
        company.setPassword(HashGenerator.getSaltHash(company.getPassword()));
        assertEquals(blank, company);

        assertTrue(companyManager.checkEmail(blank.getEmail()));
        assertTrue(blank.getFacebookID() == null || companyManager.checkFacebookID(blank.getFacebookID()));
        assertTrue(blank.getGoogleID() == null || companyManager.checkGoogleID(blank.getGoogleID()));
        assertTrue(companyManager.checkPhoneNumber(blank.getPhoneNumber()));
        assertTrue(companyManager.checkCompanyCode(blank.getCompanyCode()));

        assertFalse(companyManager.checkEmail("wrong type email"));

        companyManager.verifyEmail(blank.getEmailToken());
        companyManager.verifyPhoneNumber(blank.getPhoneNumberToken());

        blank = companyManager.login(blank.getEmail(), toI.getPassword());
        assertTrue(blank.getIsVerifiedEmail());
        assertTrue(blank.getIsVerifiedPhone());

        assertEquals(blank, companyManager.getByID(blank.getCompanyID()));
        assertEquals(blank.getCompanyID(), companyManager.getCompanyIDByCode(blank.getCompanyCode()));


        return blank;
    }

    public void testCompany() {

        Company blank = new Company(-1, "blankcode", "blank@company.ge", "blankPWd", "blankGmert", "559101010", "fb comp blank", "gl comp blank",
                false, false);

        addCompany(blank, company1);
    }

    public void testDriver() {

    }

    @Test
    public void superTest() {
        testCompany();


    }
}
