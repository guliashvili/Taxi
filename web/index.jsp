<%--
  Created by IntelliJ IDEA.
  ge.taxistgela.bean.User: Alex
  Date: 5/23/2015
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<!--
Spectral by HTML5 UP
html5up.net | @n33co
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
  <title>Taxist Gela</title>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <!--[if lte IE 8]><script src="Resources/assets/js/ie/html5shiv.js"></script><![endif]-->
  <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <link rel="stylesheet" href="Resources/assets/css/taxi.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"> </script>
  <link rel="stylesheet" href="Resources/assets/css/main.css" />
  <script src="Resources/assets/js/jquery.min.js"></script>
  <script src="Resources/assets/js/jquery.scrollex.min.js"></script>
  <script src="Resources/assets/js/jquery.scrolly.min.js"></script>
  <script src="Resources/assets/js/skel.min.js"></script>
  <script src="Resources/assets/js/util.js"></script>
  <!--[if lte IE 8]><script src="Resources/assets/js/ie/respond.min.js"></script><![endif]-->
  <script src="Resources/assets/js/main.js"></script>
  <script src="Resources/assets/js/index.js"></script>

  <!--[if lte IE 8]><link rel="stylesheet" href="Resources/assets/css/ie8.css" /><![endif]-->
  <!--[if lte IE 9]><link rel="stylesheet" href="Resources/assets/css/ie9.css" /><![endif]-->
</head>
<style>

</style>
<body class="landing">

<div id="page-wrapper">

  <header id="header" class="alt">
    <h1><a href="index.html">Taxist Gela</a></h1>
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

  <section id="banner">
    <div class="inner">
      <h2 id="mainHeader">Taxist Gela</h2>
      <p>Some Very Inspiring<br />
        Text goes<br />
        Here
      <ul class="actions">
        <li><a href="#" class="button special" data-toggle="modal" data-target="#loginModal">Sign in</a></li>
        <li><a href="#" class="button special" data-toggle="modal" data-target="#registerModal">Register</a></li>
      </ul>
    </div>
    <a href="#about" class="more scrolly one">About</a>
  </section>
  <section id="panel" class="hidden">
    <!-- Ajax request goes here -->
  </section>
  <section id="about" class="wrapper style1 special">
    <div class="inner">
      <header class="major">
        <h2>Taxist gela is an awesome free service connecting you to<br />
          your desired destination</h2>
        <p>Our flexible preference service helps you find the taxi driver you need<br />
          with lowest price, best rating and not waste a single moment. <br>
          <br>
          <img src="Resources/images/gela.jpg"/>
        <h2>Your Service is:</h2></p>
      </header>
      <ul class="icons major">
        <li><span class="icon fa-dollar style1"><span class="label">Cheap</span></span></li>
        <li><span class="icon fa-clock-o style2"><span class="label">Fast</span></span></li>
        <li><span class="icon fa-comment style3"><span class="label">Interractive</span></span></li>
      </ul>
    </div>
  </section>
  <footer id="footer">
    <ul class="icons">
      <li><a href="#" class="icon fa-twitter"><span class="label">Follow us</span></a></li>
      <li><a href="#" class="icon fa-facebook"><span class="label">Like Us</span></a></li>
      <li><a href="#" class="icon fa-envelope-o"><span class="label">Email Us</span></a></li>
    </ul>
    <ul class="copyright">
      <li>&copy; ღმერთჩემა & co.</li>
    </ul>
  </footer>

</div>

