/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

package ge.taxistgela.servlet;

import ge.taxistgela.bean.*;
import ge.taxistgela.model.CompanyManagerAPI;
import ge.taxistgela.model.DriverManagerAPI;
import ge.taxistgela.model.SuperUserManager;
import ge.taxistgela.model.UserManagerAPI;
import ge.taxistgela.ram.model.TaxRamAPI;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 6/26/2015.
 */
@WebServlet("/update")
public class UpdateServlet extends ActionServlet {

    private static final String[] A_TYPE = {
            User.class.getName(),
            Driver.class.getName(),
            Company.class.getName()
    };

    public void uPreferences(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        if (userManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            User user = (User) request.getSession().getAttribute(User.class.getName());

            ErrorCode errorCode = null;

            if (user != null) {
                UserPreference userPreference = user.getPreference();

                try {
                    userPreference.setMinimumDriverRating(new Double(request.getParameter("minimumDriverRating")));
                    userPreference.setConditioning("on".equals(request.getParameter("conditioning")));
                    userPreference.setCarYear(Integer.parseInt(request.getParameter("carYear")));
                    userPreference.setTimeLimit(Integer.parseInt(request.getParameter("timeLimit")));
                    userPreference.setPassengersCount(Integer.parseInt(request.getParameter("passengerCount")));
                    userPreference.setWantsAlone("on".equals(request.getParameter("wantsAlone")));
                } catch (Exception ex) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    updateSessionUser(User.class.getName(), userManager, request, user);

                    return;
                }

                errorCode = userManager.updateUserPreference(userPreference);

                if (errorCode.errorNotAccrued()) {
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);

                    updateSessionUser(User.class.getName(), userManager, request, user);

                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (errorCode != null) {
                response.getWriter().print(errorCode.toJson());
            }

            updateSessionUser(User.class.getName(), userManager, request, user);
        }
    }

    public void dPreferences(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        if (driverManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

            ErrorCode errorCode = null;

            if (driver != null) {
                DriverPreference driverPreference = driver.getPreferences();

                try {
                    driverPreference.setMinimumUserRating(new Double(request.getParameter("minimumUserRating")));
                    driverPreference.setCoefficientPer(new Double(request.getParameter("coefficientPer")));
                } catch (Exception ex) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    updateSessionUser(Driver.class.getName(), driverManager, request, driver);

                    return;
                }

                errorCode = driverManager.updateDriverPreference(driverPreference);

                if (errorCode.errorNotAccrued()) {
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);

                    updateSessionUser(Driver.class.getName(), driverManager, request, driver);

                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (errorCode != null) {
                response.getWriter().print(errorCode.toJson());
            }

            updateSessionUser(Driver.class.getName(), driverManager, request, driver);
        }
    }

    public void uPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        sPassword(userManager, 0, request, response);
    }

    public void dPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        sPassword(driverManager, 1, request, response);
    }

    public void cPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        sPassword(companyManager, 2, request, response);
    }

    private void sPassword(SuperUserManager man, int type, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (man == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            SuperDaoUser obj = (SuperDaoUser) request.getSession().getAttribute(A_TYPE[type]);

            ErrorCode errorCode = null;

            if (obj != null) {
                String oldPassword = request.getParameter("oldPassword");
                String password = request.getParameter("password");

                obj.setPassword(password);

                errorCode = man.changePassword(obj, oldPassword);

                if (errorCode.errorNotAccrued()) {
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);

                    updateSessionUser(A_TYPE[type], man, request, obj);
                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (errorCode != null) {
                response.getWriter().print(errorCode.toJson());
            }

            updateSessionUser(A_TYPE[type], man, request, obj);
        }
    }

    public void dLocation(HttpServletRequest request, HttpServletResponse response) {
        TaxRamAPI taxRam = (TaxRamAPI) request.getServletContext().getAttribute(TaxRamAPI.class.getName());

        if (taxRam == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

            if (driver != null) {
                Location location = new Location(
                        new Double(request.getParameter("latitude")),
                        new Double(request.getParameter("longitude"))
                );
                taxRam.updateDriverLocation(driver.getDriverID(), location);

                response.setStatus(HttpServletResponse.SC_ACCEPTED);

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void dCompanyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute((CompanyManagerAPI.class.getName()));

        if (driverManager == null || companyManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());
            String companyCode = request.getParameter("companyCode");

            ErrorCode errorCode = null;

            if (driver != null && companyCode != null) {
                Integer companyID = companyManager.getCompanyIDByCode(companyCode);

                driver.setCompanyID(companyID);

                errorCode = driverManager.update(driver);

                if (errorCode.errorNotAccrued()) {
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);

                    updateSessionUser(Driver.class.getName(), driverManager, request, driver);

                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (errorCode != null) {
                response.getWriter().print(errorCode.toJson());
            }

            updateSessionUser(Driver.class.getName(), driverManager, request, driver);
        }
    }

    public void dCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());

        if (driverManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

            ErrorCode errorCode = null;

            if (driver != null) {
                Car car = driver.getCar();

                try {
                    car.setCarID(request.getParameter("carID"));
                    car.setConditioning("on".equals(request.getParameter("conditioning")));
                    car.setCarDescription(request.getParameter("carDescription"));
                    car.setCarYear(Integer.parseInt(request.getParameter("carYear")));
                    car.setNumPassengers(Integer.parseInt(request.getParameter("numPassengers")));
                } catch (Exception ex) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    updateSessionUser(Driver.class.getName(), driverManager, request, driver);
                    return;
                }

                if (driverManager.getCarByID(car.getCarID()) == null) {
                    errorCode = driverManager.insertCar(car);
                    errorCode.union(driverManager.update(driver));
                } else {
                    errorCode = driverManager.updateCar(car);
                }

                if (errorCode.errorNotAccrued()) {
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);

                    updateSessionUser(Driver.class.getName(), driverManager, request, driver);
                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (errorCode != null) {
                response.getWriter().print(errorCode.toJson());
            }

            updateSessionUser(Driver.class.getName(), driverManager, request, driver);
        }
    }

    private void updateSessionUser(String aType, SuperUserManager um, HttpServletRequest request, SuperDaoUser superUser) {
        superUser = um.getByEmail(superUser.getEmail());
        request.getSession().setAttribute(aType, superUser);
    }
}
