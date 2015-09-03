<%@ page import="ge.taxistgela.admin.Admin" %>
<%@ page import="ge.taxistgela.bean.*" %>
<%@ page import="ge.taxistgela.model.*" %>
<%@ page import="java.util.List" %>
<%--
  ~
  ~         Copyright (C) 2015  Giorgi Guliashvili
  ~
  ~         This program is free software: you can redistribute it and/or modify
  ~         it under the terms of the GNU General Public License as published by
  ~         the Free Software Foundation, either version 3 of the License, or
  ~         (at your option) any later version.
  ~
  ~         This program is distributed in the hope that it will be useful,
  ~         but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~         GNU General Public License for more details.
  ~
  ~         You should have received a copy of the GNU General Public License
  ~         along with this program.  If not, see <http://www.gnu.org/licenses/>
  ~
  --%>

<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 6/30/2015
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Taxist Gela</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!--[if lte IE 8]>
    <script src="Resources/assets/js/ie/html5shiv.js"></script><![endif]-->
    <script type="text/javascript" src="/Resources/assets/js/jquery-2.1.4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="Resources/assets/css/w2ui-1.4.2.min.css"/>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css"
          rel="stylesheet">
    <link rel="stylesheet" href="Resources/assets/css/bootstrap.min.css">
    <script src="Resources/assets/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="Resources/assets/css/taxi.css">
    <link rel="stylesheet" href="Resources/assets/css/main.css"/>
    <script src="Resources/assets/js/jquery.scrollex.min.js"></script>
    <script src="Resources/assets/js/jquery.scrolly.min.js"></script>
    <script src="Resources/assets/js/skel.min.js"></script>
    <script src="Resources/assets/js/util.js"></script>
    <!--[if lte IE 8]>
    <script src="Resources/assets/js/ie/respond.min.js"></script><![endif]-->
    <script src="Resources/assets/js/main.js"></script>
    <script type="text/javascript" src="Resources/assets/js/w2ui-1.4.2.min.js"></script>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="Resources/assets/css/ie8.css"/><![endif]-->
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="Resources/assets/css/ie9.css"/><![endif]-->
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDYaoDG8Mj-4FtjBn1p18va0taQyb0KwBk">
    </script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
    <script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script>
        (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function () {
                        (i[r].q = i[r].q || []).push(arguments)
                    }, i[r].l = 1 * new Date();
            a = s.createElement(o),
                    m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
        })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

        ga('create', 'UA-XXXX-Y', 'auto');
        ga('send', 'pageview');
    </script>
