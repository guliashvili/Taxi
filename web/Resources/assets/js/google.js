/**
 * Created by Alex on 6/30/2015.
 */

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    if (clickChoice == "login") {
        gpLogin(profile);
    } else if (clickChoice == "register") {
        gpRegister(profile);
    } else if (clickChoice == "add") {
        gpAdd(profile);
    }
}

function gpLogin(profile) {
    var googleplusID = profile.getId();
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

function gpRegister(profile) {
    var googleplusID = profile.getId();
    var nameAndSurname = profile.getName();
    var name = nameAndSurname.split(" ", 1);
    var surname = "";
    var email = profile.getEmail();

    if (nameAndSurname.indexOf(' ') != -1)
        surname = nameAndSurname.substr(nameAndSurname.indexOf(' ') + 1, nameAndSurname.length - name.length - 1);

    if ($("#userReg:checked").val() != undefined) {
        if (googleplusID != undefined) {
            $("#usergoogleplusID").val(googleplusID);
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
    }
}

function gpAdd(profile) {
    var googleplusID = profile.getId();
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