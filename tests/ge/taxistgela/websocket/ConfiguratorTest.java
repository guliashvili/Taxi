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

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Alex on 6/11/2015.
 */
public class ConfiguratorTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    private Configurator configurator;

    @Before
    public void setUp() throws Exception {
        configurator = new Configurator();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetEndpointInstance() throws Exception {
        Object server1 = configurator.getEndpointInstance(Server.class);
        Object server2 = configurator.getEndpointInstance(Server.class);

        assertEquals(server1, server2);
        assertEquals(server1.getClass(), Server.class);
        assertEquals(server2.getClass(), Server.class);

        thrown.expect(InstantiationException.class);
        Object server3 = configurator.getEndpointInstance(Class.class);
    }

    @Test
    public void testModifyHandshake() throws Exception {
        ServerEndpointConfig config = mock(ServerEndpointConfig.class);
        Map<String, Object> userProperties = new HashMap<>();
        when(config.getUserProperties()).thenReturn(userProperties);

        ServletContext servletContext = mock(ServletContext.class);
        HttpSession session = mock(HttpSession.class);
        when(session.getServletContext()).thenReturn(servletContext);

        HandshakeRequest request1 = mock(HandshakeRequest.class);
        when(request1.getHttpSession()).thenReturn(session);

        HandshakeRequest request2 = mock(HandshakeRequest.class);
        when(request2.getHttpSession()).thenReturn(null);

        HandshakeResponse response = mock(HandshakeResponse.class);

        configurator.modifyHandshake(config, request1, response);
        assertEquals(userProperties.get(ServletContext.class.getName()), servletContext);

        userProperties.clear();

        configurator.modifyHandshake(config, request2, response);
        assertNull(userProperties.get(ServletContext.class.getName()));
    }
}