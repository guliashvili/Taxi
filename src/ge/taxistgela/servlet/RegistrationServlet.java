package ge.taxistgela.servlet;

import ge.taxistgela.bean.*;
import ge.taxistgela.helper.EmailSender;
import ge.taxistgela.model.CompanyManagerAPI;
import ge.taxistgela.model.DriverManagerAPI;
import ge.taxistgela.model.SuperUserManager;
import ge.taxistgela.model.UserManagerAPI;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 6/25/2015.
 */
@WebServlet("/register")
public class RegistrationServlet extends ActionServlet {

    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());

        UserPreference userPreference = new UserPreference(-1, 0.1, false, 1900, Integer.MAX_VALUE, 5, false);

        User user = new User(
                -1,
                request.getParameter("useremail"),
                request.getParameter("userpassword"),
                request.getParameter("userfirstName"),
                request.getParameter("userlastName"),
                request.getParameter("userphoneNumber"),
                getGender(request.getParameter("usergender")),
                null,
                null,
                0.0,
                userPreference,
                false,
                false
        );

        registerSuper(userManager, user, request, response);
    }

    public void registerDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        DriverPreference driverPreference = new DriverPreference(-1, 0.1, 0.0);

        Car car = new Car("Unknown", "Untitled", 1900, false, 0);

        Driver driver = new Driver(
                -1,
                request.getParameter("driverpersonalID"),
                request.getParameter("driveremail"),
                request.getParameter("driverpassword"),
                companyManager.getCompanyIDByCode(request.getParameter("drivercompanyCode")),
                request.getParameter("driverfirstName"),
                request.getParameter("driverlastName"),
                getGender(request.getParameter("drivergender")),
                request.getParameter("driverphoneNumber"),
                car,
                null,
                null,
                new Location(0.0, 0.0),
                5.0,
                driverPreference,
                false,
                false,
                false
        );

        registerSuper(driverManager, driver, request, response);
    }

    public void registerCompany(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        Company company = new Company(
                -1,
                request.getParameter("companyCode"),
                request.getParameter("companyemail"),
                request.getParameter("companypassword"),
                request.getParameter("companyName"),
                request.getParameter("companyphoneNumber"),
                null,
                null,
                false,
                false
        );

        registerSuper(companyManager, company, request, response);
    }

    private void registerSuper(SuperUserManager man, GeneralCheckableInformation obj, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ErrorCode errorCode = null;

        if (man != null) {
            errorCode = man.register(obj);

            if (errorCode.errorNotAccrued()) {
                response.setStatus(HttpServletResponse.SC_CREATED);

                EmailSender.verifyEmail(obj);

                return;
            }
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        if (errorCode != null) {
            response.getWriter().print(errorCode.toJson());
        }
    }

    private Gender getGender(String usergender) {
        if (usergender == null) {
            return null;
        }

        Gender gender = null;

        if (usergender.equals("Male")) {
            gender = Gender.MALE;
        } else if (usergender.equals("Female")) {
            gender = Gender.FEMALE;
        }

        return gender;
    }
}
