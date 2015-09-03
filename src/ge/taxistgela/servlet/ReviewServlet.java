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
