package ge.taxistgela.servlet;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.User;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.model.CompanyManagerAPI;
import ge.taxistgela.model.DriverManagerAPI;
import ge.taxistgela.model.SuperUserManager;
import ge.taxistgela.model.UserManagerAPI;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 6/22/2015.
 */
@WebServlet("/login")
public class LoginServlet extends ActionServlet {

    private static final String[] A_TYPE = {
            User.class.getName(),
            Driver.class.getName(),
            Company.class.getName()
    };

    private static final String[] P_TYPE = {
            "/user.jsp",
            "/driver.jsp",
            "/company.jsp"
    };

    public void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        loginSuper(userManager, 0, request, response);
    }

    public void loginDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        loginSuper(driverManager, 1, request, response);
    }

    public void loginCompany(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        loginSuper(companyManager, 2, request, response);

    }

    private void loginSuper(SuperUserManager man, int type, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ExternalAlgorithms.debugPrint("Login " + A_TYPE[type] + " " + email + " " + password);

        if (man != null) {
            Object obj = man.login(email, password);

            if (obj != null) {
                request.getSession().setAttribute(A_TYPE[type], obj);

                response.setStatus(HttpServletResponse.SC_OK);
                System.out.println("redirected");
                response.sendRedirect(P_TYPE[type]);

                return;
            }
        }
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
