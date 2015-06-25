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
<section id="map" style="position:absolute;width:100%;height:100%;">

</section>
<div class="prefPanel">
  <div class="row uniform" style="margin-left:10px">
    <br>
    <h2> Greetings Rati <br> </h2>
    <div class="5u$">
      <a href="#"><h3>Your Current Rating: 4.3</h3></a>
      <li class="fa fa-star"></li>
      <li class="fa fa-star"></li>
      <li class="fa fa-star"></li>
      <li class="fa fa-star-half-o"></li>
      <li class="fa fa-star-o"></li>
    </div>
    <div class="6u 12u$(xsmall)">
      <input type="email" disabled value="rmach13@freeuni.edu.ge">
    </div>
    <div class="6u$ 12u$(xsmall)">
      <button class="fa fa-check-circle button small disabled verified">Verified</button>
    </div>
    <div class="6u 12u$(xsmall)">
      <input type="text" disabled value="+995558677895">
    </div>
    <div class="6u$ 12u$(xsmall)">
      <button class="fa fa-check-circle button small special">Verify</button>
    </div>
    <div class="12u$">
      <button onclick="$('#cPass').toggleClass('hidden');" class="button special fa fa-key"> Change Password </button>
    </div>
    <div id="cPass" class="5u$ hidden">
      <form action="" type="post">
        <span> New Password: </span>
        <input name="password" type="password" value="" required/>
        <span> Repeat Password: </span>
        <input type="password" value="" /><br>
        <input type="submit" class="special button" value="Save"/>
      </form>
    </div>
    <div style="float:right" class="4u$ (xsmall)">
      <li style="float:left" class="disabled icon fa-google"><span class="label">Google+</span></li>
      <br><br>
      <a href="#" style="float:left" class="icon fa-facebook"><span class="label">Facebook</span></a>
    </div>
    <div class="5u$ 12u$(small)">
      <a href="#" onclick="$('#cP').toggleClass('hidden');"class="button special small fa fa-adjust"> Register To Company</a>
    </div>
    <div id="cP" class="5u$ 12u$(small) hidden">
      <form action="" type="post">
        <input type="text" placeholder="Company Code" /><br>
        <input type="submit" style="float:right;"class="button special" value="register"/>
      </form>
    </div>
    <div class="5u$ 12u$(small)">
      <a href="#" data-toggle="modal" data-target="#preferenceModal" class="button special small fa fa-adjust"> Edit Your Preferences</a>
    </div>
    <div class="12u$(small)">
      <a href="#" data-toggle="modal" data-target="#preferenceModal" class="button special small fa fa-adjust"> Edit Your Car Details</a>
    </div>
    <div class="12u 1u$(small)" style="float:left">
      <a href="#" data-toggle="modal" data-target="#historyModal" class="button special small fa fa-bar-chart"> View Order History</a>
    </div>
  </div>
</div>
<div id="preferenceModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5 class="modal-title">Preferences</h5>
      </div>
      <div class="modal-body">
        <!-- history goes here -->
      </div>
      <div class="modal-footer">
      </div>
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