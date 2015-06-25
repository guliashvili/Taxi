package ge.taxistgela.model;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Alex on 6/5/2015.
 */
public class SessionManager implements SessionManagerAPI {

    public static final int USER_SESSION = 1;
    public static final int DRIVER_SESSION = 2;

    private Map<String, Session> userSessions;
    private Map<String, Session> driverSessions;

    public SessionManager() {
        this.userSessions = new ConcurrentHashMap<>();
        this.driverSessions = new ConcurrentHashMap<>();
    }

    @Override
    public void addSession(int sessionType, String token, Session session) {
        Map<String, Session> sessions = getMap(sessionType);

        if (sessions != null) {
            sessions.put(token, session);
        }
    }

    @Override
    public void removeSession(int sessionType, String token) {
        Map<String, Session> sessions = getMap(sessionType);

        if (sessions != null) {
            sessions.remove(token);
        }
    }

    @Override
    public void sendMessage(int sessionType, String token, String message) {
        Map<String, Session> remotes = getMap(sessionType);

        if (remotes != null && remotes.containsKey(token)) {
            Session session = remotes.get(token);

            if (session.isOpen()) {
                session.getAsyncRemote().sendText(message);
            }
        }
    }

    private Map<String, Session> getMap(int sessionType) {
        switch (sessionType) {
            case USER_SESSION:
                return userSessions;
            case DRIVER_SESSION:
                return driverSessions;

            default:
                return null;
        }
    }
}
