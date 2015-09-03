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

import ge.taxistgela.bean.*;
import ge.taxistgela.model.DriverManagerAPI;
import ge.taxistgela.model.SessionManagerAPI;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alex on 6/25/2015.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final String[] A_TYPE = {
            User.class.getName(),
            Driver.class.getName(),
            Company.class.getName()
    };

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        ServletContext sc = request.getServletContext();
        DriverManagerAPI dm = (DriverManagerAPI) sc.getAttribute(DriverManagerAPI.class.getName());
        SessionManagerAPI sm = (SessionManagerAPI) sc.getAttribute(SessionManagerAPI.class.getName());

        if (dm == null || sm == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            for (int i = 0; i < A_TYPE.length; i++) {
                SuperDaoUser info = (SuperDaoUser) session.getAttribute(A_TYPE[i]);

                if (info == null)
                    continue;

                if (info instanceof Driver) {
                    dm.setDriverActiveStatus(((Driver) info).getDriverID(), false);
                }

                if (i < 2)
                    sm.removeSession(i, ((SuperPersonalTokenUser) info).getToken());

                session.removeAttribute(A_TYPE[i]);
            }
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }

}
