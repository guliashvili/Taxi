package ge.taxistgela.servlet;

import ge.taxistgela.helper.ExternalAlgorithms;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Alex on 6/5/2015.
 */
public abstract class ActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Invokes the certain method depending on the certain action.
     * Example: action=hello => hello(request, response)
     *
     * @param request
     * @param response
     */
    private void process(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        ExternalAlgorithms.debugPrint(action);
        
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
