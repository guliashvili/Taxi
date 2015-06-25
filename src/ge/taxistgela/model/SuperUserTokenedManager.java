package ge.taxistgela.model;

/**
 * Created by GIO on 6/25/2015.
 */
public interface SuperUserTokenedManager extends   SuperUserManager {
    String getTokenByID(Integer superUserID);

    Integer getIDByToken(String token);
}

