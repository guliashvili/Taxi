package ge.taxistgela.servlet;

import com.google.gson.Gson;
import ge.taxistgela.bean.*;
import ge.taxistgela.model.OrderManagerAPI;
import ge.taxistgela.model.UserManagerAPI;
import ge.taxistgela.ram.model.TaxRamAPI;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex on 6/26/2015.
 */
@WebServlet("/order")
public class OrderServlet extends ActionServlet {

    public void getOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderManagerAPI orderManager = (OrderManagerAPI) request.getServletContext().getAttribute(OrderManagerAPI.class.getName());

        if (request.getSession().getAttribute(User.class.getName()) != null) {
            getUserOrders(orderManager, request, response);
        } else if (request.getSession().getAttribute(Driver.class.getName()) != null) {
            getDriverOrders(orderManager, request, response);
        } else if (request.getSession().getAttribute(Company.class.getName()) != null) {
            getCompanyOrders(orderManager, request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void getCompanyOrders(OrderManagerAPI orderManager, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (orderManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Company company = (Company) request.getSession().getAttribute(Company.class.getName());
            List<Order> orders = orderManager.getOrdersByCompanyID(company.getCompanyID());

            printResponse(response, orders);
        }
    }

    private void getDriverOrders(OrderManagerAPI orderManager, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (orderManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());
            List<Order> orders = orderManager.getOrdersByDriverID(driver.getDriverID());

            printResponse(response, orders);
        }
    }

    private void getUserOrders(OrderManagerAPI orderManager, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (orderManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            User user = (User) request.getSession().getAttribute(User.class.getName());
            List<Order> orders = orderManager.getOrderByUserID(user.getUserID());

            printResponse(response, orders);
        }
    }

    private void printResponse(HttpServletResponse response, List<Order> orders) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().print(new Gson().toJson(orders));
    }

    public void addOrder(HttpServletRequest request, HttpServletResponse response) {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        if (taxRam == null || userManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            User user = (User) request.getSession().getAttribute(User.class.getName());

            if (user != null) {
                UserPreference userPreference = user.getPreference();

                try {
                    userPreference.setPassengersCount(Integer.parseInt(request.getParameter("passengerCount")));
                    userPreference.setTimeLimit(Integer.parseInt(request.getParameter("timeLimit")));
                    userPreference.setWantsAlone("on".equals(request.getParameter("wantsAlone")));
                    userPreference.setMinimumDriverRating(new Double(request.getParameter("minimumDriverRating")));
                    userPreference.setCarYear(Integer.parseInt(request.getParameter("carYear")));
                    userPreference.setConditioning("on".equals(request.getParameter("conditioning")));
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    return;
                }

                userManager.updateUserPreference(userPreference);

                Order order = new Order();

                try {
                    order.setStartLocation(new Location(
                            new Double(request.getParameter("startLatitude")),
                            new Double(request.getParameter("startLongitude"))
                    ));

                    order.setEndLocation(new Location(
                            new Double(request.getParameter("endLatitude")),
                            new Double(request.getParameter("endLongitude"))
                    ));

                    order.setUserID(user.getUserID());
                    order.setNumPassengers(userPreference.getPassengersCount());
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    return;
                }

                taxRam.addOrder(order);

                response.setStatus(HttpServletResponse.SC_CREATED);
                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
