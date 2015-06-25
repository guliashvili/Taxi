package ge.taxistgela.dao;

import ge.taxistgela.bean.*;
import ge.taxistgela.helper.AdminDatabase;
import ge.taxistgela.helper.HashGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
                null, 5.5, userPreference2, false, false);


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

        userDao.updateUserPreference(realUserPreference1);

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
        tmp.setPassword(to.getPassword());
        tmp.setFirstName(to.getFirstName());
        tmp.setLastName(to.getLastName());
        tmp.setPhoneNumber(to.getPhoneNumber());
        tmp.setGender(to.getGender());
        tmp.setRating(to.getRating());


        assertFalse(userDao.updateUser(tmp));

        to.setPreference(userPreference);
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
        }
    }


    public void insertUsers() {
        UserPreference blankPreference = new UserPreference(-1, 0.0, false, 0, 0, 0, false);

        userPreference1 = insertUserPreference(blankPreference, userPreference2);
        userPreference2 = insertUserPreference(blankPreference, userPreference1);

        User blankUser = new User(-1, "a@a.ge", "a", "a", "a", "555555555", Gender.MALE, null,
                null, 0.0, userPreference2, false, false);
        user1 = insertUser(blankUser, user1, userPreference1);
        assertTrue(userDao.registerUser(user1));

        user2 = insertUser(blankUser, user2, userPreference2);
        assertTrue(userDao.registerUser(user2));

        checksUsers(new User[]{user1, user2});
    }

    @Test
    public void superTest() {
        insertUsers();
    }


}
