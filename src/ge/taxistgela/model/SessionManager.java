/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

package ge.taxistgela.model;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Alex on 6/5/2015.
 */
public class SessionManager implements SessionManagerAPI {

    public static final int USER_SESSION = 0;
    public static final int DRIVER_SESSION = 1;

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
    public synchronized void sendMessage(int sessionType, Integer ID, String message) {
        Map<String, Session> sessions = getMap(sessionType);
        String token = managers[sessionType].getTokenByID(ID);

        if (sessions != null && sessions.containsKey(token)) {
            Session session = sessions.get(token);

            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
