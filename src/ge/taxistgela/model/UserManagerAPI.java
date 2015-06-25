package ge.taxistgela.model;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.ErrorCode;
import ge.taxistgela.bean.User;
import ge.taxistgela.bean.UserPreference;
import ge.taxistgela.dao.UserDaoAPI;

import java.util.List;

/**
 * Created by GIO on 5/25/2015.
 */
public abstract class UserManagerAPI implements SuperUserTokenedManager {
    protected UserDaoAPI userDao;
    protected TaxRam taxRam;

    public UserManagerAPI(UserDaoAPI userDao, TaxRam taxRam) {
        this.userDao = userDao;
        this.taxRam = taxRam;
    }

    /**
     * Returns UserPreference selected by the certain userPreferenceID.
     *
     * @param userPreferenceID
     * @return UserPreference generated from database.
     */
    public abstract UserPreference getUserPreferenceByID(Integer userPreferenceID);


    /**
     * Inserts the certain UserPreference.
     * Returns operation result.
     *
     * @param userPreference
     * @return operationCode
     */
    public abstract ErrorCode insertUserPreference(UserPreference userPreference);

    /**
     * Updates the userPreference with the new data.
     *
     * @param userPreference
     * @return operationCode
     */
    public abstract ErrorCode updateUserPreference(UserPreference userPreference);


    /**
     * Returns Users selected by the driver  criteria.
     * Does not consider TimeLimit
     * returns null if problem
     * Considers
     *  User.minimumDriverRating
     *  User.conditioning
     *  User.carYear
     *  User.passengersCount
     *  Driver.minimumUserRating
     * @param driver
     * @return List of users generated from database.
     */
    public abstract List<User> getUsersByPreferences(Driver driver);

}