</head>
<body class="landing">
<%
    Admin admin = (Admin) request.getSession().getAttribute(Admin.class.getName());

    if (admin == null) {
%>
<script>
    console.log("pass");
    $(function () {
        $("#loginModal").modal("show");
    })
</script>
<!-- Login Modal -->
<div id="loginModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Admin Panel</h5>
            </div>
            <div class="modal-body">
                <form id="adminForm" method="post" action="admin">

                    <div class="innerContainer">
                        <input type="hidden" name="action" value="login"/>
                        <input type="text" id="loginEmail" name="username" placeholder="Username" required><br>
                        <input type="password" name="password" placeholder="Password" required><br>
                        <button type="submit" class="btn btn-success btn-sm" style="float:right" id="adminBtn">Log
                            In
                        </button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
            </div>
        </div>

    </div>
</div>
<% } else { %>
<table class="table table-bordered" style="overflow: auto;font-size: x-small;">
    <tr>
        <td>#</td>
        <td>Password</td>
        <td>Email</td>
        <td>FirstName</td>
        <td>LastName</td>
        <td>PhoneNumber</td>
        <td>Gender</td>
        <td>Rating</td>
        <td>FacebookID</td>
        <td>GoogleID</td>
        <td>IsVerifiedEmail</td>
        <td>IsverifiedPhone</td>
        <td>MinimumDriverRating</td>
        <td>Conditioning</td>
        <td>CarYear</td>
        <td>PassengersCount</td>
        <td>WantsAlone</td>
        <td>TimeLimit</td>
    </tr>
    <%
        UserManagerAPI userManager = (UserManagerAPI) request.getServletContext().getAttribute(UserManagerAPI.class.getName());
        List<User> users = userManager.getAllUsers();

        for (User user : users) {
    %>
    <tr>
        <td><%=user.getUserID()%>
        </td>
        <td><%=user.getPassword()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getFirstName()%>
        </td>
        <td><%=user.getLastName()%>
        </td>
        <td><%=user.getPhoneNumber()%>
        </td>
        <td><%=user.getGender()%>
        </td>
        <td><%=user.getRating()%>
        </td>
        <td><%=user.getFacebookID()%>
        </td>
        <td><%=user.getGoogleID()%>
        </td>
        <td><%=user.getIsVerifiedEmail()%>
        </td>
        <td><%=user.getIsVerifiedPhone()%>
        </td>
        <td><%=user.getPreference().getMinimumDriverRating()%>
        </td>
        <td><%=user.getPreference().isConditioning()%>
        </td>
        <td><%=user.getPreference().getCarYear()%>
        </td>
        <td><%=user.getPreference().getPassengersCount()%>
        </td>
        <td><%=user.getPreference().isWantsAlone()%>
        </td>
        <td><%=user.getPreference().getTimeLimit()%>
        </td>
        <td>
            <button onclick="banUser(<%=user.getUserID()%>)">BAN</button>
        </td>
    </tr>
    <% } %>
</table>
<br><br>
<table class="table table-bordered" style="overflow: auto;font-size: x-small;">
    <tr>
        <td>#</td>
        <td>PersonalID</td>
        <td>Password</td>
        <td>Email</td>
        <td>CompanyID</td>
        <td>FirstName</td>
        <td>LastName</td>
        <td>PhoneNumber</td>
        <td>Gender</td>
        <td>Rating</td>
        <td>FacebookID</td>
        <td>GoogleID</td>
        <td>IsActive</td>
        <td>IsVerifiedEmail</td>
        <td>IsVerifiedPhone</td>
        <td>MinimumUserRating</td>
        <td>CoefficientPer</td>
        <td>CarID</td>
        <td>CarDescription</td>
        <td>CarYear</td>
        <td>NumPassengers</td>
    </tr>
    <%
        DriverManagerAPI driverManager = (DriverManagerAPI) request.getServletContext().getAttribute(DriverManagerAPI.class.getName());
        List<Driver> drivers = driverManager.getAllDrivers();

        for (Driver driver : drivers) {
    %>
    <tr>
        <td><%=driver.getDriverID()%>
        </td>
        <td><%=driver.getPersonalID()%>
        </td>
        <td><%=driver.getPassword()%>
        </td>
        <td><%=driver.getEmail()%>
        </td>
        <td><%=driver.getCompanyID()%>
        </td>
        <td><%=driver.getFirstName()%>
        </td>
        <td><%=driver.getLastName()%>
        </td>
        <td><%=driver.getPhoneNumber()%>
        </td>
        <td><%=driver.getGender()%>
        </td>
        <td><%=driver.getRating()%>
        </td>
        <td><%=driver.getFacebookID()%>
        </td>
        <td><%=driver.getGoogleID()%>
        </td>
        <td><%=driver.getIsActive()%>
        </td>
        <td><%=driver.getIsVerifiedEmail()%>
        </td>
        <td><%=driver.getIsVerifiedPhone()%>
        </td>
        <td><%=driver.getPreferences().getMinimumUserRating()%>
        </td>
        <td><%=driver.getPreferences().getCoefficientPer()%>
        </td>
        <td><%=driver.getCar().getCarID()%>
        </td>
        <td><%=driver.getCar().getCarDescription()%>
        </td>
        <td><%=driver.getCar().getCarYear()%>
        </td>
        <td><%=driver.getCar().getNumPassengers()%>
        </td>
        <td>
            <button onclick="banDriver(<%=driver.getDriverID()%>)">BAN</button>
        </td>
    </tr>
    <% } %>
</table>
<br><br>
<table class="table table-bordered" style="overflow: auto;font-size: x-small;">
    <tr>
        <td>#</td>
        <td>CompanyCode</td>
        <td>Email</td>
        <td>Password</td>
        <td>CompanyName</td>
        <td>PhoneNumber</td>
        <td>FacebookID</td>
        <td>GoogleID</td>
        <td>IsVerifiedEmail</td>
        <td>isVerifiedPhone</td>
    </tr>
    <%
        CompanyManagerAPI companyManager = (CompanyManagerAPI) request.getServletContext().getAttribute(CompanyManagerAPI.class.getName());
        List<Company> companies = companyManager.getAllCompanies();

        for (Company company : companies) {
    %>
    <tr>
        <td><%=company.getCompanyID()%>
        </td>
        <td><%=company.getCompanyCode()%>
        </td>
        <td><%=company.getEmail()%>
        </td>
        <td><%=company.getPassword()%>
        </td>
        <td><%=company.getCompanyName()%>
        </td>
        <td><%=company.getPhoneNumber()%>
        </td>
        <td><%=company.getFacebookID()%>
        </td>
        <td><%=company.getGoogleID()%>
        </td>
        <td><%=company.getIsVerifiedEmail()%>
        </td>
        <td><%=company.getIsVerifiedPhone()%>
        </td>
        <td>
            <button onclick="banCompany(<%=company.getCompanyID()%>)">BAN</button>
        </td>
    </tr>
    <% } %>
</table>
<br><br>
<table class="table table-bordered" style="overflow: auto;font-size: x-small;">
    <tr>
        <td>#</td>
        <td>OrderID</td>
        <td>OrientationFlag</td>
        <td>Rating</td>
        <td>Description</td>
    </tr>
    <%
        ReviewManagerAPI reviewManager = (ReviewManagerAPI) request.getServletContext().getAttribute(ReviewManagerAPI.class.getName());
        List<Review> reviews = reviewManager.getAllReviews();

        for (Review review : reviews) {
    %>
    <tr>
        <td><%=review.getReviewID()%>
        </td>
        <td><%=review.getOrderID()%>
        </td>
        <td><%=review.isOrientationFlag()%>
        </td>
        <td><%=review.getRating()%>
        </td>
        <td><%=review.getDescription()%>
        </td>
    </tr>
    <% } %>
</table>
<br><br>
<table class="table table-bordered" style="overflow: auto;font-size: x-small;">
    <tr>
        <td>#</td>
        <td>UserID</td>
        <td>DriverID</td>
        <td>NumPassengers</td>
        <td>StartLocationLat</td>
        <td>StartLocationLong</td>
        <td>EndLocationLat</td>
        <td>EndLocationLong</td>
        <td>StartTime</td>
        <td>EndTime</td>
        <td>PaymentAmoun</td>
        <td>CallTime</td>
        <td>RevokedByUser</td>
        <td>RevokedByDriver</td>
    </tr>
    <%
        OrderManagerAPI orderManager = (OrderManagerAPI) request.getServletContext().getAttribute(OrderManagerAPI.class.getName());
        List<Order> orders = orderManager.getAllOrders();

        for (Order order : orders) {
    %>
    <tr>
        <td><%=order.getOrderID()%>
        </td>
        <td><%=order.getUserID()%>
        </td>
        <td><%=order.getDriverID()%>
        </td>
        <td><%=order.getNumPassengers()%>
        </td>
        <td><%=order.getStartLocation().getLatitude()%>
        </td>
        <td><%=order.getStartLocation().getLongitude()%>
        </td>
        <td><%=order.getEndLocation().getLatitude()%>
        </td>
        <td><%=order.getEndLocation().getLongitude()%>
        </td>
        <td><%=order.getStartTime()%>
        </td>
        <td><%=order.getEndTime()%>
        </td>
        <td><%=order.getPaymentAmount()%>
        </td>
        <td><%=order.getCallTime()%>
        </td>
        <td><%=order.getRevokedByUser()%>
        </td>
        <td><%=order.getRevokedByDriver()%>
        </td>
    </tr>
    <% } %>
</table>
<% } %>
</body>
<script>
    function banUser(userID) {
        $.ajax({
            url: "/admin",
            method: "post",
            data: {action: "banUser", userID: userID},
            cache: false,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.error(data);
            }
        });
    }
    function banDriver(driverID) {
        $.ajax({
            url: "/admin",
            method: "post",
            data: {action: "banDriver", driverID: driverID},
            cache: false,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.error(data);
            }
        });
    }
    function banCompany(companyID) {
        $.ajax({
            url: "/admin",
            method: "post",
            data: {action: "banCompany", companyID: companyID},
            cache: false,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.error(data);
            }
        });
    }
</script>
</html>
