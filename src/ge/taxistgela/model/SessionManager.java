package ge.taxistgela.model;

import javax.websocket.Session;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 6/5/2015.
 */
public class SessionManager implements SessionManagerAPI {

    public static final int USER_SESSION = 1;
    public static final int DRIVER_SESSION = 2;

    private Map<String, Session> userSessions;
    private Map<String, Session> driverSessions;

    public SessionManager() {
        this.userSessions = Collections.synchronizedMap(new HashMap<>());
        this.driverSessions = Collections.synchronizedMap(new HashMap<>());
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
        Map<String, Session> sessions = getMap(sessionType);

        if (sessions != null && sessions.containsKey(token)) {
            sessions.get(token).getAsyncRemote().sendText(message);
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
