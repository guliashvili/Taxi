<%@ page import="ge.taxistgela.bean.Company" %>
<%--
  Created by IntelliJ IDEA.
  User: Ratmach
  Date: 19.06.2015
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="Resources/assets/js/company.js"/>
<% Company company = (Company) session.getAttribute(Company.class.getName());%>
<section id="map" style="width:100%;height:100%;">

</section>
<div class="prefPanel">
  <div class="row uniform" style="margin-left:10px">
    <br>
    <h2> Greetings <%= company.getCompanyName()%> <br></h2>
    <div class="5u$">
      <a href="#"><h3>Your Average Driver Rating: 4.3</h3></a>
      <li class="fa fa-star"></li>
      <li class="fa fa-star"></li>
      <li class="fa fa-star"></li>
      <li class="fa fa-star-half-o"></li>
      <li class="fa fa-star-o"></li>
    </div>
    <div class="3u$ (small)">
      <span> Your Company Code </span> <a href="#" data-target="#helperModal" data-toggle="modal" class="fa fa-question"></a>
      <input type="text" disabled value="<%=company.getCompanyCode()%>">
    </div>
    <div class="6u 12u$(xsmall)">
      <input type="email" disabled value="<%=company.getEmail()%>">
    </div>
    <div class="6u$ 12u$(xsmall)">
      <button class="fa fa-check-circle button small <% if(company.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>">
        <% if(company.getIsVerifiedEmail()){out.println("Verified");}else{out.println("Verify");} %>
      </button>
    </div>
    <div class="6u 12u$(xsmall)">
      <input type="text" disabled value="<%=company.getPhoneNumber()%>">
    </div>
    <div class="6u$ 12u$(xsmall)">
      <button class="fa fa-check-circle button small <% if(company.getIsVerifiedPhone()){out.println("disabled verified");}else{out.println("special");}%>">
        <% if(company.getIsVerifiedPhone()){out.println("Verified");}else{out.println("Verify");} %>
      </button>
    </div>
    <div class="12u$">
      <button onclick="$('#cPass').toggleClass('hidden');" class="button special fa fa-key"> Change Password </button>
    </div>
    <div id="cPass" class="5u$ hidden">
      <form id="passForm" action="" type="post">
        <span> New Password: </span>
        <input name="password" type="password" value="" required/>
        <span> Repeat Password: </span>
        <input type="password" value="" required/><br>
        <button id="passChange" class="special button">Save</button>
      </form>
    </div>
    <div style="float:right" class="4u$ (xsmall)">
      <a href="#" style="float:left" class="<%if(company.getGoogleID()!=null){ %> disabled <%}%> icon fa-facebook"><span
              class="label">getGoogleID</span></a>
      <br><br>
      <a href="#" style="float:left" class="<%if(company.getFacebookID()!=null){ %> disabled <%}%> icon fa-facebook"><span
              class="label">Facebook</span></a>
    </div>
    <div class="12u 1u$(small)" style="float:left">
      <a href="#" data-toggle="modal" data-target="#historyModal" class="button special small fa fa-bar-chart"> View Order History</a>
    </div>
    <div class="12u 1u$(small)" style="float:left">
      <a href="#" data-toggle="modal" data-target="#driversModal" class="button special small fa fa-bar-chart"> View Drivers</a>
    </div>
    <div class="12u 1u$(small)" style="float:left">
      <a href="#" data-toggle="modal" data-target="#statisticsModal" class="button special small fa fa-bar-chart"> View Statistics</a>
    </div>
  </div>
</div>
<div id="historyModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5 class="modal-title">History</h5>
      </div>
      <div class="modal-body">
        <!-- history goes here -->
      </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>
<div id="driversModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5 class="modal-title">Drivers</h5>
      </div>
      <div class="modal-body">
        <table id="driversTable" class="display" cellspacing="0" width="100%">
          <thead>
          <tr>
            <th>Name</th>
            <th>Last name</th>
            <th>Personal ID</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Location</th>
            <th>Rating</th>
            <th>isActive</th>
            <th>isVerified</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td data-search="გელა">გელა</td>
            <td data-search="მაღალთაძე">მაღალთაძე</td>
            <td data-search="01010101011">01010101011</td>
            <td data-search="558555569">558555569</td>
            <td data-search="gmagh13@freeuni.edu.ge">gmagh13@freeuni.edu.ge</td>
            <td data-search="Male">Male</td>
            <td data-search="unknown">unknown</td>
            <td data-search="3.1">3.1</td>
            <td data-search="+">+</td>
            <td data-search="+">+</td>
          </tr>

          </tbody>
        </table>
      </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>
<div id="statisticsModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5 class="modal-title">Statistics</h5>
      </div>
      <div class="modal-body">
        <!-- statistics goes here -->
      </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>
<div id="helperModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
        "Company Code" is unique code given to company account<br>
        via this code Drivers can be registered under given company<br>
        in order for you to see them in total statistics
      </div>
    </div>
  </div>
</div>