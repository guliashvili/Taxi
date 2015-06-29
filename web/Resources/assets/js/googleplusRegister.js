/**
 * Created by Tornike on 27.06.2015.
 */

(function() {
    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/client:plusone.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
})();

function onRegister(authResult) {
    if (!authResult['g-oauth-window'] || !authResult['status']['signed_in'])
        return;
    String;
    GOOGLE_ME_URL = "https://www.googleapis.com/plus/v1/people/me";
    final;
    DefaultHttpClient;
    client = new DefaultHttpClient();
    final;
    HttpGet;
    request = new HttpGet(GOOGLE_ME_URL);
    request.addHeader("Authorization", "OAuth=" + authToken);
    request.execute(function (resp) {
            var googleplusId = resp.userid;
            var name = resp.first_name;
            var surname = resp.lastName;
            var phoneNumber = resp.phone;
            var sex = resp.gender;
            var email = resp.gmail;
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
}