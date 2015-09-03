/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

/**
 * Created by Alex on 6/30/2015.
 */

// This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
    if (response.status === 'connected')
        fbRender();
}

// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
    FB.getLoginStatus(function (response) {
        statusChangeCallback(response);
    });
}

window.fbAsyncInit = function () {
    FB.init({
        appId: '771449059639215',
        cookie: true,  // enable cookies to allow the server to access
                       // the session
        xfbml: true,  // parse social plugins on this page
        version: 'v2.2' // use version 2.2
    });
};

// Load the SDK asynchronously
(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.
function fbRender() {
    FB.api('/me', function (response) {
        if (clickChoice == "login") {
            fbLogin(response);
        } else if (clickChoice == "register") {
            fbRegister(response);
        } else if (clickChoice == "add") {
            fbAdd(response);
        }
    });
}
function fbLogin(response) {
    var facebookID = response.id;
    var objectName;
    if ($("#userLogin:checked").val() != undefined) {
        objectName = "loginFbUser";
    } else if ($("#driverLogin:checked").val() != undefined) {
        objectName = "loginFbDriver";
    } else if ($("#companyLogin:checked").val() != undefined) {
        objectName = "loginFbCompany";
    }
    $.ajax({
        url: "/login",
        method: "post",
        data: {
            "action": objectName,
            "facebookID": facebookID
        },
        cache: false,
        success: function (data) {
            console.log("Logged in\n" + JSON.stringify(facebookID));
            console.log(data);
            //$("#panel").load(data);
            $("footer").hide();
            $("#panel").html(data);
            $("#loginModal").modal('hide');
            $("#banner").hide();
            initializeMap();
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(facebookID));
        }
    });
}

function fbRegister(response) {
    var facebookID = response.id;
    var nameAndSurname = response.name;
    var email = response.email;
    var phoneNumber = response.phoneNumber;
    var name = nameAndSurname.split(" ", 1);
    var surname = "";
    var sex = response.gender;

    if (nameAndSurname.indexOf(' ') != -1)
        surname = nameAndSurname.substr(nameAndSurname.indexOf(' ') + 1, nameAndSurname.length - name.length - 1);
    if ($("#userReg:checked").val() != undefined) {
        if (facebookID != undefined) {
            $("#userfacebookId").val(facebookID);
        }
        if (sex != undefined) {
            if (sex == "male") {
                $("#usergender").val('Male');
            }
            else {
                $("#usergender").val('Female');
            }
        }
        if (name != "undefined") {
            $("#userfirstName").val(name);
        }
        if (surname != "undefined") {
            $("#userlastName").val(surname);
        }
        if (email != "undefined") {
            $("#useremail").val(email);
        }
        if (phoneNumber != "undefined") {
            $("#userphoneNumber").val(phoneNumber);
        }
    } else if ($("#driverReg:checked").val() != undefined) {
        if (facebookID != undefined) {
            $("#driverfacebookId").val(facebookID);
        }
        if (name != "undefined") {
            $("#driverfirstName").val(name);
        }
        if (surname != "undefined") {
            $("#driverlastName").val(surname);
        }
        if (email != "undefined") {
            $("#driveremail").val(email);
        }
        if (phoneNumber != "undefined") {
            $("#driverphoneNumber").val(phoneNumber);
        }
        if (sex != undefined) {
            if (sex == "male") {
                $("#drivergender").val('Male');
            }
            else {
                $("#drivergender").val('Female');
            }
        }
    } else if ($("#companyReg:checked").val() != undefined) {
        if (facebookID != undefined) {
            $("#companyfacebookId").val(facebookID);
        }
        if (name != "undefined") {
            $("#companyName").val(name);
        }
        if (email != "undefined") {
            $("#companyemail").val(email);
        }
        if (phoneNumber != "undefined") {
            $("#companyphoneNumber").val(phoneNumber);
        }
    }
}

function fbAdd(response) {
    var facebookID = response.id;

    console.log(facebookID);
    $.ajax({
        url: "/social",
        method: "post",
        data: {
            "action": "addFbAccount",
            "facebookID": facebookID
        },
        cache: false,
        success: function (data) {
            console.log("added", facebookID);
        },
        error: function (data) {
            console.error(data);
        }
    });
}