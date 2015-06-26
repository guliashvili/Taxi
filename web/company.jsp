<%@ page import="ge.taxistgela.bean.Company" %>
<%@ page import="ge.taxistgela.model.OrderManager" %>
<%@ page import="ge.taxistgela.bean.Order" %>
<%@ page import="java.util.List" %>
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
        <span> Current Password: </span>
        <input name="oldPassword" type="password" value=""/>
        <span> New Password: </span>
        <input name="password" type="password" value=""/>
        <span> Repeat Password: </span>
        <input type="password" value=""/><br>
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
      <a href="#" onclick="$('#history').toggleClass('hidden');" class="button special small fa fa-bar-chart">
        View Order History</a>
    </div>
    <div id="history" class="12 1u$ hidden" >
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
<script>
  function generateGrid(){
    $('#grid').w2grid({
      name: 'grid',
      header: 'List of Names',
      columns: [
        { field: 'Date', caption: 'Date', size: '30%' },
        { field: 'Date', caption: 'DateEnded', size: '30%' },
        { field: 'callTime', caption: 'Call Time', size: '30%' },
        { field: 'User', caption: 'Date', size: '30px' },
        { field: 'Driver', caption: 'Driver', size: '30px' },
        { field: 'StLoc', caption: 'Start', size: '30px' },
        { field: 'EndLoc', caption: 'End', size: '30px' },
        { field: 'paymentAmount', caption: 'Amount', size: '30px' }
      ],
      records: [
          <% OrderManager man = (OrderManager) application.getAttribute(OrderManager.class.getName());
              if (man != null) {
                  List<Order> orders = man.getorder
                  for (Order ord : orders) {
                      %>{ Date: <%=ord.getStartTime()%>, callTime: <%=ord.getCallTime()%>, Driver: <%=ord.getDriverID()%>, StLoc: <%=ord.getStartLocation()%>, EndLoc: <%=ord.getEndLocation()%>,paymentAmount:<%=ord.getPaymentAmount()%> },<%
                    }
                }
            %>
      ]
    });
  }
</script>