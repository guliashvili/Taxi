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

package ge.taxistgela.listener;
/**
 * Created by Alex on 6/5/2015.
 */

import ge.taxistgela.dao.*;
import ge.taxistgela.model.*;
import ge.taxistgela.ram.model.TaxRam;
import ge.taxistgela.ram.model.TaxRamAPI;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class ContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public ContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        DriverDao driverDao = new DriverDao();
        UserDao userDao = new UserDao();
        CompanyDao companyDao = new CompanyDao();
        OrderDao orderDao = new OrderDao();
        ReviewDao reviewDao = new ReviewDao();
        UserManager um = new UserManager(userDao);
        DriverManager dm = new DriverManager(driverDao);
        SessionManager sessionManager = new SessionManager(new SuperUserTokenedManager[]{um, dm});

        ServletContext sc = sce.getServletContext();
        TaxRamAPI ram = new TaxRam(orderDao, userDao, driverDao, sessionManager);

        // add CompanyManager.
        sc.setAttribute(CompanyManagerAPI.class.getName(), new CompanyManager(companyDao));

        // add DriverManager.

        sc.setAttribute(DriverManagerAPI.class.getName(), dm);

        // add OrderManager.
        sc.setAttribute(OrderManagerAPI.class.getName(), new OrderManager(orderDao));

        // add ReviewManager.
        sc.setAttribute(ReviewManagerAPI.class.getName(), new ReviewManager(reviewDao));

        // add UserManager.

        sc.setAttribute(UserManagerAPI.class.getName(), um);

        // add SessionManager.
        sc.setAttribute(SessionManagerAPI.class.getName(), sessionManager);

        // add TaxRam.
        sc.setAttribute(TaxRamAPI.class.getName(), ram);

        // add SmsQueue.
        sc.setAttribute(SmsQueue.class.getName(), new SmsQueue());
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        ServletContext sc = sce.getServletContext();

        // remove CompanyManager.
        sc.removeAttribute(CompanyManagerAPI.class.getName());

        // remove DriverManager.
        sc.removeAttribute(DriverManagerAPI.class.getName());

        // remove OrderManager.
        sc.removeAttribute(OrderManagerAPI.class.getName());

        // remove ReviewManager.
        sc.removeAttribute(ReviewManagerAPI.class.getName());

        // remove SessionManager.
        sc.removeAttribute(SessionManagerAPI.class.getName());

        // remove UserManager.
        sc.removeAttribute(UserManagerAPI.class.getName());

        // remove TaxRam.
        sc.removeAttribute(TaxRamAPI.class.getName());

        // remove SmsQueue.
        sc.removeAttribute(SmsQueue.class.getName());
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
