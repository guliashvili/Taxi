<%@ page import="ge.taxistgela.bean.Company" %>
<%@ page import="ge.taxistgela.model.CompanyManagerAPI" %>
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
<script src="/Resources/assets/js/company.js"></script>
<% Company company = (Company) session.getAttribute(Company.class.getName());%>
<script>
    ga('send', 'pageview', {
        'page': '/company.jsp',
        'title': 'Company Panel'
    });
    clickChoice = "add";
</script>
<section id="map" style="position:absolute;width:100%;height:100%;">

</section>
<div class="prefPanel">
    <div class="row uniform" style="margin-left:10px">
        <br>

        <h2> Greetings <%= company.getCompanyName()%> <br></h2>

        <div class="5u$">
            <a href="#"><h3>Your Average Driver
                Rating: <%
                    ServletContext sc = request.getServletContext();
                    CompanyManagerAPI man = (CompanyManagerAPI) sc.getAttribute(CompanyManagerAPI.class.getName());
                    Double Rating = man.getCompanyScore(company.getCompanyID());%>
            </h3></a>
            <% for (int i = 1; i <= 5; ++i) { %>
            <% if (Rating >= i) { %>
            <li class="fa fa-star"></li>
            <% } else { %>
            <% if (Math.ceil(Rating) > i) { %>
            <li class="fa fa-star-half-o"></li>
            <% } else { %>
            <li class="fa fa-star-o"></li>
            <%} %>
            <%} %>
            <%} %>
        </div>
        <div class="3u$ (small)">
            <span> Your Company Code </span> <a href="#" data-target="#helperModal" data-toggle="modal"
                                                class="fa fa-question"></a>
            <input type="text" disabled value="<%=company.getCompanyCode()%>">
        </div>
        <div class="6u 12u$(xsmall)">
            <input type="email" disabled value="<%=company.getEmail()%>">
        </div>
        <div class="6u$ 12u$(xsmall)">
            <button class="fa fa-check-circle button small <% if(company.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>" <%
                if (!company.getIsVerifiedEmail()) {
                    out.println(" onclick='resendEmail()'");
                }
            %>>
                <% if (company.getIsVerifiedEmail()) {
                    out.println("Verified");
                } else {
                    out.println("Verify");
                } %>
            </button>
        </div>
        <div class="6u 12u$(xsmall)">
            <input type="text" disabled value="<%=company.getPhoneNumber()%>">
        </div>
        <div class="6u$ 12u$(xsmall)">
            <button class="fa fa-check-circle button small <% if(company.getIsVerifiedPhone()){out.println("disabled verified");}else{out.println("special");}%>" <%
                if (!company.getIsVerifiedPhone()) {
                    out.println(" onclick='resendPhone()'");
                }
            %>>
                <% if (company.getIsVerifiedPhone()) {
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
                <input type="text" name="action" value="cPassword" class="hidden"/>
                <span> Repeat Password: </span>
                <input type="password" value=""/><br>
            </form>
            <button id="passChange" class="special button">Save</button>
        </div>
        <div style="float:right" class="4u$ (xsmall)">
            <a href="#" style="float:left"
               class="<%if(company.getGoogleID()==null){ %> disabled <%}%> icon fa-google-plus"><span
                    class="label">Google+</span></a>
            <% if (company.getGoogleID() != null) { %>  Account Attached <% }%>
            <br><br>
            <%if (company.getGoogleID() == null) { %>
            <div class="g-signin2" data-onsuccess="onSignIn"></div>
            <%}%>
            <br><br>
            <a href="#" style="float:left"
               class="<%if(company.getFacebookID()==null){ %> disabled <%}%> icon fa-facebook"><span
                    class="label">Facebook</span></a>
            <% if (company.getFacebookID() != null) { %>  Account Attached <% }%>
            <br><br>
            <% if (company.getFacebookID() == null) { %> <fb:login-button scope="public_profile,email"
                                                                          onlogin="checkLoginState();"></fb:login-button> <% } %>
            <br><br>
        </div>
        <div id="gridHistory" style="color:black;width: 100%;padding:0px; height: 250px;">

        </div>

        <div id="gridDrivers" style="color:black;width: 100%;padding:0px; height: 250px;">

        </div>

        <div id="gridReview" style="color:black;width: 100%;padding:0px; height: 250px;">

        </div>
    </div>
</div>