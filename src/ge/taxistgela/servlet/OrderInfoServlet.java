package ge.taxistgela.servlet;

import com.google.gson.Gson;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.User;
import ge.taxistgela.ram.bean.OrderInfo;
import ge.taxistgela.ram.model.TaxRamAPI;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex on 6/30/2015.
 */
@WebServlet("/orderinfo")
public class OrderInfoServlet extends ActionServlet {

    public void getWaitingUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());

        if (taxRam == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

            if (driver != null) {
                List<OrderInfo> users = taxRam.getWaitingUsers(driver.getDriverID());

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().print(new Gson().toJson(users));

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void getWaitingDrivers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());

        if (taxRam == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            User user = (User) request.getSession().getAttribute(User.class.getName());

            if (user != null) {
                List<OrderInfo> drivers = taxRam.getWaitingDrivers(user.getUserID());

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().print(new Gson().toJson(drivers));

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
