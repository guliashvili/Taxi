/**
 * Created by Alex on 6/18/2015.
 */

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
            action:"cPassword",
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
