/**
 * Created by Alex on 6/18/2015.
 */


// Login management goes here.

function createLogin () {
    $('#loginBtn').click(function () {
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
                initializeMap();
                createPreferencesSaves();
                generateGrid();
            },
            error: function (data) {
                console.error("Couldn't log in\n" + JSON.stringify(formData));
            }
        });

        return false;
    });
}