package ge.taxistgela.servlet;

import ge.taxistgela.bean.ErrorCode;
import ge.taxistgela.model.CompanyManagerAPI;
import ge.taxistgela.model.DriverManagerAPI;
import ge.taxistgela.model.UserManagerAPI;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 6/24/2015.
 */
@WebServlet("/verify")
public class VerificationServlet extends ActionServlet {

    private final static String[] V_TYPE = {"phone number", "email"};

    // /verify?action=uPhone&token=
    public void uPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        String token = request.getParameter("token");

        if (userManager != null && token != null) {
            ErrorCode errorCode = userManager.verifyUserPhoneNumber(token);

            if (errorCode.errorNotAccrued()) {
                printAccepted(response, 0);

                return;
            }

        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    // /verify?action=uEmail&token=
    public void uEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        String token = request.getParameter("token");

        if (userManager != null && token != null) {
            ErrorCode errorCode = userManager.verifyUserEmail(token);

            if (errorCode.errorNotAccrued()) {
                printAccepted(response, 1);

                return;
            }

        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    // /verify?action=dPhone&token=
    public void dPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        String token = request.getParameter("token");

        if (driverManager != null && token != null) {
            ErrorCode errorCode = driverManager.verifyDriverPhoneNumber(token);

            if (errorCode.errorNotAccrued()) {
                printAccepted(response, 0);

                return;
            }

        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    // /verify?action=dEmail&token=
    public void dEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        String token = request.getParameter("token");

        if (driverManager != null && token != null) {
            ErrorCode errorCode = driverManager.verifyDriverEmail(token);

            if (errorCode.errorNotAccrued()) {
                printAccepted(response, 1);

                return;
            }
        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    // /verify?action=cPhone&token=
    public void cPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        String token = request.getParameter("token");

        if (companyManager != null && token != null) {
            ErrorCode errorCode = companyManager.verifyCompanyPhoneNumber(token);

            if (errorCode.errorNotAccrued()) {
                printAccepted(response, 0);

                return;
            }
        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    // /verify?action=cEmail&token=
    public void cEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        String token = request.getParameter("token");

        if (companyManager != null && token != null) {
            ErrorCode errorCode = companyManager.verifyCompanyEmail(token);

            if (errorCode.errorNotAccrued()) {
                printAccepted(response, 1);

                return;
            }
        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    private void printAccepted(HttpServletResponse response, int type) throws IOException {
        response.setStatus(HttpServletResponse.SC_ACCEPTED);

        response.getWriter().print(
                "<h3>You have successfully verified your " + V_TYPE[type] + "</h3><br/><br/>" +
                        "<a href=\"/\">Go to homepage</a>"
        );
    }
}
