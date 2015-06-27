package ge.taxistgela.servlet;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.GeneralCheckableInformation;
import ge.taxistgela.bean.User;
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

    public void addFbAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String facebookId = request.getParameter("facebookID");
        if (request.getSession().getAttribute(User.class.getName()) != null)
            addFbAccount((User) request.getSession().getAttribute(User.class.getName()), facebookId,
                    (SuperUserManager) request.getServletContext().getAttribute(UserManagerAPI.class.getName()),
                    response);
        if (request.getSession().getAttribute(Driver.class.getName()) != null)
            addFbAccount((Driver) request.getSession().getAttribute(Driver.class.getName()), facebookId,
                    (SuperUserManager) request.getServletContext().getAttribute(DriverManagerAPI.class.getName()),
                    response);
        if (request.getSession().getAttribute(Company.class.getName()) != null)
            addFbAccount((Company) request.getSession().getAttribute(Company.class.getName()), facebookId,
                    (SuperUserManager) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName()),
                    response);
    }

    private void addFbAccount(GeneralCheckableInformation superUser, String facebookId, SuperUserManager um, HttpServletResponse response) throws IOException {
        System.out.println(superUser);
        System.out.println(facebookId);
        if (um == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            superUser.setFacebookID(facebookId);
            um.update(superUser);
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/");
        }
    }



}
