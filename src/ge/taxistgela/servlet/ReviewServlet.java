package ge.taxistgela.servlet;

import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.Review;
import ge.taxistgela.bean.SuperDaoUser;
import ge.taxistgela.bean.User;
import ge.taxistgela.model.ReviewManagerAPI;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alex on 6/26/2015.
 */
@WebServlet("/review")
public class ReviewServlet extends ActionServlet {

    public void addUserReview(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(User.class.getName());

        addReview(user, false, request, response);
    }

    public void addDriverReview(HttpServletRequest request, HttpServletResponse response) {
        Driver driver = (Driver) request.getSession().getAttribute(Driver.class.getName());

        addReview(driver, true, request, response);
    }

    private void addReview(SuperDaoUser superUser, boolean orientationFlag, HttpServletRequest request, HttpServletResponse response) {
        ReviewManagerAPI reviewManager = (ReviewManagerAPI) request.getServletContext().getAttribute(ReviewManagerAPI.class.getName());

        if (reviewManager == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            if (superUser != null) {
                Integer orderID = null;
                Double rating = null;
                String description = null;

                try {
                    orderID = Integer.parseInt(request.getParameter("orderID"));
                    rating = new Double(request.getParameter("rating"));
                    description = request.getParameter("description");
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    return;
                }

                Review review = new Review(
                        -1,
                        orderID,
                        orientationFlag,
                        rating,
                        description
                );

                if (!reviewManager.addReview(review)) {
                    response.setStatus(HttpServletResponse.SC_CREATED);

                    return;
                }
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
