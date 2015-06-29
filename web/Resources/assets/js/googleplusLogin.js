/**
 * Created by Tornike on 27.06.2015.
 */

function onLogin(googleUser) {
    if(!googleUser['g-oauth-window'])
        return;
    var id_token = googleUser.getAuthResponse().id_token;
    var objectName;
    if ($("#userLogin:checked").val() != undefined) {
        objectName = "loginGPUser";
    } else
    if ($("#driverLogin:checked").val() != undefined) {
        objectName = "loginGPDriver";
    }else
    if ($("#companyLogin:checked").val() != undefined) {
        objectName = "loginGPCompany";
    }
    $.ajax({
        url: "/login",
        method: "post",
        data: {
            "action" : objectName,
            "id_token" : id_token
        },
        cache: false,
        success: function (data) {
            console.log("Logged in\n" + JSON.stringify(id_token));
            console.log(data);
            //$("#panel").load(data);
            $("footer").hide();
            $("#panel").html(data);
            $("#loginModal").modal('hide');
            $("#banner").hide();
            initializeMap();
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(id_token));
        }
    });
}