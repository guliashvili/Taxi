package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.Gender;
import ge.taxistgela.bean.User;
import ge.taxistgela.bean.UserPreference;

/**
 * Created by GIO on 6/28/2015.
 */
public class UserInfo extends User {
    public UserInfo() {
        super();
    }

    public UserInfo(User user) {
        super(user);
    }

    public UserInfo(Integer userID, String email, String password, String firstName, String lastName, String phoneNumber, Gender gender, String facebookID, String googleID, Double rating, UserPreference preference, Boolean isVerifiedEmail, Boolean isVerifiedPhone) {
        super(userID, email, password, firstName, lastName, phoneNumber, gender, facebookID, googleID, rating, preference, isVerifiedEmail, isVerifiedPhone);
    }




}
