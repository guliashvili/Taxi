package ge.taxistgela.servlet;

import ge.taxistgela.bean.*;
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
@WebServlet("/social")
public class SocialNetworkServlet extends ActionServlet {

    private static final String[] A_TYPE = {
            User.class.getName(),
            Driver.class.getName(),
            Company.class.getName()
    };

    private static final String[] C_TYPE = {
            UserManagerAPI.class.getName(),
            DriverManagerAPI.class.getName(),
            CompanyManagerAPI.class.getName()
    };


    public void addFbAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String facebookId = request.getParameter("facebookID");

        for (int i = 0; i < A_TYPE.length; i++) {
            if (request.getSession().getAttribute(A_TYPE[i]) != null)
                addFbAccount(A_TYPE[i], facebookId,
                        (SuperUserManager) request.getServletContext().getAttribute(C_TYPE[i]),
                        request, response);
        }
    }

    private void addFbAccount(String aType, String facebookId, SuperUserManager um, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (um == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            SuperDaoUser superUser = (SuperDaoUser) request.getSession().getAttribute(aType);

            superUser.setFacebookID(facebookId);

            ErrorCode errorCode = um.update(superUser);

            if (errorCode.errorNotAccrued()) {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                response.sendRedirect("/");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(errorCode.toJson());
            }

            updateSessionUser(aType, um, request, superUser);
        }
    }

    public void addGPAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String googleID = request.getParameter("googleID");

        for (int i = 0; i < A_TYPE.length; i++) {
            if (request.getSession().getAttribute(A_TYPE[i]) != null)
                addGPAccount(A_TYPE[i], googleID,
                        (SuperUserManager) request.getServletContext().getAttribute(C_TYPE[i]),
                        request, response);
        }
    }

    private void addGPAccount(String aType, String googleID, SuperUserManager um, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (um == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            SuperDaoUser superUser = (SuperDaoUser) request.getSession().getAttribute(aType);
            superUser.setGoogleID(googleID);

            ErrorCode errorCode = um.update(superUser);

            if (errorCode.errorNotAccrued()) {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                response.sendRedirect("/");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(errorCode.toJson());
            }

            updateSessionUser(aType, um, request, superUser);
        }
    }

    private void updateSessionUser(String aType, SuperUserManager um, HttpServletRequest request, SuperDaoUser superUser) {
        superUser = um.getByEmail(superUser.getEmail());
        request.getSession().setAttribute(aType, superUser);
    }

}
