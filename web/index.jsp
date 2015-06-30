<%@ page import="ge.taxistgela.bean.Company" %>
<%@ page import="ge.taxistgela.bean.Driver" %>
<%@ page import="ge.taxistgela.bean.User" %>
<%@ page import="ge.taxistgela.socialNetwork.SNInfo" %>
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
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="google-signin-client_id" content=<%=SNInfo.googleClientID%>>
    <!--[if lte IE 8]>
    <script src="Resources/assets/js/ie/html5shiv.js"></script><![endif]-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="http://w2ui.com/src/w2ui-1.4.2.min.css"/>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="/Resources/assets/js/facebookLogin.js"></script>
    <script src="/Resources/assets/js/facebookRegister.js"></script>
    <script src="/Resources/assets/js/googleplusRegister.js"></script>
    <script src="/Resources/assets/js/googleplusLogin.js"></script>
    <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css"
          rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="Resources/assets/css/taxi.css">
    <link rel="stylesheet" href="Resources/assets/css/main.css"/>
    <script src="Resources/assets/js/jquery.scrollex.min.js"></script>
    <script src="Resources/assets/js/jquery.scrolly.min.js"></script>
    <script src="Resources/assets/js/skel.min.js"></script>
    <script src="Resources/assets/js/util.js"></script>
    <!--[if lte IE 8]>
    <script src="Resources/assets/js/ie/respond.min.js"></script><![endif]-->
    <script src="Resources/assets/js/main.js"></script>
    <script src="Resources/assets/js/login.js"></script>
    <script src="Resources/assets/js/register.js"></script>
    <script src="Resources/assets/js/index.js"></script>
    <script type="text/javascript" src="http://w2ui.com/src/w2ui-1.4.2.min.js"></script>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="Resources/assets/css/ie8.css"/><![endif]-->
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="Resources/assets/css/ie9.css"/><![endif]-->
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDYaoDG8Mj-4FtjBn1p18va0taQyb0KwBk">
    </script>
    <script src="Resources/assets/js/map.js"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
    <script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
</head>
<body class="landing">

