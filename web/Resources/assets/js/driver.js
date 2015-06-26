/**
 * Created by Alex on 6/18/2015.
 */

// Here goes user panel management.
$(document).ready(function(){
    initializeMap();
    createPreferencesSaves();
    generateGrid();
});
function createPreferencesSaves(){
    $("#passChange").click(function(e){
        var formDate = $("#passForm").serialize();
        $.ajax({
            url: "/update",
            action:"dPassword",
            method: "post",
            data: formData,
            cache: false,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.error("Couldn't log in\n" + JSON.stringify(formData));
            }
        });
    });
    $("#savePref").click(function(e){
        var formDate = $("#prefForm").serialize();
        $.ajax({
            url: "/update",
            action:"dPreferences",
            method: "post",
            data: formData,
            cache: false,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.error("Couldn't log in\n" + JSON.stringify(formData));
            }
        });
    });
    $("#companyCodeBtn").click(function(e){
        var formDate = $("#companyCodeForm").serialize();
        $.ajax({
            url: "/update",
            action:"dCompanyCode",
            method: "post",
            data: formData,
            cache: false,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.error("Couldn't log in\n" + JSON.stringify(formData));
            }
        });
    });
    $("#cPrefBtn").click(function(e){
        var formDate = $("#cPrefForm").serialize();
        $.ajax({
            url: "/update",
            action:"dPreferences",
            method: "post",
            data: formData,
            cache: false,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.error("Couldn't log in\n" + JSON.stringify(formData));
            }
        });
    });
    $("#cCarBtn").click(function(e){
        var formDate = $("#cCarForm").serialize();
        $.ajax({
            url: "/update",
            action:"dCar",
            method: "post",
            data: formData,
            cache: false,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.error("Couldn't log in\n" + JSON.stringify(formData));
            }
        });
    });
}
