/**
 * Created by Alex on 6/18/2015.
 */
var loginParam=null;
$(function () {
    $(".regChange").change(function (e) {
        $("#companyRegistration").addClass("hidden");
        $("#userRegistration").addClass("hidden");
        $("#driverRegistration").addClass("hidden");
        $("#" + $(e.target).attr("showf")).removeClass("hidden");
    });
	$(".loginChange").change(function (e) {
        loginParam=$(e.target).attr("showf");
    });
});