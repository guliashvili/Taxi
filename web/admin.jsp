<%@ page import="ge.taxistgela.admin.Admin" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 6/30/2015
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
    <script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
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
<body class="lading">
<%
    Admin admin = (Admin) request.getSession().getAttribute(Admin.class.getName());

    if (admin == null) {
%>
<form method="post" action="/admin" id="adminForm">
    <div class="innerContainer">
        <input type="hidden" name="action" value="login"/>
        <input type="text" id="username" name="username" placeholder="Email" required><br>
        <input type="password" name="password" placeholder="Password" required><br>
        <button type="submit" class="btn btn-success btn-sm" style="float:right">Log In</button>
    </div>
</form>
<% } %>
</body>
</html>
