package ge.taxistgela.listener; /**
 * Created by Alex on 6/5/2015.
 */

import ge.taxistgela.dao.*;
import ge.taxistgela.dispatcher.OrderDispatcher;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.model.*;
import ge.taxistgela.ram.TaxRam;
import ge.taxistgela.ram.TaxRamAPI;

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

        ServletContext sc = sce.getServletContext();
        TaxRamAPI ram = new TaxRam(orderDao, userDao, driverDao);

        // add CompanyManager.
        sc.setAttribute(CompanyManagerAPI.class.getName(), new CompanyManager(companyDao, ram));

        // add DriverManager.
        DriverManager dm = new DriverManager(driverDao, ram);
        sc.setAttribute(DriverManagerAPI.class.getName(), dm);

        // add OrderManager.
        sc.setAttribute(OrderManagerAPI.class.getName(), new OrderManager(orderDao));

        // add ReviewManager.
        sc.setAttribute(ReviewManagerAPI.class.getName(), new ReviewManager(reviewDao));

        // add UserManager.
        UserManager um = new UserManager(userDao, ram);
        sc.setAttribute(UserManagerAPI.class.getName(), um);

        // add SessionManager.
        sc.setAttribute(SessionManagerAPI.class.getName(), new SessionManager(new SuperUserTokenedManager[]{um, dm}));

        // add TaxRam.
        sc.setAttribute(TaxRamAPI.class.getName(), ram);

        // add OrderDispatcher.
        OrderDispatcher orderDispatcher = new OrderDispatcher(sc);
        sc.setAttribute(OrderDispatcher.class.getName(), orderDispatcher);
        ExternalAlgorithms.debugPrint("Starting order dispatcher...");
        orderDispatcher.start();


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

        // remove OrderDispatcher.
        OrderDispatcher orderDispatcher = (OrderDispatcher) sce.getServletContext().getAttribute(OrderDispatcher.class.getName());
        orderDispatcher.cancel();
        sce.getServletContext().removeAttribute(OrderDispatcher.class.getName());
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
