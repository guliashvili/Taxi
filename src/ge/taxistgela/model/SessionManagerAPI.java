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
     * @param ID
     * @param message
     */
    void sendMessage(int sessionType, Integer ID, String message);
}
