<%@ page import="ge.taxistgela.bean.Driver" %>
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

\<%--
  Created by IntelliJ IDEA.
  User: Ratmach
  Date: 19.06.2015
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/Resources/assets/js/driver.js"></script>
<% Driver driver = (Driver) session.getAttribute(Driver.class.getName());%>
<script>
    ga('send', 'pageview', {
        'page': '/driver.jsp',
        'title': 'Driver Panel'
    });
    initializeSockets("<%=driver.getToken()%>");
    clickChoice = "add";
</script>
<section id="map" style="position:absolute;width:100%;height:100%;">

</section>
<div class="modal fade" id="reviewModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body" style="margin-top:30%">
                <label for="reviewText">Rating:</label>
                <input type="number" id="reviewRating" step=".1" value="0">
                <label for="reviewText">Description:</label>
                <textarea id="reviewText" style="width:100%;height:25%" name="carDescription"
                          style="width:60%;font-size:1em"></textarea><br>
                <button class="special" onclick="addReview()">Add Review</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="orderModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div id="orderModalCont" class="modal-body">

            </div>
        </div>
    </div>
</div>
<button type="button" onclick="$('#routeDiv').toggleClass('cl');" id="routeDivToggle" class="btn btn-default" aria-label="Justify"><span style="font-size:4.5em" class="fa fa-align-justify" aria-hidden="true"></span></button>
<div id="routeDiv" class="cl">
</div>
<div class="prefPanel">
    <div class="row uniform" style="margin-left:10px">
        <br>

        <h2> Greetings <%=driver.getFirstName()%> <br></h2>

        <div class="5u$">
            <a href="#"><h3>Your Current Rating: <%= driver.getRating()%>
            </h3></a>
            <% for (int i = 1; i <= 5; ++i) { %>
            <% if (driver.getRating() >= i) { %>
            <li class="fa fa-star"></li>
            <% } else { %>
            <% if (Math.ceil(driver.getRating()) > i) { %>
            <li class="fa fa-star-half-o"></li>
            <% } else { %>
            <li class="fa fa-star-o"></li>
            <%} %>
            <%} %>
            <%} %>
        </div>
        <div class="6u 12u$(xsmall)">
            <input type="email" disabled value="<%=driver.getEmail()%>">
        </div>
        <div class="6u$ 12u$(xsmall)">
            <button class="fa fa-check-circle button small <% if(driver.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>" <%
                if (!driver.getIsVerifiedEmail()) {
                    out.println(" onclick='resendEmail()'");
                }
            %>>
                <% if (driver.getIsVerifiedEmail()) {
                    out.println("Verified");
                } else {
                    out.println("Verify");
                } %>
            </button>
        </div>
        <div class="6u 12u$(xsmall)">
            <input type="text" disabled value="<%=driver.getPhoneNumber()%>">
        </div>
        <div class="6u$ 12u$(xsmall)">
            <button class="fa fa-check-circle button small <% if(driver.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>" <%
                if (!driver.getIsVerifiedPhone()) {
                    out.println(" onclick='resendPhone()'");
                }
            %>>
                <% if (driver.getIsVerifiedPhone()) {
                    out.println("Verified");
                } else {
                    out.println("Verify");
                } %>
            </button>
        </div>
        <div class="12u$">
            <button onclick="$('.prefPanel').toggleClass('zero');$('#map').toggleClass('mOP');$('#panelToggle').toggleClass('hidden');"
                    class="button special fa fa-exchange"> Become Active
            </button>
        </div>
        <div class="12u$">
            <button onclick="$('#cPass').toggleClass('hidden');" class="button special fa fa-key"> Change Password
            </button>
        </div>
        <div id="cPass" class="5u$ hidden">
            <form id="passForm" action="" type="post">
                <span> Current Password: </span>
                <input name="oldPassword" type="password" value=""/>
                <span> New Password: </span>
                <input name="password" type="password" value=""/>
                <span> Repeat Password: </span>
                <input type="password" value=""/><br>
                <button id="passChange" class="special button">Save</button>
                <input type="text" name="action" value="dPassword" class="hidden"/>
            </form>
        </div>
        <div style="float:right" class="4u$ (xsmall)">
            <a href="#" style="float:left"
               class="<%if(driver.getGoogleID()==null){ %> disabled <%}%> icon fa-google-plus"><span
                    class="label">Google+</span></a>
            <% if (driver.getGoogleID() != null) { %>  Account Attached <% }%>
            <br><br>
            <% if (driver.getGoogleID() == null) { %>
            <div class="g-signin2" data-onsuccess="onSignIn"></div>
            <% } %>

            <br><br>
            <a href="#" style="float:left"
               class="<%if(driver.getFacebookID()==null){ %> disabled <%}%> icon fa-facebook"><span
                    class="label">Facebook</span></a>
            <% if (driver.getFacebookID() != null) { %>  Account Attached <% }%>
            <br><br>
            <% if (driver.getFacebookID() == null) { %> <fb:login-button scope="public_profile,email"
                                                                         onlogin="checkLoginState();"></fb:login-button> <% } %>
            <br><br>
        </div>

        <div class="5u$ 12u$(small)">
            <a href="#" onclick="$('#cP').toggleClass('hidden');" class="button special small fa fa-adjust"> Register To
                Company</a>
        </div>
        <div id="cP" class="5u$ 12u$(small) hidden">
            <form id="companyCodeForm" action="" type="post">
                <input name="companyCode" type="text" placeholder="Company Code"/><br>
                <input type="text" name="action" value="dCompany" class="hidden"/>
            </form>
            <button id="companyCodeBtn" style="float:right;" class="button special">Register</button>
        </div>
        <div class="5u$ 12u$(small)">
            <a href="#" onclick="$('#cPref').toggleClass('hidden');" class="button special small fa fa-adjust"> Edit
                Your Preferences</a>
        </div>
        <div id="cPref" class="6u$ hidden">
            <form id="cPrefForm">
                <input type="text" name="action" value="dPreferences" class="hidden"/>
                <label for="minimumUserRating"> Minimum User Rating </label>
                <input type="number" id="minimumUserRating" name="minimumUserRating"
                       value="<%=driver.getPreferences().getMinimumUserRating()%>" step="1"/>
                <label for="coefficientPer"> Coefficient Per KM. </label>
                <input type="number" id="coefficientPer" name="coefficientPer"
                       value="<%=driver.getPreferences().getCoefficientPer()%>" step=".1"/>
            </form>
            <button id="cPrefBtn" class="button special small fa fa-adjust"> Save</button>
        </div>
        <div class="12u$(small)">
            <a href="#" onclick="$('#cCar').toggleClass('hidden');" class="button special small fa fa-adjust"> Edit Your
                Car Details</a>
        </div>
        <br>

        <div id="cCar" class="12u hidden">
            <form id="cCarForm">
                <input type="text" name="action" value="dCar" class="hidden"/>
                <label for="carID"> Car Plate Number </label>
                <input type="text" id="carID" name="carID" style="width:250px" value="<%=driver.getCar().getCarID()%>"/><br>
                <input type="checkbox" id="conditioning"
                       name="conditioning" <%if(driver.getCar().hasConditioning()){out.println("checked");}%>>
                <label for="conditioning"> Conditioning </label>
                </input><br>
                <label for="carDescription"> Car Description </label>
                <textarea id="carDescription" name="carDescription"
                          style="width:60%;font-size:1em"><%=driver.getCar().getCarDescription()%></textarea>
                <label for="carYear"> Car Year </label>
                <input type="number" id="carYear" name="carYear" value="<%=driver.getCar().getCarYear()%>" step="1"/>
                <label for="numPassengers"> Max. Number Of Passengers </label>
                <input type="number" id="numPassengers" name="numPassengers"
                       value="<%=driver.getCar().getNumPassengers()%>" step="1"/><br>
            </form>
            <button id="cCarBtn" class="button special small fa fa-adjust"> Save</button>
        </div>
        <div class="12u 1u$(small)" style="float:left">
            <a href="#" onclick="$('#grid').toggleClass('hidden');" class="button special small fa fa-bar-chart">
                View Order History</a>
        </div>
        <div id="grid" class="hidden" style="color:black;width: 100%;padding:0px; height: 250px;"></div>
    </div>
</div>