package ge.taxistgela.websocket.driver;

import ge.taxistgela.model.SessionManager;
import ge.taxistgela.model.SessionManagerAPI;

import javax.servlet.ServletContext;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Alex on 6/5/2015.
 */
@ServerEndpoint(value = "/wsapp/driver/{token}", configurator = DriverServerConfigurator.class)
public class DriverServer {

    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("token") String token) {
        ServletContext servletContext = (ServletContext) config.getUserProperties().get(ServletContext.class.getName());

        if (servletContext != null) {
            System.out.println("pass servletcontext");
            SessionManagerAPI sessionManager = (SessionManager) servletContext.getAttribute(SessionManager.class.getName());

            if (sessionManager != null && token != null) {
                System.out.println("pass sessionmanager");
                sessionManager.addDriverSession(token, session);
            }
        }
    }

    @OnMessage
    public void onMassage(String msg, Session session, @PathParam("token") String token) {
        throw new UnsupportedOperationException();
    }

    @OnClose
    public void onClose(Session session, @PathParam("token") String token) {
//        ServletContext servletContext = (ServletContext) config.getUserProperties().get(ServletContext.class.getName());
//
//        if (servletContext != null) {
//            SessionManagerAPI sessionManager = (SessionManager) servletContext.getAttribute(SessionManager.class.getName());
//
//            if (sessionManager != null && token != null) {
//                sessionManager.removeDriverSession(token);
//            }
//        }
    }

    @OnError
    public void onError(Session session, Throwable t, @PathParam("token") String token) {
        System.out.println(t.toString());
    }
}
