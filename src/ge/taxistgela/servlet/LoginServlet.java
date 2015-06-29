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
import java.security.GeneralSecurityException;

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

    public void loginGPUser(HttpServletRequest request, HttpServletResponse response) throws IOException, GeneralSecurityException {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        loginGPSuper(userManager, 0, request, response);
    }

    public void loginGPDriver(HttpServletRequest request, HttpServletResponse response) throws IOException, GeneralSecurityException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        loginGPSuper(driverManager, 1, request, response);
    }

    public void loginGPCompany(HttpServletRequest request, HttpServletResponse response) throws IOException, GeneralSecurityException {
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        loginGPSuper(companyManager, 2, request, response);
    }


    public void loginGPSuper(SuperUserManager man, int type, HttpServletRequest request, HttpServletResponse response) throws IOException, GeneralSecurityException {
        String googleID = request.getParameter("googleplusId");
        ExternalAlgorithms.debugPrint("LoginGP " + A_TYPE[type] + " " + googleID);

        if (man == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Object obj = man.getByGoogleID(googleID);

            if (obj != null) {
                request.getSession().setAttribute(A_TYPE[type], obj);
                response.setStatus(HttpServletResponse.SC_OK);
                response.sendRedirect(P_TYPE[type]);

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void loginFbUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        loginFbSuper(userManager, 0, request, response);
    }

    public void loginFbDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        loginFbSuper(driverManager, 1, request, response);
    }

    public void loginFbCompany(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        loginFbSuper(companyManager, 2, request, response);
    }

    private void loginFbSuper(SuperUserManager man, int type, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String facebookID = request.getParameter("facebookID");

        ExternalAlgorithms.debugPrint("LoginFb " + A_TYPE[type] + " " + facebookID);

        if (man == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Object obj = man.getByFacebookID(facebookID);

            if (obj != null) {
                request.getSession().setAttribute(A_TYPE[type], obj);
                response.setStatus(HttpServletResponse.SC_OK);
                response.sendRedirect(P_TYPE[type]);

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

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

        if (man == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Object obj = man.login(email, password);

            if (obj != null) {
                request.getSession().setAttribute(A_TYPE[type], obj);
                response.setStatus(HttpServletResponse.SC_OK);
                response.sendRedirect(P_TYPE[type]);

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


}
