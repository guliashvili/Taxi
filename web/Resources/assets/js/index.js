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


$(function () {
    $(".regChange").change(function (e) {
        $("#companyRegistration").addClass("hidden");
        $("#userRegistration").addClass("hidden");
        $("#driverRegistration").addClass("hidden");
        $("#" + $(e.target).attr("showf")).removeClass("hidden");
        $("#registerBtn").removeClass("hidden");
    });
    $(".headCaption").addClass("hidden");
    createRegister();
    createLogin();
});