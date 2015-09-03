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
