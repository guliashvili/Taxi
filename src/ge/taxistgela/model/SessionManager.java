package ge.taxistgela.model;

import javax.websocket.Session;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 6/5/2015.
 */
public class SessionManager implements SessionManagerAPI {

    private Map<String, Session> userSessions;
    private Map<String, Session> driverSessions;

    public SessionManager() {
        this.userSessions = Collections.synchronizedMap(new HashMap<>());
        this.driverSessions = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public void addUserSession(String token, Session session) {
        userSessions.put(token, session);
    }

    @Override
    public void removeUserSession(String token) {
        if (userSessions.containsKey(token))
            userSessions.remove(token);
    }

    @Override
    public void sendToUser(String token, String message) {
        Session session = userSessions.get(token);

        System.out.println(token + " " + session.toString());

        if (session != null)
            session.getAsyncRemote().sendText(message);
    }

    @Override
    public void addDriverSession(String token, Session session) {
        driverSessions.put(token, session);
    }

    @Override
    public void removeDriverSession(String token) {
        driverSessions.remove(token);
    }

    @Override
    public void sendToDriver(String token, String message) {
        Session session = driverSessions.get(token);

        System.out.println(token + " " + session.toString());

        if (session != null)
            session.getAsyncRemote().sendText(message);
    }
}
