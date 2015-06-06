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
        switch (sessionType) {
            case USER_SESSION:
                addUserSession(token, session);
                break;
            case DRIVER_SESSION:
                addDriverSession(token, session);
                break;

            default:
                break;
        }
    }

    @Override
    public void removeSession(int sessionType, String token) {
        switch (sessionType) {
            case USER_SESSION:
                removeUserSession(token);
                break;
            case DRIVER_SESSION:
                removeDriverSession(token);
                break;

            default:
                break;
        }
    }

    @Override
    public void sendMessage(int sessionType, String token, String message) {
        switch (sessionType) {
            case USER_SESSION:
                sendToUser(token, message);
                break;
            case DRIVER_SESSION:
                sendToDriver(token, message);
                break;

            default:
                break;
        }
    }

    private void addUserSession(String token, Session session) {
        userSessions.put(token, session);
    }

    private void removeUserSession(String token) {
        if (userSessions.containsKey(token)) {
            userSessions.remove(token);
        }
    }

    private void sendToUser(String token, String message) {
        Session session = userSessions.get(token);

        if (session != null) {
            session.getAsyncRemote().sendText(message);
        }
    }

    private void addDriverSession(String token, Session session) {
        driverSessions.put(token, session);
    }

    private void removeDriverSession(String token) {
        driverSessions.remove(token);
    }

    private void sendToDriver(String token, String message) {
        Session session = driverSessions.get(token);

        if (session != null) {
            session.getAsyncRemote().sendText(message);
        }
    }
}
