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

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.SuperDaoUser;
import ge.taxistgela.bean.User;
import ge.taxistgela.helper.EmailSender;
import ge.taxistgela.model.CompanyManagerAPI;
import ge.taxistgela.model.SmsQueue;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by Alex on 6/5/2015.
 */
@WebServlet("/sendverification")
public class SendVerificationServlet extends ActionServlet {
    public void uEmail(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(User.class.getName());

        sEmail(user, response);
    }

    public void uPhone(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        User user = (User) request.getSession().getAttribute(User.class.getName());

        sPhone(user, request, response);
    }

    public void dEmail(HttpServletRequest request, HttpServletResponse response) {
        Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

        sEmail(driver, response);
    }

    public void dPhone(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

        sPhone(driver, request, response);
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
                        return;
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

    public void cPhone(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Company company = (Company) request.getSession().getAttribute(Company.class.getName());

        sPhone(company, request, response);
    }

    private void sEmail(SuperDaoUser superUser, HttpServletResponse response) {
        if (superUser == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            EmailSender.verifyEmail(superUser);

            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private void sPhone(SuperDaoUser superUser, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        SmsQueue smsQueue = (SmsQueue) request.getServletContext().getAttribute(SmsQueue.class.getName());

        if (smsQueue == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            if (superUser != null) {
                if (!smsQueue.addSms(superUser)) {
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }

                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
