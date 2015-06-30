/**
 * Created by Alex on 6/30/2015.
 */

(function () {
    var po = document.createElement('script');
    po.type = 'text/javascript';
    po.async = true;
    po.src = 'https://apis.google.com/js/client:plusone.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(po, s);
})();

function signinCallback(authResult) {
    if (!authResult['g-oauth-window'] || !authResult['status']['signed_in'])
        return;

    gapi.client.load('plus', 'v1', function () {
        var request = gapi.client.plus.people.get({
            'userId': 'me'
        });

        request.execute(function (resp) {
            if (clickChoice == "login") {
                gpLogin(resp);
            } else if (clickChoice == "register") {
                gpRegister(resp);
            } else if (clickChoice == "add") {
                gpAdd(resp);
            }
        });
    });
}

function gpLogin(resp) {
    var googleplusID = resp.id;
    var objectName;
    if ($("#userLogin:checked").val() != undefined) {
        objectName = "loginGPUser";
    } else if ($("#driverLogin:checked").val() != undefined) {
        objectName = "loginGPDriver";
    } else if ($("#companyLogin:checked").val() != undefined) {
        objectName = "loginGPCompany";
    }
    $.ajax({
        url: "/login",
        method: "post",
        data: {
            "action": objectName,
            "googleplusID": googleplusID
        },
        cache: false,
        success: function (data) {
            console.log("Logged in\n" + JSON.stringify(googleplusID));
            console.log(data);
            $("footer").hide();
            $("#panel").html(data);
            $("#loginModal").modal('hide');
            $("#banner").hide();
            initializeMap();
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(googleplusID));
        }
    });
}

function gpRegister(resp) {
    var googleplusID = resp.id;
    var nameAndSurname = resp.displayName;
    var name = nameAndSurname.split(" ", 1);
    var surname = "";
    var phoneNumber = undefined;
    var sex = undefined;
    var email = resp.email;

    if (nameAndSurname.indexOf(' ') != -1)
        surname = nameAndSurname.substr(nameAndSurname.indexOf(' ') + 1, nameAndSurname.length - name.length - 1);

    if ($("#userReg:checked").val() != undefined) {
        if (googleplusID != undefined) {
            $("#usergoogleplusID").val(googleplusID);
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
        if (googleplusID != undefined) {
            $("#drivergoogleplusId").val(googleplusID);
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
        if (googleplusID != undefined) {
            $("#companygoogleplusId").val(googleplusID);
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

function gpAdd(resp) {
    var googleplusID = resp.id;
    $.ajax({
        url: "/social",
        method: "post",
        data: {
            "action": "addGPAccount",
            "googleID": googleplusID
        },
        cache: false,
        success: function (data) {
            console.log("Facebook Account added\n" + JSON.stringify(googleplusId));
            console.log(data);
            //$("#panel").load(data);
            $("footer").hide();
            $("#panel").html(data);
            $("#loginModal").modal('hide');
            $("#banner").hide();
            initializeMap();
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(googleplusId));
        }
    });
}