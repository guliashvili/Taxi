/**
 * Created by Alex on 6/18/2015.
 */

/**
 * Created by Alex on 6/18/2015.
 */

// Here goes user panel management.
$(document).ready(function(){
    initializeO();
});
function resendEmail(){
    $.ajax({
        url: "/sendverification",
        method: "post",
        data: {action: "cEmail"},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error(data);
        }
    });
}
function resendPhone(){
    $.ajax({
        url: "/sendverification",
        method: "post",
        data: {action: "cPhone"},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error(data);
        }
    });
}
function initializeO(){
    $(".headCaption").removeClass("hidden");
    initializeMap();
    createPreferencesSaves();
    generateGrid();
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
}
