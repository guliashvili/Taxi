package ge.taxistgela.servlet;

import ge.taxistgela.admin.Admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 6/30/2015.
 */
@WebServlet("/admin")
public class AdminServlet extends ActionServlet {

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = new Admin();

        if (admin.checkLogin(username, password)) {
            request.getSession().setAttribute(Admin.class.getName(), admin);

            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/admin.jsp");

            return;
        }

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
