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

import ge.taxistgela.admin.Admin;
import ge.taxistgela.bean.ErrorCode;
import ge.taxistgela.bean.SuperDaoUser;
import ge.taxistgela.model.CompanyManagerAPI;
import ge.taxistgela.model.DriverManagerAPI;
import ge.taxistgela.model.SuperUserManager;
import ge.taxistgela.model.UserManagerAPI;
import org.apache.commons.lang3.RandomStringUtils;

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

    public void banUser(HttpServletRequest request, HttpServletResponse response) {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        String userID = request.getParameter("userID");
        String password = RandomStringUtils.randomAlphanumeric(20);

        toogleBan(userManager, userID, password, request, response);
    }

    public void banDriver(HttpServletRequest request, HttpServletResponse response) {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        String driverID = request.getParameter("driverID");
        String password = RandomStringUtils.randomAlphanumeric(20);

        toogleBan(driverManager, driverID, password, request, response);
    }

    public void banCompany(HttpServletRequest request, HttpServletResponse response) {
        CompanyManagerAPI orderManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        String companyID = request.getParameter("companyID");
        String password = RandomStringUtils.randomAlphanumeric(20);

        toogleBan(orderManager, companyID, password, request, response);
    }

    public void unbanUser(HttpServletRequest request, HttpServletResponse response) {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        toogleBan(userManager, userID, password, request, response);
    }

    public void unbanDriver(HttpServletRequest request, HttpServletResponse response) {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        String driverID = request.getParameter("driverID");
        String password = RandomStringUtils.randomAlphanumeric(20);

        toogleBan(driverManager, driverID, password, request, response);
    }

    public void unbanCompany(HttpServletRequest request, HttpServletResponse response) {
        CompanyManagerAPI orderManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        String companyID = request.getParameter("companyID");
        String password = request.getParameter("password");

        toogleBan(orderManager, companyID, password, request, response);
    }

    private void toogleBan(SuperUserManager superUserManager, String sID, String password, HttpServletRequest request, HttpServletResponse response) {
        if (superUserManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Admin admin = (Admin) request.getSession().getAttribute(Admin.class.getName());

            if (admin != null) {
                if (sID != null) {
                    Integer id = null;

                    try {
                        id = Integer.parseInt(sID);
                    } catch (Exception e) {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                        return;
                    }

                    SuperDaoUser superUser = superUserManager.getByID(id);

                    if (superUser != null) {
                        superUser.setPassword(password);

                        ErrorCode errorCode = superUserManager.update(superUser);

                        if (errorCode.errorNotAccrued()) {
                            response.setStatus(HttpServletResponse.SC_ACCEPTED);
                        } else {
                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }
}
