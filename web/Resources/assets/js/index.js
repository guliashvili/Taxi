/**
 * Created by Alex on 6/18/2015.
 */

$(function () {
    $(".regChange").change(function (e) {
        $("#companyRegistration").addClass("hidden");
        $("#userRegistration").addClass("hidden");
        $("#driverRegistration").addClass("hidden");
        $("#" + $(e.target).attr("showf")).removeClass("hidden");
    });
});