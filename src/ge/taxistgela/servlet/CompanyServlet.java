package ge.taxistgela.servlet;

import com.google.gson.Gson;
import ge.taxistgela.bean.*;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.model.DriverManagerAPI;
import ge.taxistgela.model.OrderManagerAPI;
import ge.taxistgela.model.ReviewManagerAPI;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex on 6/30/2015.
 */
@WebServlet("/company")
public class CompanyServlet extends ActionServlet {

    public void getDrivers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        if (driverManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Company company = (Company) request.getSession().getAttribute(Company.class.getName());

            if (company != null) {
                List<Driver> drivers = driverManager.getDriverByCompanyID(company.getCompanyID());

                if (drivers != null) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("application/json");
                    response.getWriter().print(new Gson().toJson(drivers));

                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void getOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderManagerAPI orderManager = (OrderManagerAPI) request.getServletContext().getAttribute(OrderManagerAPI.class.getName());

        if (orderManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Company company = (Company) request.getSession().getAttribute(Company.class.getName());

            if (company != null) {
                List<Order> orders = orderManager.getOrdersByCompanyID(company.getCompanyID());

                if (orders != null) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("application/json");
                    response.getWriter().print(ExternalAlgorithms.parseToJson(orders));

                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void fireDriver(HttpServletRequest request, HttpServletResponse response) {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        if (driverManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Company company = (Company) request.getSession().getAttribute(Company.class.getName());

            if (company != null) {
                String sDriverID = request.getParameter("driverID");

                Integer driverID = null;

                try {
                    driverID = Integer.parseInt(sDriverID);
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    return;
                }

                Driver driver = (Driver) driverManager.getByID(driverID);

                if (driver != null) {
                    driver.setCompanyID(null);

                    ErrorCode errorCode = driverManager.update(driver);

                    if (errorCode.errorNotAccrued()) {
                        response.setStatus(HttpServletResponse.SC_ACCEPTED);

                        return;
                    }
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void getReviews(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ReviewManagerAPI reviewManager = (ReviewManagerAPI) request.getServletContext().getAttribute(ReviewManagerAPI.class.getName());

        if (reviewManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Company company = (Company) request.getSession().getAttribute(Company.class.getName());

            if (company != null) {
                List<Review> reviews = reviewManager.getReviewByCompanyID(company.getCompanyID());

                if (reviews != null) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("application/json");
                    response.getWriter().print(new Gson().toJson(reviews));

                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