<div id="page-wrapper">
    <jsp:include page="Resources/header.html"/>
    <%
        User user = (User) session.getAttribute(User.class.getName());
        Company company = (Company) session.getAttribute(Company.class.getName());
        Driver driver = (Driver) session.getAttribute(Driver.class.getName());
        if (user == null && company == null && driver == null) {
    %>
    <section id="banner" class="containerCard">
        <div id="card" class="inner">
            <figure class="front">
                <h2 id="mainHeader">Taxist Gela</h2>

                Taxi is subject that needs passing<br/>
                    At least<br/>
                    Twice
                <ul class="actions">
                    <li><a href="#" class="button special" data-toggle="modal" data-target="#loginModal">Sign in</a>
                    </li>
                    <li><a href="#" class="button special" data-toggle="modal" data-target="#registerModal">Register</a>
                    </li>
                </ul>
            </figure>
            <figure class="back">
                <header class="major">
                    <h2>Taxist gela is an awesome free service connecting you to<br/>
                        your desired destination</h2>

                    <p>Our flexible preference service helps you find the taxi driver you need<br/>
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
            </figure>
        </div>
        <a onclick="$('#card').toggleClass('flipped')" class="more">flip</a>
    </section>
    <section id="panel">
        <!-- Ajax request goes here -->
    </section>
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
                        <div class="4u">
                            <input type="radio" class="regChange" showf="userRegistration" id="userReg" name="action"
                                   value="registerUser">
                            <label for="userReg">User</label>
                        </div>
                        <div class="4u">
                            <input type="radio" class="regChange" showf="driverRegistration" id="driverReg"
                                   name="action"
                                   value="registerDriver">
                            <label for="driverReg">Driver</label>
                        </div>
                        <div class="4u">
                            <input type="radio" class="regChange" showf="companyRegistration" id="companyReg"
                                   name="action"
                                   value="registerCompany">
                            <label for="companyReg">Company</label>
                        </div>
                        <br><br>

                        <div id="companyRegistration" class="hidden">
                            <input id="companyCode" type="text" name="companyCode" value="" placeholder="Company Code"/><br>
                            <input id="companyemail" type="email" name="companyemail" value=""
                                   placeholder="Company Email"/><br>
                            <input id="companyfacebookId" type="hidden" name="companyfacebookId" value=""
                                   placeholder="Facebook Id"/>
                            <input id="companygoogleplusId" type="hidden" name="companygoogleplusId" value=""
                                   placeholder="Google Plus Id"/>
                            <input id="companypassword" type="password" name="companypassword" value=""
                                   placeholder="Company Password"/><br>
                            <input id="companyrepassword" type="password" name="companyrepassword" value=""
                                   placeholder="Repeat Password"/><br>
                            <input id="companyName" type="text" name="companyName" value="" placeholder="Company Name"/><br>
                            <input id="companyphoneNumber" type="text" name="companyphoneNumber" value=""
                                   placeholder="Company Phone Number"/><br>
                            <fb:login-button scope="public_profile,email"
                                             onlogin="checkRegisterState();"></fb:login-button>
                            <br>

                            <div class="gg_button">
                                    <span
                                            class="g-signin"
                                            data-height="short"
                                            data-callback="onRegister"
                                            data-clientid=<%=SNInfo.googleClientID%>
                                                    data-cookiepolicy="single_host_origin"
                                            data-requestvisibleactions="http://schemas.google.com/AddActivity"
                                            data-scope="https://www.googleapis.com/auth/plus.login">
                                    </span>
                            </div>
                        </div>
                        <div id="userRegistration" class="hidden">
                            <input id="userfirstName" type="text" name="userfirstName" value=""
                                   placeholder="First Name"/><br>
                            <input id="userfacebookId" type="hidden" name="userfacebookId" value=""
                                   placeholder="Facebook Id"/>
                            <input id="usergoogleplusId" type="hidden" name="usergoogleplusId" value=""
                                   placeholder="Google Plus Id"/>
                            <input id="userlastName" type="text" name="userlastName" value=""
                                   placeholder="Last Name"/><br>
                            <input id="useremail" type="email" name="useremail" value="" placeholder="Email"/><br>
                            <input id="userpassword" type="password" name="userpassword" value=""
                                   placeholder="Password"/><br>
                            <input id="userrepassword" type="password" name="userrepassword" value=""
                                   placeholder="Repeat Password"/><br>
                            <select id="usergender" name="usergender">
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select><br>
                            <input id="userphoneNumber" type="text" name="userphoneNumber" value=""
                                   placeholder="Phone Number"/><br>
                            <fb:login-button scope="public_profile,email"
                                             onlogin="checkRegisterState();"></fb:login-button>
                            <br>

                            <div class="gg_button">
                                        <span
                                                class="g-signin"
                                                data-height="short"
                                                data-callback="onRegister"
                                                data-clientid=<%=SNInfo.googleClientID%>
                                                        data-cookiepolicy="single_host_origin"
                                                data-requestvisibleactions="http://schemas.google.com/AddActivity"
                                                data-scope="https://www.googleapis.com/auth/plus.login">
                                        </span>
                            </div>
                        </div>
                        <div id="driverRegistration" class="hidden">
                            <input id="driverfirstName" type="text" name="driverfirstName" value=""
                                   placeholder="First Name"/><br>
                            <input id="driverlastName" type="text" name="driverlastName" value=""
                                   placeholder="Last Name"/><br>
                            <input id="driverfacebookId" type="hidden" name="driverfacebookId" value=""
                                   placeholder="Facebook Id"/>
                            <input id="drivergoogleplusId" type="hidden" name="drivergoogleplusId" value=""
                                   placeholder="Google Plus Id"/>
                            <input id="driverpersonalID" type="text" name="driverpersonalID" value=""
                                   placeholder="Personal ID"/><br>
                            <input id="drivercompanyCode" type="text" name="drivercompanyCode" value=""
                                   placeholder="Company Code"/><br>
                            <input id="driveremail" type="email" name="driveremail" value="" placeholder="Email"/><br>
                            <input id="driverpassword" type="password" name="driverpassword" value=""
                                   placeholder="Password"/><br>
                            <input id="driverrepassword" type="password" name="driverrepassword" value=""
                                   placeholder="Repeat Password"/><br>
                            <select id="drivergender" name="drivergender">
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select><br>
                            <input id="driverphoneNumber" type="text" name="driverphoneNumber" value=""
                                   placeholder="Phone Number"/><br>
                            <fb:login-button scope="public_profile,email"
                                             onlogin="checkRegisterState();"></fb:login-button>
                            <br>

                            <div class="gg_button">
                                        <span
                                                class="g-signin"
                                                data-height="short"
                                                data-callback="onRegister"
                                                data-clientid=<%=SNInfo.googleClientID%>
                                                        data-cookiepolicy="single_host_origin"
                                                data-requestvisibleactions="http://schemas.google.com/AddActivity"
                                                data-scope="https://www.googleapis.com/auth/plus.login">
                                        </span>
                            </div>
                        </div>
                        <div class="g-recaptcha" data-sitekey="6LcS8QgTAAAAAP98JRVGsRNt0uKxnX_LDKRiWN70"></div>
                    </form>
                    <button id="registerBtn" class="hidden button special">Register</button>
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
                    <span id="regStatus" class="hidden"> Registration Successfull</span>

                    <form id="loginForm">
                        <div class="4u">
                            <input type="radio" class="loginChange" id="userLogin" name="action" value="loginUser">
                            <label for="userLogin">User</label>
                        </div>
                        <div class="4u">
                            <input type="radio" class="loginChange" id="driverLogin" name="action" value="loginDriver">
                            <label for="driverLogin">Driver</label>
                        </div>
                        <div class="4u">
                            <input type="radio" class="loginChange" id="companyLogin" name="action"
                                   value="loginCompany">
                            <label for="companyLogin">Company</label>
                        </div>
                        <br><br>

                        <div class="innerContainer">
                            <input type="text" name="email" placeholder="Email"><br>
                            <input type="password" name="password" placeholder="Password"><br>
                            <button class="btn btn-success btn-sm" style="float:right" id="loginBtn">Log In</button>
                            <fb:login-button scope="public_profile,email"
                                             onlogin="checkLoginState();"></fb:login-button>
                            <br>

                            <div class="gg_button">
                                        <span
                                                class="g-signin"
                                                data-height="short"
                                                data-callback="onLogin"
                                                data-clientid=<%=SNInfo.googleClientID%>
                                                        data-cookiepolicy="single_host_origin"
                                                data-requestvisibleactions="http://schemas.google.com/AddActivity"
                                                data-scope="https://www.googleapis.com/auth/plus.login">
                                        </span>
                            </div>
                            <!--  <div id="loginStatus">
                            </div>
                            -->
                            <br><br>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                </div>
            </div>

        </div>
    </div>

    <footer id="footer">
        <ul class="icons">
            <li><a href="#" class="icon fa-twitter"><span class="label">Follow us</span></a></li>
            <li><a href="#" class="icon fa-facebook"><span class="label">Like Us</span></a></li>
            <li><a href="#" class="icon fa-envelope-o"><span class="label">Email Us</span></a></li>
            <!-- TODO es ragaca ar chans dasamtavrebels gavs -->
        </ul>
        <ul class="copyright">
            <li>&copy; ღმერთჩემა & co.</li>
        </ul>
    </footer>
    <% } else {%>
    <section id="panel">
        <% if (user != null) { %>
        <jsp:include page="user.jsp"/>
        <% } else if (driver != null) { %>
        <jsp:include page="driver.jsp"/>
        <% } else if (company != null) { %>
        <jsp:include page="company.jsp"/>
        <% } %>
    </section>
    <% }%>

</div>

</body>
</html>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
