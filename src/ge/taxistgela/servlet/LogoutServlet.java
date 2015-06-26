package ge.taxistgela.servlet;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.GeneralCheckableInformation;
import ge.taxistgela.bean.User;
import ge.taxistgela.model.DriverManagerAPI;
import ge.taxistgela.model.SessionManagerAPI;

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
        DriverManagerAPI dm = (DriverManagerAPI) sc.getAttribute(DriverManagerAPI.class.getName());
        SessionManagerAPI sm = (SessionManagerAPI) sc.getAttribute(SessionManagerAPI.class.getName());

        if (dm == null || sm == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            for (int i = 0; i < A_TYPE.length; i++) {
                GeneralCheckableInformation info = (GeneralCheckableInformation) session.getAttribute(A_TYPE[i]);

                if (info instanceof Driver) {
                    dm.setDriverActiveStatus(((Driver) info).getDriverID(), false);
                }

                // if (i < 2)
                //   sm.removeSession(i, ((GeneralCheckableInformationTokened) info).getToken());

                session.removeAttribute(A_TYPE[i]);
            }
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }

}
