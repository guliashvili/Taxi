/**
 * Created by Tornike on 27.06.2015.
 */

function onRegister(authResult) {
    if (!authResult['g-oauth-window'] || !authResult['status']['signed_in'])
        return;
    gapi.client.load('plus','v1', function() {
        var requestRegister = gapi.client.plus.people.get({
            'userId': 'me'
        });
        requestRegister.execute(function (resp) {
            console.log(resp);
            var googleplusId = resp.id;
            var nameAndSurname = resp.displayName;
            var name = nameAndSurname.split(" ", 1);
            var surname = "";
            var phoneNumber = undefined;
            var sex = undefined;
            var email = resp.emails[0].value;

            if (nameAndSurname.indexOf(' ') != -1)
                surname = nameAndSurname.substr(nameAndSurname.indexOf(' ') + 1, nameAndSurname.length - name.length - 1);

            console.log(googleplusId + " " + name + " " + surname + " " + phoneNumber + " " + sex + " " + email);
            if ($("#userReg:checked").val() != undefined) {
                if (googleplusId != undefined) {
                    $("#usergoogleplusId").val(googleplusId);
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
                if (googleplusId != undefined) {
                    $("#drivergoogleplusId").val(googleplusId);
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
                if (googleplusId != undefined) {
                    $("#companygoogleplusId").val(googleplusId);
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

        });
    });
}