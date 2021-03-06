<%@ page import="ge.taxistgela.bean.User" %>
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
  User: Ratmach
  Date: 19.06.2015
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/Resources/assets/js/user.js"></script>
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
<% User user = (User) session.getAttribute(User.class.getName());%>
<script>
    ga('send', 'pageview', {
        'page': '/user.jsp',
        'title': 'User Panel'
    });
    initializeSockets("<%=user.getToken()%>");
    clickChoice = "add";
</script>
<div class="prefPanel">
    <div class="row uniform" style="margin-left:10px">
        <br>

        <h2> Greetings <%= user.getFirstName()%> <br></h2>

        <div class="5u$">
            <a href="#"><h3>Your Current Rating: <%= user.getRating()%>
            </h3></a>
            <% for (int i = 1; i <= 5; ++i) { %>
            <% if (user.getRating() >= i) { %>
            <li class="fa fa-star"></li>
            <% } else { %>
            <% if (Math.ceil(user.getRating()) > i) { %>
            <li class="fa fa-star-half-o"></li>
            <% } else { %>
            <li class="fa fa-star-o"></li>
            <%} %>
            <%} %>
            <%} %>
        </div>
        <div id="addOrdBtn1" class="12u$ (small) fit" style="float:left">
            <a href="#"
               onclick="minimize()"
               class="button special small fa fa-bar-chart"> Show Map</a>
        </div>
        <div id="revokeOrderBTN" class="12u$ (small) fit" style="float:left">
            <a href="#"
               onclick="revokeOrder()"
               class="button special small fa fa-bar-chart"> Revoke </a>
        </div>
        <br><br><br><br>

        <div class="6u 12u$(xsmall)">
            <input type="email" disabled value="<%=user.getEmail()%>">
        </div>
        <div class="6u$ 12u$(xsmall)">
            <button class="fa fa-check-circle button small <% if(user.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>" <%
                if (!user.getIsVerifiedEmail()) {
                    out.println(" onclick='resendEmail()'");
                }
            %>>
                <% if (user.getIsVerifiedEmail()) {
                    out.println("Verified");
                } else {
                    out.println("Verify");
                } %>
            </button>
        </div>
        <div class="6u 12u$(xsmall)">
            <input type="text" disabled value="<%=user.getPhoneNumber()%>">
        </div>
        <div class="6u$ 12u$(xsmall)">
            <button class="fa fa-check-circle button small <% if(user.getIsVerifiedPhone()){out.println("disabled verified");}else{out.println("special");}%>" <%
                if (!user.getIsVerifiedPhone()) {
                    out.println(" onclick='resendPhone()'");
                }
            %>>
                <%
                    if (user.getIsVerifiedPhone()) {
                        out.println("Verified");
                    } else {
                        out.println("Verify");
                    } %>
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
                <input type="text" name="action" value="uPassword" class="hidden"/>
            </form>
            <button id="passChange" class="special button">Save</button>
        </div>
        <br>

        <div style="float:right" class="4u$ (xsmall)">
            <a href="#" style="float:left"
               class="<%if(user.getGoogleID()==null){ %> disabled <%}%> icon fa-google-plus"><span
                    class="label">Google+</span></a>
            <% if (user.getGoogleID() != null) { %>  Account Attached <% }%>
            <br><br>
            <%if (user.getGoogleID() == null) { %>
            <div class="g-signin2" data-onsuccess="onSignIn"></div>
            <%}%>
            <br><br>
            <a href="#" style="float:left"
               class="<%if(user.getFacebookID()==null){ %> disabled <%}%> icon fa-facebook"><span
                    class="label">Facebook</span></a>
            <% if (user.getFacebookID() != null) { %>  Account Attached <% }%>
            <br><br>
            <%if (user.getFacebookID() == null) { %> <fb:login-button scope="public_profile,email"
                                                                      onlogin="checkLoginState();"></fb:login-button> <%}%>
            <br><br>
        </div>

        <br>
    </div>
    <div class="5u$ 12u$(small)">
        <a href="#" onclick="$('#cPref').toggleClass('hidden');" class="button special small fa fa-adjust"> Edit
            Your Preferences</a>
    </div>
    <div id="cPref" class="6u$ hidden">
        <form id="prefForm" action="" type="post">
            <input type="text" name="action" value="uPreferences" class="hidden"/>
            <input type="number" id="minimumDriverRating" name="minimumDriverRating"
                   value="<%=user.getPreference().getPassengersCount()%>"
                   style="color:black;padding-left:5px" value="1" step="1">
            <label for="minimumDriverRating"> Minimum Driver Rating </label><br>
            <input type="checkbox" id="conditioning"
                   name="conditioning" <% if(user.getPreference().isConditioning()){%> checked <%}%>>
            <label for="conditioning"> Conditioning Required </label>
            </input><br>
            <input type="number" name="carYear" value="<%=user.getPreference().getCarYear()%>" id="carYear"
                   style="color:black;padding-left:5px" value="1990" step="1">
            <label for="carYear"> Minimum Car Year </label>
            <input type="number" name="timeLimit" id="timeLimit" value="<%=user.getPreference().getTimeLimit()%>"
                   style="color:black;padding-left:5px" value="10" step="1">
            <label for="timeLimit"> Maximum Time Limit (Minutes) </label>
            <input type="number" name="passengerCount" id="passengerCount"
                   value="<%=user.getPreference().getPassengersCount()%>"
                   style="color:black;padding-left:5px" value="1" step="1">
            <label for="passengerCount"> Passenger Count </label><br>
            <input type="checkbox" name="wantsAlone" id="wantsAlone"
                   value="<%if(user.getPreference().isWantsAlone()){%>checked<%}%>"
                   name="wantsAlone">
            <label for="wantsAlone"> Want To Travel Alone </label>
            </input>
            <br>
        </form>
        <button id="savePref" style="margin-top:5px" class="button special"> Save</button>
    </div>
    <div class="12u 1u$(small)" style="margin-top:10px;float:left">
        <a href="#" onclick="$('#grid').toggleClass('hidden');" class="button special small fa fa-bar-chart">
            View Order History</a>
    </div>
    <div id="grid" class="hidden" style="color:black;width: 100%;padding:0px; height: 250px;"></div>
</div>
</div>
</div>