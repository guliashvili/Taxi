package ge.taxistgela.model;

import javax.websocket.Session;

/**
 * Created by Alex on 6/5/2015.
 */
public interface SessionManagerAPI {

    /**
     * Saves user and open connection session.
     *
     * @param token
     * @param session
     */
    void addUserSession(String token, Session session);

    /**
     * Removes the certain session dependent on the user.
     *
     * @param token
     */
    void removeUserSession(String token);


    /**
     * Saves driver and open connection session.
     *
     * @param token
     * @param session
     */
    void addDriverSession(String token, Session session);

    /**
     * Removes the certain session dependent on the driver.
     *
     * @param token
     */
    void removeDriverSession(String token);
}
