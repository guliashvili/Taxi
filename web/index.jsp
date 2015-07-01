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
    <script type="text/javascript" src="/Resources/assets/js/jquery-2.1.4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="Resources/assets/css/w2ui-1.4.2.min.css"/>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css"
          rel="stylesheet">
    <link rel="stylesheet" href="Resources/assets/css/bootstrap.min.css">
    <script src="Resources/assets/js/bootstrap.min.js"></script>
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
    <script src="Resources/assets/js/review.js"></script>
    <script type="text/javascript" src="Resources/assets/js/w2ui-1.4.2.min.js"></script>

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
    <script>
        var clickChoice = undefined;
    </script>
    <script src="/Resources/assets/js/facebook.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="/Resources/assets/js/google.js"></script>
    <script>
        (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function () {
                        (i[r].q = i[r].q || []).push(arguments)
                    }, i[r].l = 1 * new Date();
            a = s.createElement(o),
                    m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
        })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

        ga('create', 'UA-XXXX-Y', 'auto');
        ga('send', 'pageview');
    </script>
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
                    <li><a href="#" class="button special" data-toggle="modal"
                           onclick="$('#loginModal').modal('show');clickChoice='login';">Sign in</a>
                    </li>
                    <li><a href="#" class="button special" data-toggle="modal"
                           onclick="$('#registerModal').modal('show');clickChoice='register';">Register</a>
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
                            <input id="companyCode" type="text" name="companyCode" value="" placeholder="Company Code"
                                   required/><br>
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
                                             onlogin="checkLoginState();"></fb:login-button>
                            <br>

                            <div class="g-signin2" data-onsuccess="onSignIn"></div>
                        </div>
                        <div id="userRegistration" class="hidden">
                            <input id="userfirstName" type="text" name="userfirstName" value=""
                                   placeholder="First Name"/><br>
                            <input id="userfacebookId" type="hidden" name="userfacebookId" value=""
                                   placeholder="Facebook Id"/>
                            <input id="usergoogleplusID" type="hidden" name="usergoogleplusId" value=""
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
                                             onlogin="checkLoginState();"></fb:login-button>
                            <br>

                            <div class="g-signin2" data-onsuccess="onSignIn"></div>
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
                                             onlogin="checkLoginState();"></fb:login-button>
                            <br>

                            <div class="g-signin2" data-onsuccess="onSignIn"></div>
                        </div>
                        <div class="g-recaptcha" data-sitekey="6LcS8QgTAAAAAP98JRVGsRNt0uKxnX_LDKRiWN70"></div>
                    </form>
                    <button type="submit" id="registerBtn" class="hidden button special">Register</button>
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
                        <span id="actionError" class="hidden">Please check action checkbox.</span>
                        <br><br>

                        <div class="innerContainer">
                            <input type="email" id="loginEmail" name="email" placeholder="Email" required><br>
                            <input type="password" name="password" placeholder="Password" required><br>
                            <button type="submit" class="btn btn-success btn-sm" style="float:right" id="loginBtn">Log
                                In
                            </button>
                            <fb:login-button scope="public_profile,email"
                                             onlogin="checkLoginState();"></fb:login-button>
                            <br>

                            <div class="g-signin2" data-onsuccess="onSignIn"></div>
                            <!--  <div id="loginStatus">
                            </div>
                            -->
                            <span id="loginError" class="hidden">Couldn't login wrong parameters.</span>
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
