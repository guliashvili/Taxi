/**
 * Created by Alex on 6/18/2015.
 */

// Here goes user panel management.
$(document).ready(function(){
    initializeMap();
    createPreferencesSaves();
});
function createPreferencesSaves(){
    $("#passChange").click(function(e){
        var formDate = $("#passForm").serialize();
        $.ajax({
            url: "/update",
            action:"uPassword",
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
            action:"uPreferences",
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
