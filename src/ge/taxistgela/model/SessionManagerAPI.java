package ge.taxistgela.model;

import javax.websocket.Session;

/**
 * Created by Alex on 6/5/2015.
 */
public interface SessionManagerAPI {

    /**
     * Saves the session depending on the session type and token.
     *
     * @param sessionType
     * @param token
     * @param session
     */
    void addSession(int sessionType, String token, Session session);

    /**
     * Removes the session depending on the session type and token.
     *
     * @param sessionType
     * @param token
     */
    void removeSession(int sessionType, String token);

    /**
     * Sends the message to session's remote endpoint depending on the session type and token.
     *
     * @param sessionType
     * @param message
     */
    void sendMessage(int sessionType, String token, String message);
}
