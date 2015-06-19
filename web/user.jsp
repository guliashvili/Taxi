<%@ page import="ge.taxistgela.bean.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Ratmach
  Date: 19.06.2015
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Taxist Gela</title>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <!--[if lte IE 8]><script src="/Resources/assets/js/ie/html5shiv.js"></script><![endif]-->
  <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <link rel="stylesheet" href="/Resources/assets/css/taxi.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"> </script>
  <link rel="stylesheet" href="/Resources/assets/css/main.css" />
  <script src="/Resources/assets/js/jquery.min.js"></script>
  <script src="/Resources/assets/js/jquery.scrollex.min.js"></script>
  <script src="/Resources/assets/js/jquery.scrolly.min.js"></script>
  <script src="/Resources/assets/js/skel.min.js"></script>
  <script src="/Resources/assets/js/util.js"></script>
  <!--[if lte IE 8]><script src="/Resources/assets/js/ie/respond.min.js"></script><![endif]-->
  <script src="/Resources/assets/js/main.js"></script>

  <!--[if lte IE 8]><link rel="stylesheet" href="/Resources/assets/css/ie8.css" /><![endif]-->
  <!--[if lte IE 9]><link rel="stylesheet" href="/Resources/assets/css/ie9.css" /><![endif]-->
  <script type="text/javascript"
          src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDYaoDG8Mj-4FtjBn1p18va0taQyb0KwBk">
  </script>
  <script src="/Resources/assets/js/map.js"></script>
  <script src="/Resources/assets/js/user.js"></script>
</head>
<body class="landing">
<header id="header" class="alt">
  <h1 class="headCaption"><a href="index.html">Taxist Gela</a></h1>
  <nav id="nav">
    <ul>
      <li class="special">
        <a href="#menu" class="menuToggle"><span>Menu</span></a>
        <div id="menu">
          <ul>
            <li><a href="index.html">Home</a></li>
            <li><a href="generic.html">Generic</a></li>
            <li><a href="elements.html">Elements</a></li>
            <li><a href="#" data-toggle="modal" data-target="#myModal">Sign Up</a></li>
            <li><a href="#">Log In</a></li>
          </ul>
        </div>
      </li>
    </ul>
  </nav>
</header>
<section id="map" style="width:100%;height:100%;">

</section>
<% User user = (User) session.getAttribute(User.class.getName());%>
<div class="prefPanel">
  <div class="row uniform" style="margin-left:10px">
    <br>
    <h2> Greetings <%= user.getFirstName()%> <br> </h2>
    <div class="5u$">
      <a href="#"><h3>Your Current Rating: <%= user.getRating()%></h3></a>
      <% for(int i=1;i<=5;++i){ %>
        <% if(user.getRating()>i){ %>
          <li class="fa fa-star"></li>
        <% }else{ %>
          <% if(Math.ceil(user.getRating())>i){ %>
            <li class="fa fa-star-half-o"></li>
          <% }else{ %>
            <li class="fa fa-star-o"></li>
          <%} %>
        <%} %>
      <%} %>
    </div>
    <div class="12u$ (small) fit" style="float:left">
      <a href="#" onclick = "$('.prefPanel').toggleClass('zero');$('#map').toggleClass('mOP');" class="button special small fa fa-bar-chart"> Add Order</a>
    </div><br><br><br><br>
    <div class="6u 12u$(xsmall)">
      <input type="email" disabled value="rmach13@freeuni.edu.ge">
    </div>
    <div class="6u$ 12u$(xsmall)">
      <button class="fa fa-check-circle button small <% if(user.isVerified()){out.println("disalbed");}%> verified">Verified</button>
    </div>
    <div class="6u 12u$(xsmall)">
      <input type="text" disabled value="+995558677895">
    </div>
    <div class="6u$ 12u$(xsmall)">
      <button class="fa fa-check-circle button small <% if(user.isVerified()){out.println("disalbed");}%> special">Verify</button>
    </div>
    <div class="12u$">
      <button onclick="$('#cPass').toggleClass('hidden');" class="button special fa fa-key"> Change Password </button>
    </div>
    <div id="cPass" class="5u$ hidden">
      <form action="" type="post">
        <span> Current Password: </span>
        <input name="oldPassword" type="password" value="" required/>
        <span> New Password: </span>
        <input name="password" type="password" value="" required/>
        <span> Repeat Password: </span>
        <input type="password" value="" required/><br>
        <input type="submit" class="special button" value="Save"/>
      </form>
    </div>
    <div style="float:right" class="4u$ (xsmall)">
      <li style="float:left" class="disabled icon fa-google"><span class="label">Google+</span></li>
      <br><br>
      <a href="#" style="float:left" class="icon fa-facebook"><span class="label">Facebook</span></a>
    </div>
    <div class="5u$ 12u$(small)">
      <a href="#" onclick="$('#cPref').toggleClass('hidden');" class="button special small fa fa-adjust"> Edit Your Preferences</a>
    </div>
    <div id="cPref" class="6u$ hidden">
      <form action="" type="post">
        <span> Minimum Driver Rating </span>
        <i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i>
        <input type="checkbox" id="conditioning" name="conditioning" checked>
        <label for="conditioning"> Conditioning Required </label>
        </input><br>
        <input type="number" id="carYear" style="color:black;padding-left:5px" value="1990" step="1">
        <label for="carYear"> Minimum Car Year </label>
        <input type="number" id="timeLimit" style="color:black;padding-left:5px" value="10" step="1">
        <label for="timeLimit"> Maximum Time Limit (Minutes) </label>
        <input type="number" id="passengerCount" style="color:black;padding-left:5px" value="1" step="1">
        <label for="passengerCount"> Passenger Count </label><br>
        <input type="checkbox" id="wantsAlone" name="wantsAlone" checked>
        <label for="wantsAlone"> Want To Travel Alone </label>
        </input>
        <input type="submit" style="margin-top:5px" class="button special"><br>
      </form>
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
</body>
</html>