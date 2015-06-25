package ge.taxistgela.servlet;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alex on 6/25/2015.
 */
@WebServlet("/logout")
public class LogoutServlet extends ActionServlet {

    private static final String[] A_TYPE = {
            User.class.getName(),
            Driver.class.getName(),
            Company.class.getName()
    };

    public void logoutUser(HttpServletRequest request, HttpServletResponse response) {

    }

    public void logoutDriver(HttpServletRequest request, HttpServletResponse response) {

    }

    public void logoutCompany(HttpServletRequest request, HttpServletResponse response) {

    }

    private void logoutSuper(HttpServletRequest request, HttpServletResponse response) {

        // TODO remove socket sessions. in the near future.
    }
}
