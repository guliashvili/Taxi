package ge.taxistgela.servlet;

import ge.taxistgela.helper.ExternalAlgorithms;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 6/22/2015.
 */
@WebServlet("/login")
public class LoginServlet extends ActionServlet {

    public void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExternalAlgorithms.debugPrint("Login User " + request.getParameter("email") + " " + request.getParameter("password"));
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/Resources/user.html");
    }

    public void loginDriver(HttpServletRequest request, HttpServletResponse response) {
        ExternalAlgorithms.debugPrint("Login Driver " + request.getParameter("email") + " " + request.getParameter("password"));
    }

    public void loginCompany(HttpServletRequest request, HttpServletResponse response) {
        ExternalAlgorithms.debugPrint("Login Company " + request.getParameter("email") + " " + request.getParameter("password"));
    }
}
