package ge.taxistgela.websocket.user;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Created by Alex on 6/5/2015.
 */
public class Configurator extends ServerEndpointConfig.Configurator {
    private static UserServer server = new UserServer();

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        if (UserServer.class.equals(endpointClass)) {
            return (T) server;
        }

        throw new InstantiationException();
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession session = (HttpSession)request.getHttpSession();
        ServletContext servletContext = session.getServletContext();
        sec.getUserProperties().put(servletContext.getClass().getName(), servletContext);
    }
}
