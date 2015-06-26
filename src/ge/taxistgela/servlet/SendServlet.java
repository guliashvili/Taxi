package ge.taxistgela.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alex on 6/5/2015.
 */
@WebServlet("/send")
public class SendServlet extends ActionServlet {

    // TODO Replace with real implementations.

    protected void sendToDriver(HttpServletRequest request, HttpServletResponse response) {
        /*System.out.println("sendToDriver");

        OrderDispatcher orderDispatcher = (OrderDispatcher) getServletContext().getAttribute(OrderDispatcher.class.getName());

        String message = request.getParameter("message");

        System.out.println("sendToDriver: " + message);

        orderDispatcher.addToQueue(new Gson().fromJson(message, Order.class));

        response.setStatus(HttpServletResponse.SC_OK);*/
    }

    protected void sendToUser(HttpServletRequest request, HttpServletResponse response) {
        /*System.out.println("sendToUser");

        SessionManagerAPI sessionManager = (SessionManagerAPI) getServletContext().getAttribute(SessionManagerAPI.class.getName());

        String token = request.getParameter("token");
        String message = request.getParameter("message");

        System.out.println("sendToUser: " + token + " " + message);

        sessionManager.sendMessage(SessionManager.USER_SESSION, token, message);

        response.setStatus(HttpServletResponse.SC_OK);*/
    }
}
