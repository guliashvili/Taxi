<%@ page import="ge.taxistgela.bean.Company" %>
<%@ page import="ge.taxistgela.socialNetwork.SNInfo" %>
<%@ page import="ge.taxistgela.dao.CompanyDao" %>
<%@ page import="ge.taxistgela.model.CompanyManager" %>
<%--
  Created by IntelliJ IDEA.
  User: Ratmach
  Date: 19.06.2015
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/Resources/assets/js/addFacebookAccount.js"></script>
<script src="/Resources/assets/js/addGoogleplusAccount.js"></script>
<script src="Resources/assets/js/company.js"></script>
<% Company company = (Company) session.getAttribute(Company.class.getName());%>
<section id="map" style="position:absolute;width:100%;height:100%;">

</section>
<div class="prefPanel">
    <div class="row uniform" style="margin-left:10px">
        <br>

        <h2> Greetings <%= company.getCompanyName()%> <br></h2>

        <div class="5u$">
            <a href="#"><h3>Your Average Driver Rating: <% CompanyManager man  =(CompanyManager) request.getServletContext().getAttribute(CompanyManager.class.getName());
            double Rating= man.getCompanyScore(company.getCompanyID());%>
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
            <button class="fa fa-check-circle button small <% if(company.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>" <%if(!company.getIsVerifiedEmail()){out.println(" onclick='resendEmail()'");}%>>
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
            <button class="fa fa-check-circle button small <% if(company.getIsVerifiedPhone()){out.println("disabled verified");}else{out.println("special");}%>" <%if(!company.getIsVerifiedPhone()){out.println(" onclick='resendPhone()'");}%>>
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
            <br><br>
            <a href="#" style="float:left"
               class="<%if(company.getFacebookID()==null){ %> disabled <%}%> icon fa-facebook"><span
                    class="label">Facebook</span></a>
        </div>
        <br><br>
        <% if (company.getFacebookID() == null) { %> <fb:login-button scope="public_profile,email"
                                                                      onlogin="checkLoginState();"></fb:login-button> <% } %>
        <br><br>
        <%if (company.getGoogleID() == null) { %> <div class="gg_button">
                                                            <span
                                                                    class="g-signin"
                                                                    data-height="short"
                                                                    data-callback="onLogin"
                                                                    data-clientid=<%=SNInfo.googleClientID%>
                                                                            data-cookiepolicy="single_host_origin"
                                                                    data-requestvisibleactions="http://schemas.google.com/AddActivity"
                                                                    data-scope="https://www.googleapis.com/auth/plus.login">
                                                            </span>
        </div> <%}%>

        <div class="12u 1u$(small)" style="float:left">
            <a href="#" onclick="$('#history').toggleClass('hidden');" class="button special small fa fa-bar-chart">
                View Order History</a>
        </div>
        <div id="history" class="12 1u$ hidden">
            <div id="grid">

            </div>
        </div>
        <div class="12u 1u$(small)" style="float:left">
            <a href="#" class="button special small fa fa-bar-chart"> View Drivers</a>
        </div>
        <div class="12u 1u$(small)" style="float:left">
            <a href="#" class="button special small fa fa-bar-chart"> View Statistics</a>
        </div>
    </div>
</div>