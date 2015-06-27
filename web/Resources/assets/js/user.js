/**
 * Created by Alex on 6/18/2015.
 */
$(document).ready(function(){
    initializeO();
});
var records=[];
function initializeO(){
    initializeMap(true);
    createPreferencesSaves();
    $.ajax({
        url: "/order",
        method: "post",
        data: {action:"getOrders"},
        cache: false,
        success: function (data) {
            records=data;
            for (var i=0;i<records.length;++i){
                records[i]['recid']= records[i].orderID;
            }
            console.log(data);
            generateGrid();
        },
        error: function (data) {
            console.error(data);
        }
    });
    console.log("ajax request sent");
}
function generateGrid(){
    $('#grid').w2grid({
        name: 'grid',
        header: 'List of Names',
        show: {
            toolbar: true,
            footer: true
        },
        columns: [
            { field: 'recid', caption: 'orderID', size: '50px', sortable: true, attr: 'align=center' },
            { field: 'userID', caption: 'userID', size: '30%', sortable: true, resizable: false },
            { field: 'driverID', caption: 'driverID', size: '30%', sortable: true, resizable: false },
            { field: 'numPassengers', caption: 'numPassengers', size: '40%', resizable: true },
            { field: 'startLocation', caption: 'startLocation', size: '120px', resizable: true },
            { field: 'endLocation', caption: 'endLocation', size: '120px', resizable: true },
            { field: 'startTime', caption: 'startTime', size: '120px', resizable: true },
            { field: 'endTime', caption: 'endTime', size: '120px', resizable: true },
            { field: 'paymentAmount', caption: 'paymentAmount', size: '120px', resizable: true },
            { field: 'callTime', caption: 'callTime', size: '120px', resizable: true },
            { field: 'revokedByUser', caption: 'revokedByUser', size: '120px', resizable: true },
            { field: 'revokedByDriver', caption: 'revokedByDriver', size: '120px', resizable: true },
        ],
        searches: [
            { field: 'driverID', caption: 'driverID', type: 'text' },
            { field: 'orderID', caption: 'orderID', type: 'text' },
        ],
        sortData: [{ field: 'orderID', direction: 'ASC' }],
        records: records,
        onLoad: function(e){
            //because i am too lazy to read docs, hoping to make a difference
            console.log("loaded");
            $($("#grid").children()[0]).css("width","90%");
        }
    });
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
