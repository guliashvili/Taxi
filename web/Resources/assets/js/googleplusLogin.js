/**
 * Created by Tornike on 27.06.2015.
 */

(function() {
    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/client:plusone.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
})();

function onLogin(authResult) {
    console.log("GELA");
    if (!authResult['g-oauth-window'] || !authResult['status']['signed_in'])
        return;
    gapi.client.load('plus','v1', function() {
        var requestLogin = gapi.client.plus.people.get({
            'userId': 'me'
        });
        requestLogin.execute(function (resp) {
            console.log(resp);
            var googleplusId = resp.id;
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
                    "googleplusId": googleplusId
                },
                cache: false,
                success: function (data) {
                    console.log("Logged in\n" + JSON.stringify(googleplusId));
                    console.log(data);
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
        });
    });
}