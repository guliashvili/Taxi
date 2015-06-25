package ge.taxistgela.listener; /**
 * Created by Alex on 6/5/2015.
 */

import ge.taxistgela.dao.*;
import ge.taxistgela.dispatcher.OrderDispatcher;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.model.*;

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
        ServletContext sc = sce.getServletContext();

        // add CompanyManager.
        sc.setAttribute(CompanyManagerAPI.class.getName(), new CompanyManager(new CompanyDao()));

        // add DriverManager.
        sc.setAttribute(DriverManagerAPI.class.getName(), new DriverManager(new DriverDao()));

        // add OrderManager.
        sc.setAttribute(OrderManagerAPI.class.getName(), new OrderManager(new OrderDao()));

        // add ReviewManager.
        sc.setAttribute(ReviewManagerAPI.class.getName(), new ReviewManager(new ReviewDao()));

        // add SessionManager.
        sc.setAttribute(SessionManagerAPI.class.getName(), new SessionManager());

        // add UserManagerManager.
        sc.setAttribute(UserManagerManagerAPI.class.getName(), new UserManagerManager(new UserDao()));

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

        // remove UserManagerManager.
        sc.removeAttribute(UserManagerManagerAPI.class.getName());

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
