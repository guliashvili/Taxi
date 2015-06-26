package ge.taxistgela.servlet;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.GeneralCheckableInformation;
import ge.taxistgela.bean.User;
import ge.taxistgela.dao.CompanyDao;
import ge.taxistgela.dao.DriverDao;
import ge.taxistgela.dao.UserDao;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.model.*;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alex on 6/22/2015.
 */
@WebServlet("/social")
public class SocialNetworkServlet extends ActionServlet {

    public void addFbAccount(HttpServletRequest request, HttpServletResponse response){
        String facebookId = request.getParameter("facebookID");
        if (request.getSession().getAttribute(User.class.getName()) != null)
            addUserFb((User)request.getSession().getAttribute(User.class.getName()), facebookId, request, response);
        if (request.getSession().getAttribute(Driver.class.getName()) != null)
            addDriverFb((Driver)request.getSession().getAttribute(Driver.class.getName()), facebookId, request, response);
        if (request.getSession().getAttribute(Company.class.getName()) != null)
            addCompanyFb((Company)request.getSession().getAttribute(Company.class.getName()), facebookId, request, response);
    }

    private void addUserFb(User user, String facebookId, HttpServletRequest request, HttpServletResponse response){
        ServletContext sc = request.getServletContext();
        UserManager um = (UserManager) sc.getAttribute(UserManager.class.getName());
        if (um == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            user.setFacebookID(facebookId);
            um.update(user);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private void addDriverFb(Driver driver, String facebookId, HttpServletRequest request, HttpServletResponse response){
        ServletContext sc = request.getServletContext();
        DriverManagerAPI dm = (DriverManagerAPI) sc.getAttribute(DriverManager.class.getName());
        if (dm == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            driver.setFacebookID(facebookId);
            dm.update(driver);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private void addCompanyFb(Company company, String facebookId, HttpServletRequest request, HttpServletResponse response){
        ServletContext sc = request.getServletContext();
        CompanyManagerAPI cm = (CompanyManagerAPI) sc.getAttribute(CompanyManager.class.getName());
        if (cm == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            company.setFacebookID(facebookId);
            cm.update(company);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }




}
