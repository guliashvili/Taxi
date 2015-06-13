package ge.taxistgela.listener; /**
 * Created by Alex on 6/5/2015.
 */

import ge.taxistgela.dispatcher.OrderDispatcher;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.model.SessionManager;
import ge.taxistgela.model.SessionManagerAPI;

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
        sc.setAttribute(SessionManagerAPI.class.getName(), new SessionManager());

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
        sce.getServletContext().removeAttribute(SessionManagerAPI.class.getName());

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
