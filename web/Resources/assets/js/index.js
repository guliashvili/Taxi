/**
 * Created by Alex on 6/18/2015.
 */

$.noConflict();

$(function () {
    $(".regChange").change(function (e) {
        $("#companyRegistration").addClass("hidden");
        $("#userRegistration").addClass("hidden");
        $("#driverRegistration").addClass("hidden");
        $("#" + $(e.target).attr("showf")).removeClass("hidden");
    });
});