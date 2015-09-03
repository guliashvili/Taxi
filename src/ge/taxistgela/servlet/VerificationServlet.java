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

package ge.taxistgela.servlet;

import ge.taxistgela.bean.ErrorCode;
import ge.taxistgela.model.CompanyManagerAPI;
import ge.taxistgela.model.DriverManagerAPI;
import ge.taxistgela.model.SuperUserManager;
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

    private final static String[] V_TYPE = {"phone number", "email", "company"};

    private void mainVerifyPhone(SuperUserManager obj, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String token = request.getParameter("token");

        if (obj == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            if (token != null) {
                ErrorCode errorCode = obj.verifyPhoneNumber(token);

                if (errorCode.errorNotAccrued()) {
                    printAccepted(response, 0);

                    return;
                }

            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    private void mainVerifyEmail(SuperUserManager obj, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getParameter("token");

        if (obj == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            if (token != null) {
                ErrorCode errorCode = obj.verifyEmail(token);

                if (errorCode.errorNotAccrued()) {
                    printAccepted(response, 1);

                    return;
                }

            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
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

    // /verify?action=dCompany&token=
    public void dCompany(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        if (driverManager == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            String token = request.getParameter("token");

            if (token != null) {
                ErrorCode errorCode = driverManager.verifyCompany(token);

                if (errorCode.errorNotAccrued()) {
                    printAccepted(response, 2);

                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void printAccepted(HttpServletResponse response, int type) throws IOException {
        response.setStatus(HttpServletResponse.SC_ACCEPTED);

        response.getWriter().print(
                "<html><body><h3>You have successfully verified your " + V_TYPE[type] + "</h3><br/><br/>" +
                        "<a href=\"/\">Go to homepage</a></body></html>"
        );
    }
}