package ge.taxistgela.servlet;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.GeneralCheckableInformation;
import ge.taxistgela.bean.User;
import ge.taxistgela.model.DriverManager;
import ge.taxistgela.model.DriverManagerAPI;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alex on 6/25/2015.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final String[] A_TYPE = {
            User.class.getName(),
            Driver.class.getName(),
            Company.class.getName()
    };

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        ServletContext sc = request.getServletContext();
        DriverManagerAPI dm = (DriverManagerAPI)sc.getAttribute(DriverManagerAPI.class.getName());
        for (String s : A_TYPE){
            GeneralCheckableInformation info = (GeneralCheckableInformation) session.getAttribute(s);
            if (info instanceof Driver){
                Driver d = (Driver)info;
                d.setIsActive(false);
                dm.update(d);
            }
            //TODO remove socket session
            session.removeAttribute(s);
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }

}
