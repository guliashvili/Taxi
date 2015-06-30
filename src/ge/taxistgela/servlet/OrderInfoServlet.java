package ge.taxistgela.servlet;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.User;
import ge.taxistgela.ram.model.TaxRamAPI;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 6/30/2015.
 */
@WebServlet("/orderinfo")
public class OrderInfoServlet extends ActionServlet {

    public void getDriverInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());

        if (taxRam == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

            if (driver != null) {
                taxRam.getRouteDriver(driver.getDriverID());
                taxRam.getWaitingUsers(driver.getDriverID());

                response.setStatus(HttpServletResponse.SC_OK);

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());

        if (taxRam == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            User user = (User) request.getSession().getAttribute(User.class.getName());

            if (user != null) {
                taxRam.getRouteUser(user.getUserID());
                taxRam.getWaitingDrivers(user.getUserID());

                response.setStatus(HttpServletResponse.SC_OK);

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void driverAccept(HttpServletRequest request, HttpServletResponse response) {
        driverChoice(true, request, response);
    }

    public void driverReject(HttpServletRequest request, HttpServletResponse response) {
        driverChoice(false, request, response);
    }

    private void driverChoice(boolean choice, HttpServletRequest request, HttpServletResponse response) {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());

        if (taxRam == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

            if (driver != null) {
                Integer userID = null;
                Integer orderID = null;

                try {
                    userID = Integer.parseInt(request.getParameter("userID"));
                    orderID = Integer.parseInt(request.getParameter("orderID"));
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    return;
                }

                if (!taxRam.driverChoice(driver.getDriverID(), userID, orderID, true)) {
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);

                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void userAccept(HttpServletRequest request, HttpServletResponse response) {
        userChoice(true, request, response);
    }

    public void userReject(HttpServletRequest request, HttpServletResponse response) {
        userChoice(false, request, response);
    }

    private void userChoice(boolean choice, HttpServletRequest request, HttpServletResponse response) {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());

        if (taxRam == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            User user = (User) request.getSession().getAttribute(User.class.getName());

            if (user != null) {
                Integer driverID = null;
                Integer orderID = null;

                try {
                    driverID = Integer.parseInt(request.getParameter("driverID"));
                    orderID = Integer.parseInt(request.getParameter("orderID"));
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    return;
                }

                if (!taxRam.userChoice(driverID, user.getUserID(), orderID, choice)) {
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);

                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void pickUser(HttpServletRequest request, HttpServletResponse response) {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());

        if (taxRam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

            if (driver != null) {
                Integer userID = null;
                Integer orderID = null;

                try {
                    userID = Integer.parseInt(request.getParameter("userID"));
                    orderID = Integer.parseInt(request.getParameter("orderID"));
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    return;
                }

                if (!taxRam.pickUser(driver.getDriverID(), orderID, userID)) {
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);

                    return;
                }

            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void leaveUser(HttpServletRequest request, HttpServletResponse response) {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());

        if (taxRam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

            if (driver != null) {
                Integer userID = null;
                Integer orderID = null;

                try {
                    userID = Integer.parseInt(request.getParameter("userID"));
                    orderID = Integer.parseInt(request.getParameter("orderID"));
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    return;
                }

                double ret = taxRam.leaveUser(driver.getDriverID(), orderID, userID);
                if (ret >= 0) {
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);

                    return;
                }

            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void revokeOrderUser(HttpServletRequest request, HttpServletResponse response) {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());

        if (taxRam == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            User user = (User) request.getSession().getAttribute(User.class.getName());

            if (user != null) {
                taxRam.revokeOrderUser(user.getUserID());

                response.setStatus(HttpServletResponse.SC_ACCEPTED);

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void revokeOrderDriver(HttpServletRequest request, HttpServletResponse response) {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());

        if (taxRam == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(User.class.getName());

            if (driver != null) {
                Integer userID = null;

                try {
                    userID = Integer.parseInt(request.getParameter("userID"));
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    return;
                }

                taxRam.revokeOrderDriver(userID);

                response.setStatus(HttpServletResponse.SC_ACCEPTED);

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
