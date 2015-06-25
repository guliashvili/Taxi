<%@ page import="ge.taxistgela.bean.Driver" %>
\<%--
  Created by IntelliJ IDEA.
  User: Ratmach
  Date: 19.06.2015
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="Resources/assets/js/driver.js"/>
<% Driver driver = (Driver) session.getAttribute(Driver.class.getName());%>
<section id="map" style="position:absolute;width:100%;height:100%;">

</section>
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
            <button class="fa fa-check-circle button small <% if(driver.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>">
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
            <button class="fa fa-check-circle button small <% if(driver.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>">
                <% if (driver.getIsVerifiedPhone()) {
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
                <span> New Password: </span>
                <input name="password" type="password" value="" required/>
                <span> Repeat Password: </span>
                <input type="password" value=""/><br>
                <button id="passChange" class="special button">Save</button>
            </form>
        </div>
        <div style="float:right" class="4u$ (xsmall)">
            <a href="#" style="float:left"
               class="<%if(driver.getGoogleID()!=null){ %> disabled <%}%> icon fa-facebook"><span
                    class="label">getGoogleID</span></a>
            <br><br>
            <a href="#" style="float:left" class="<%if(driver.getFacebookID()!=null){ %> disabled <%}%> icon fa-facebook"><span
                    class="label">Facebook</span></a>
        </div>
        <div class="5u$ 12u$(small)">
            <a href="#" onclick="$('#cP').toggleClass('hidden');" class="button special small fa fa-adjust"> Register To
                Company</a>
        </div>
        <div id="cP" class="5u$ 12u$(small) hidden">
            <form id="companyCodeForm" action="" type="post">
                <input name="companyCode" type="text" placeholder="Company Code"/><br>
                <button id="companyCodeBtn" style="float:right;" class="button special">Register</button>
            </form>
        </div>
        <div class="5u$ 12u$(small)">
            <a href="#" data-toggle="modal" data-target="#preferenceModal" class="button special small fa fa-adjust">
                Edit Your Preferences</a>
        </div>
        <div class="12u$(small)">
            <a href="#" data-toggle="modal" data-target="#preferenceModal" class="button special small fa fa-adjust">
                Edit Your Car Details</a>
        </div>
        <div class="12u 1u$(small)" style="float:left">
            <a href="#" data-toggle="modal" data-target="#historyModal" class="button special small fa fa-bar-chart">
                View Order History</a>
        </div>
    </div>
</div>