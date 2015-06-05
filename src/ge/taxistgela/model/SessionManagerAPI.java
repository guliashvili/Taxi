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
     * Send the certain message to the certain user.
     *
     * @param token
     * @param message
     */
    void sendToUser(String token, String message);

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

    /**
     * Send the certain message to the certain driver.
     *
     * @param token
     * @param message
     */
    void sendToDriver(String token, String message);
}