<div id="registerModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5 class="modal-title">Registration</h5>
      </div>
      <div class="modal-body">
        <form id="registrationForm">
          <div class = "4u">
            <input type="radio" class="regChange" showf="userRegistration" id="userReg" name="userReg">
            <label for="userReg">User</label>
          </div>
          <div class = "4u">
            <input type="radio" class="regChange" showf="driverRegistration" id="driverReg" name="userReg">
            <label for="driverReg">Driver</label>
          </div>
          <div class = "4u">
            <input type="radio" class="regChange" showf="companyRegistration" id="companyReg" name="userReg">
            <label for="companyReg">Company</label>
          </div>
          <br><br>
          <div id="companyRegistration" class="hidden" >
            <input type ="text" name="companyCode" value="" placeholder="Company Code" required/><br>
            <input type ="email" name="email" value="" placeholder="Company Email" required/><br>
            <input type ="password" name="password" value="" placeholder="Company Password" required/><br>
            <input type ="password" name="password" value="" placeholder="Repeat Password" required/><br>
            <input type ="text" name="companyName" value="" placeholder="Company Name" required/><br>
            <input type ="text" name="phoneNumber" value="" placeholder="Company Phone Number" required/><br>
            <a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a>
            <a href="#" class="icon fa-google"><span class="label">Google+</span></a><br><br>
            <input type="submit" class="button special" value="Register"/>
          </div>
          <div id="userRegistration" class="hidden">
            <input type ="text" name="firstName" value="" placeholder="First Name" required/><br>
            <input type ="text" name="lastName" value="" placeholder="Last Name" required/><br>
            <input type ="email" name="email" value="" placeholder="Email" required/><br>
            <input type ="password" name="password" value="" placeholder="Password" required/><br>
            <input type ="password" name="password" value="" placeholder="Repeat Password" required/><br>
            <select name="gender">
              <option value="MALE">Male</option>
              <option value="FEMALE">Female</option>
            </select><br>
            <input type ="text" name="phoneNumber" value="" placeholder="Phone Number" required/><br>
            <a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a>
            <a href="#" class="icon fa-google"><span class="label">Google+</span></a><br><br>
            <input type="submit" class="button special" value="Register"/>
          </div>
          <div id="driverRegistration" class="hidden">
            <input type ="text" name="firstName" value="" placeholder="First Name" required/><br>
            <input type ="text" name="lastName" value="" placeholder="Last Name" required/><br>
            <input type ="text" name="personalID" value="" placeholder="Personal ID" required/><br>
            <input type ="text" name="companyID" value="" placeholder="Company ID"/><br>
            <input type ="email" name="email" value="" placeholder="Email" required/><br>
            <input type ="password" name="password" value="" placeholder="Password" required/><br>
            <input type ="password" name="password" value="" placeholder="Repeat Password" required/><br>
            <select name="gender">
              <option value="MALE">Male</option>
              <option value="FEMALE">Female</option>
            </select><br>
            <input type ="text" name="phoneNumber" value="" placeholder="Phone Number" required/><br>
            <a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a>
            <a href="#" class="icon fa-google"><span class="label">Google+</span></a><br><br>
            <input type="submit" class="button special" value="Register"/>
          </div>
        </form>
      </div>
      <div class="modal-footer">
      </div>
    </div>

  </div>
</div>

<!-- Login Modal -->
<div id="loginModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5 class="modal-title">Login</h5>
      </div>
      <div class="modal-body">
        <form id="loginForm">
          <div class="4u">
            <input type="radio" class="loginChange" id="userLogin" showf="1" name="loginC">
            <label for="userLogin">User</label>
          </div>
          <div class="4u">
            <input type="radio" class="loginChange" id="driverLogin" showf="2" name="loginC">
            <label for="driverLogin">Driver</label>
          </div>
          <div class="4u">
            <input type="radio" class="loginChange" id="companyLogin" showf="3" name="loginC">
            <label for="companyLogin">Company</label>
          </div>
          <br><br>

          <div class="innerContainer">
            <input type="text" name="username" id="usernameLogin" value="" placeholder="Username"><br>
            <input type="password" name="password" id="passwordLogin" value=""
                   placeholder="Password"><br>
            <button class="btn btn-success btn-sm" style="float:right">Log In</button>
            <br><br>
          </div>
        </form>
      </div>
      <div class="modal-footer">
      </div>
    </div>

  </div>
</div>

</body>
</html>