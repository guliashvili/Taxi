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
            error: function (args) {
                console.log(args);

                var resp = JSON.parse(args.responseText);

                for (var key in resp) {
                    $("#errorLog").append("<span>" + key + " " + resp[key] + "</span><br>");
                }
            }
        });

        return false;
    });
}