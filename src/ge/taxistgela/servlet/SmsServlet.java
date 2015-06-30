package ge.taxistgela.servlet;

import ge.taxistgela.model.SmsQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 6/30/2015.
 */
@WebServlet("/sms")
public class SmsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SmsQueue smsQueue = (SmsQueue) request.getServletContext().getAttribute(SmsQueue.class.getName());

        if (smsQueue == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            String message = smsQueue.getSms();

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/plain");
            response.getWriter().print(message);
        }
    }
}
