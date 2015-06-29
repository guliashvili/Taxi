/**
 * Created by Alex on 6/18/2015.
 */

// Here goes user panel management.
$(document).ready(function(){
    initializeO();
});
var latitude = null;
var longitude = null;
function initializeO(){
    $(".headCaption").removeClass("hidden");
    initializeMap();
    createPreferencesSaves();
    setInterval(function () {
        navigator.geolocation.getCurrentPosition(updateLatLang);
        if (latitude == null || longitude == null) return;
        $.ajax({
            url: "/update",
            method: "post",
            data: {
                action: "dLocation",
                latitude: latitude,
                longitude: longitude
            },
            cache: false,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.error(data);
            }
        });
    }, 3000);
    //generateGrid();
}
function updateLatLang(position) {
    latitude = position.coords.latitude;
    longitude = position.coords.longitude;
}
function initializeSockets(mToken) {
    var websocket = new WebSocket("ws://" + window.location.host + "/wsapp/" + 1 + "/" + mToken);

    websocket.onopen = function (arg) {
        console.log("success", "connected");
    };

    websocket.onmessage = function (arg) {
        console.log("success", arg.data);
        addOrder(arg.data);
    };

    websocket.onclose = function (arg) {
        console.log("success", "disconnected");
    };

    websocket.error = function (arg) {
        console.error(arg.data);
    };

    return websocket;
}
function resendEmail(){

}
function resendPhone(){

}
function createPreferencesSaves(){
    $("#passChange").click(function(e){
        var formData = $("#passForm").serialize();
        $.ajax({
            url: "/update",
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
        var formData = $("#prefForm").serialize();
        $.ajax({
            url: "/update",
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
        var formData = $("#companyCodeForm").serialize();
        $.ajax({
            url: "/update",
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
        var formData = $("#cPrefForm").serialize();
        $.ajax({
            url: "/update",
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
        var formData = $("#cCarForm").serialize();
        $.ajax({
            url: "/update",
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
