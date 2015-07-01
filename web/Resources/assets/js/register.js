/**
 * Created by Alex on 6/18/2015.
 */

// Registration management goes here.
function createRegister () {
    $('#registerBtn').click(function () {
        $("#errorLog").html("");

        var formData = $("#registrationForm").serialize();

        $.ajax({
            url: "/register",
            method: "post",
            data: formData,
            cache: false,
            success: function (data) {
                $("#registerModal").modal('hide');
                $("#loginModal").modal('show');
                $("#regStatus").removeClass('hidden');
            },
            error: function (data) {
                console.log(data);

                var resp = data;

                for (var key in resp) {
                    console.log(key + " " + resp(key));
                    $("#errorLog").append(key + " " + resp(key));
                }
            }
        });

        return false;
    });
}