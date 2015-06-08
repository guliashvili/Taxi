package ge.taxistgela.websocket;

import ge.taxistgela.model.RemoteManagerAPI;

import javax.servlet.ServletContext;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Alex on 6/6/2015.
 */
@ServerEndpoint(value = "/wsapp/{sessionType}/{token}", configurator = Configurator.class)
public class Server {

    private RemoteManagerAPI sm;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("sessionType") int sessionType, @PathParam("token") String token) {
        System.out.println(sessionType + " " + token);
        if (sm == null) {
            ServletContext sc = (ServletContext) config.getUserProperties().get(ServletContext.class.getName());

            if (sc != null) {
                sm = (RemoteManagerAPI) sc.getAttribute(RemoteManagerAPI.class.getName());
            }
        }

        if (sm != null) {
            sm.addRemote(sessionType, token, session.getAsyncRemote());
        }
    }

    @OnMessage
    public void onMessage(String msg, Session session, @PathParam("sessionType") int sessionType, @PathParam("token") String token) {
        throw new UnsupportedOperationException();
    }

    @OnClose
    public void onClose(Session session, @PathParam("sessionType") int sessionType, @PathParam("token") String token) {
        if (sm != null) {
            sm.removeRemote(sessionType, token);
        }
    }

    @OnError
    public void onError(Session session, Throwable t, @PathParam("sessionType") int sessionType, @PathParam("token") String token) {
        System.out.println(t.toString());
    }
}
