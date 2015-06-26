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

    private SuperUserTokenedManager[] managers;

    public SessionManager(SuperUserTokenedManager[] managers) {
        this.userSessions = new ConcurrentHashMap<>();
        this.driverSessions = new ConcurrentHashMap<>();
        this.managers = managers;
    }

    @Override
    public void addSession(int sessionType, String token, Session session) {
        Map<String, Session> sessions = getMap(sessionType);

        if (sessions != null) {
            sessions.put(token, session);
            updateDriverStatusIfNeeded(sessionType, token, true);
        }
    }

    @Override
    public void removeSession(int sessionType, String token) {
        Map<String, Session> sessions = getMap(sessionType);

        if (sessions != null) {
            sessions.remove(token);
            updateDriverStatusIfNeeded(sessionType, token, false);
        }
    }

    private void updateDriverStatusIfNeeded(int sessionType, String token, boolean status) {
        if (sessionType == DRIVER_SESSION) {
            DriverManagerAPI dm = (DriverManagerAPI) managers[sessionType];
            dm.setDriverActiveStatus(dm.getIDByToken(token), status);
        }
    }

    @Override
    public void sendMessage(int sessionType, Integer ID, String message) {
        Map<String, Session> sessions = getMap(sessionType);
        String token = managers[sessionType].getTokenByID(ID);

        if (sessions != null && sessions.containsKey(token)) {
            Session session = sessions.get(token);

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
