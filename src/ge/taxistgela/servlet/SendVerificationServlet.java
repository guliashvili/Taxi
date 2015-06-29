package ge.taxistgela.servlet;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.SuperDaoUser;
import ge.taxistgela.bean.User;
import ge.taxistgela.helper.EmailSender;
import ge.taxistgela.model.CompanyManagerAPI;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alex on 6/5/2015.
 */
@WebServlet("/sendverification")
public class SendVerificationServlet extends ActionServlet {

    public void uEmail(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(User.class.getName());

        sEmail(user, response);
    }

    public void uPhone(HttpServletRequest request, HttpServletResponse response) {

    }

    public void dEmail(HttpServletRequest request, HttpServletResponse response) {
        Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

        sEmail(driver, response);
    }

    public void dPhone(HttpServletRequest request, HttpServletResponse response) {

    }

    public void dCompany(HttpServletRequest request, HttpServletResponse response) {
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());

        if (companyManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

            if (driver != null) {
                Integer companyID = companyManager.getCompanyIDByCode(request.getParameter("companyCode"));

                if (companyID != null) {
                    Company company = (Company) companyManager.getByID(companyID);

                    if (company != null) {
                        EmailSender.verifyCompany(driver, company);

                        response.setStatus(HttpServletResponse.SC_OK);
                    }
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void cEmail(HttpServletRequest request, HttpServletResponse response) {
        Company company = (Company) request.getSession().getAttribute(Company.class.getName());

        sEmail(company, response);
    }

    public void cPhone(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sEmail(SuperDaoUser superUser, HttpServletResponse response) {
        if (superUser == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            EmailSender.verifyEmail(superUser);

            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
