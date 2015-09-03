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

package ge.taxistgela.websocket;

import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.model.SessionManagerAPI;

import javax.servlet.ServletContext;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by Alex on 6/6/2015.
 */
@ServerEndpoint(value = "/wsapp/{sessionType}/{token}", configurator = Configurator.class)
public class Server {

    private SessionManagerAPI sm;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("sessionType") int sessionType, @PathParam("token") String token) {
        if (sm == null) {
            ServletContext sc = (ServletContext) config.getUserProperties().get(ServletContext.class.getName());

            if (sc != null) {
                sm = (SessionManagerAPI) sc.getAttribute(SessionManagerAPI.class.getName());
            }
        }

        if (sm != null) {
            sm.addSession(sessionType, token, session);
        }
    }

    @OnMessage
    public void onMessage(String msg, Session session, @PathParam("sessionType") int sessionType, @PathParam("token") String token) {
        throw new UnsupportedOperationException();
    }

    @OnClose
    public void onClose(Session session, @PathParam("sessionType") int sessionType, @PathParam("token") String token) {
        if (sm != null) {
            sm.removeSession(sessionType, token);
        }
    }

    @OnError
    public void onError(Session session, Throwable t, @PathParam("sessionType") int sessionType, @PathParam("token") String token) {
        try {
            session.close();
        } catch (IOException e) {
            ExternalAlgorithms.debugPrint(e.toString());
        }

        if (sm != null) {
            sm.removeSession(sessionType, token);
        }

        ExternalAlgorithms.debugPrint(t.toString());
    }
}
