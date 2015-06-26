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
