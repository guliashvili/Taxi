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
import ge.taxistgela.helper.EmailSender;
import ge.taxistgela.helper.GoogleReCaptchaValidation;
import ge.taxistgela.model.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 6/25/2015.
 */
@WebServlet("/register")
public class RegistrationServlet extends ActionServlet {


    private boolean verify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ErrorCode errorCode = new ErrorCode();
        String gRecaptchaResponse = request
                .getParameter("g-recaptcha-response");
        if (!GoogleReCaptchaValidation.verify(gRecaptchaResponse)) {
            errorCode.wrongCaptcha();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(errorCode.toJson());
            return false;
        }
        return true;
    }
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!verify(request, response)) return;

        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        UserPreference userPreference = new UserPreference(-1, 0.1, false, 1900, Integer.MAX_VALUE, 5, false);

        ErrorCode code = userManager.insertUserPreference(userPreference);
        if (code.errorAccrued()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        User user = new User(
                -1,
                request.getParameter("useremail"),
                request.getParameter("userpassword"),
                request.getParameter("userfirstName"),
                request.getParameter("userlastName"),
                request.getParameter("userphoneNumber"),
                getGender(request.getParameter("usergender")),
                filterSocialID(request.getParameter("userfacebookId")),
                filterSocialID(request.getParameter("usergoogleplusId")),
                5.0,
                userPreference,
                false,
                false
        );

        registerSuper(userManager, user, request, response);
    }

    public void registerDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!verify(request, response)) return;

        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        DriverPreference driverPreference = new DriverPreference(-1, 0.1, 0.0);

        ErrorCode code = driverManager.insertDriverPreference(driverPreference);
        if (code.errorAccrued()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        Car car = new Car(RandomStringUtils.randomAlphanumeric(7), "Untitled", 1900, false, 0);

        code = driverManager.insertCar(car);
        if (code.errorAccrued()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        Integer companyID = null;
        if (request.getParameter("drivercompanyCode") != "") {
            companyID = companyManager.getCompanyIDByCode(request.getParameter("drivercompanyCode"));
            if (companyID == null) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
        }

        Driver driver = new Driver(
                -1,
                request.getParameter("driverpersonalID"),
                request.getParameter("driveremail"),
                request.getParameter("driverpassword"),
                null,
                request.getParameter("driverfirstName"),
                request.getParameter("driverlastName"),
                getGender(request.getParameter("drivergender")),
                request.getParameter("driverphoneNumber"),
                car,
                filterSocialID(request.getParameter("driverfacebookId")),
                filterSocialID(request.getParameter("drivergoogleplusId")),
                5.0,
                driverPreference,
                false,
                false,
                false
        );

        registerSuper(driverManager, driver, request, response);

        if (response.getStatus() == HttpServletResponse.SC_CREATED) {
            Company company = (Company) companyManager.getByID(companyID);
            driver = (Driver) driverManager.getByEmail(driver.getEmail());

            if (company != null && driver != null) {
                EmailSender.verifyCompany(driver, company);
            }
        }
    }

    public void registerCompany(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!verify(request, response)) return;

        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        Company company = new Company(
                -1,
                request.getParameter("companyCode"),
                request.getParameter("companyemail"),
                request.getParameter("companypassword"),
                request.getParameter("companyName"),
                request.getParameter("companyphoneNumber"),
                filterSocialID(request.getParameter("companyfacebookId")),
                filterSocialID(request.getParameter("companygoogleplusId")),
                false,
                false
        );

        registerSuper(companyManager, company, request, response);
    }

    private void registerSuper(SuperUserManager man, SuperDaoUser obj, HttpServletRequest request, HttpServletResponse response) throws IOException {
        SmsQueue smsQueue = (SmsQueue) request.getServletContext().getAttribute(SmsQueue.class.getName());

        if (man == null || smsQueue == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {

            ErrorCode errorCode = new ErrorCode();

            errorCode.union(man.register(obj));


            if (errorCode.errorNotAccrued()) {
                response.setStatus(HttpServletResponse.SC_CREATED);

                EmailSender.verifyEmail(obj);

                smsQueue.addSms(obj);

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(errorCode.toJson());
        }
    }

    private Gender getGender(String usergender) {
        if (usergender == null) {
            return null;
        }

        Gender gender = null;

        if (usergender.toUpperCase().equals("MALE")) {
            gender = Gender.MALE;
        } else if (usergender.toUpperCase().equals("FEMALE")) {
            gender = Gender.FEMALE;
        }

        return gender;
    }

    private String filterSocialID(String socialID) {
        if (socialID != null && socialID.equals("")) {
            return null;
        }

        return socialID;
    }
}
