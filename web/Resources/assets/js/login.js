/**
 * Created by Alex on 6/18/2015.
 */


// Login management goes here.

function createLogin() {
    $('#loginForm').submit(function () {
        if (!validateLoginForm())
            return false;

        var formData = $("#loginForm").serialize();

        $.ajax({
            url: "/login",
            method: "post",
            data: formData,
            cache: false,
            success: function (data) {
                console.log("Logged in\n" + JSON.stringify(formData));
                console.log(data);
                //$("#panel").load(data);
                $("footer").hide();
                $("#panel").html(data);
                $("#loginModal").modal('hide');
                $("#banner").hide();
            },
            error: function (data) {
                $("#loginError").removeClass("hidden");
                console.error("Couldn't log in\n" + JSON.stringify(formData));
            }
        });

        return false;
    });
}

function validateLoginForm() {
    $("#actionError").addClass("hidden");
    $("#loginError").addClass("hidden");

    if ($("#userLogin:checked").val() == undefined && $("#driverLogin:checked").val() == undefined
        && $("#companyLogin:checked").val() == undefined) {
        $("#actionError").removeClass("hidden");

        return false;
    }

    return true;
}
