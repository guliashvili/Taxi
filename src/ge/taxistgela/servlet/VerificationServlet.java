package ge.taxistgela.servlet;

import ge.taxistgela.bean.ErrorCode;
import ge.taxistgela.model.CompanyManagerAPI;
import ge.taxistgela.model.DriverManagerAPI;
import ge.taxistgela.model.SuperUser;
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

    private void mainVerifyPhone(SuperUser obj, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String token = request.getParameter("token");

        if (obj != null && token != null) {
            ErrorCode errorCode = obj.verifyPhoneNumber(token);

            if (errorCode.errorNotAccrued()) {
                printAccepted(response, 0);

                return;
            }

        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);

    }

    private void mainVerifyEmail(SuperUser obj, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getParameter("token");

        if (obj != null && token != null) {
            ErrorCode errorCode = obj.verifyEmail(token);

            if (errorCode.errorNotAccrued()) {
                printAccepted(response, 1);

                return;
            }

        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    // /verify?action=uPhone&token=
    public void uPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        mainVerifyPhone(userManager, request, response);

    }

    // /verify?action=uEmail&token=
    public void uEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        mainVerifyEmail(userManager, request, response);
    }

    // /verify?action=dPhone&token=
    public void dPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        mainVerifyPhone(driverManager, request, response);
    }

    // /verify?action=dEmail&token=
    public void dEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        mainVerifyEmail(driverManager, request, response);
    }

    // /verify?action=cPhone&token=
    public void cPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        mainVerifyPhone(companyManager, request, response);
    }

    // /verify?action=cEmail&token=
    public void cEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        mainVerifyEmail(companyManager, request, response);
    }

    private void printAccepted(HttpServletResponse response, int type) throws IOException {
        response.setStatus(HttpServletResponse.SC_ACCEPTED);

        response.getWriter().print(
                "<h3>You have successfully verified your " + V_TYPE[type] + "</h3><br/><br/>" +
                        "<a href=\"/\">Go to homepage</a>"
        );
    }
}
