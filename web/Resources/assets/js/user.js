/**
 * Created by Alex on 6/18/2015.
 */
$(document).ready(function(){
    initializeO();
});
function initializeO(){
    initializeMap(true);
    createPreferencesSaves();
}

function askForDate(){
    if(endMarker!=null){
        askWindow.open(map,startMarker);
        $('#datetimepicker10').datetimepicker();
    }
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
}
