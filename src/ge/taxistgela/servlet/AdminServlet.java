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

        banSuper(userManager, request, response);
    }

    public void banDriver(HttpServletRequest request, HttpServletResponse response) {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        banSuper(driverManager, request, response);
    }

    public void banCompany(HttpServletRequest request, HttpServletResponse response) {
        CompanyManagerAPI orderManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        banSuper(orderManager, request, response);
    }

    private void banSuper(SuperUserManager superUserManager, HttpServletRequest request, HttpServletResponse response) {
        if (superUserManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Admin admin = (Admin) request.getSession().getAttribute(Admin.class.getName());

            if (admin != null) {
                String sID = request.getParameter("id");

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
                        superUser.setPassword(RandomStringUtils.randomAlphanumeric(10));

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
