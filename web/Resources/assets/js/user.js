/**
 * Created by Alex on 6/18/2015.
 */
$(document).ready(function(){
    initializeO();
});
var records=[];
var travel=null;
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
                if(records[i].revokedByDriver){
                    records[i]['style']="background-color:red";
                }
                if(records[i].revokedByUser){
                    records[i]['style']="background-color:green";
                }
                var recM=[records[i].startLocation.latitude,records[i].startLocation.longitude,records[i].endLocation.latitude,records[i].endLocation.longitude];
                console.log(recM);
                records[i]['startLocation']="<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint("+recM[0]+','+recM[1]+','+recM[2]+','+recM[3]+");'>";
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
    $("#grid").removeClass("hidden");
    $('#grid').w2grid({
        name: 'grid',
        header: 'Orders',
        show: {
            toolbar: false,
            footer: false
        },
        columns: [
            { field: 'recid', caption: 'orderID', size: '50px', sortable: true, attr: 'align=center' },
            { field: 'driverID', caption: 'driverID', size: '30%', sortable: true, resizable: false },
            { field: 'numPassengers', caption: 'numPassengers', size: '40%', resizable: true },
            { field: 'startLocation', caption: 'startLocation', size: '20px', resizable: true },
            { field: 'Travel', caption: 'endLocation', size: '20px', resizable: true },
            { field: 'endTime', caption: 'endTime', size: '20px', resizable: true },
            { field: 'paymentAmount', caption: 'paymentAmount', size: '20px', resizable: true },
            { field: 'callTime', caption: 'callTime', size: '20px', resizable: true },
        ],
        searches: [
            { field: 'driverID', caption: 'driverID', type: 'text' },
            { field: 'orderID', caption: 'orderID', type: 'text' },
        ],
        sortData: [{ field: 'orderID', direction: 'ASC' }],
        records: records,
        onLoad: function(event){
            //because i am too lazy to read docs, hoping to make a difference
            console.log("grid loaded");
            $("#grid").addClass("hidden");//needs fixing somehow fucks grid up dunno why
        }
    });
}
function askForDate(){
    var coords=[
        new google.maps.LatLng(startMarker.position.A, startMarker.position.F),
        new google.maps.LatLng(endMarker.position.A, endMarker.position.F)
    ];
    if(travel!=null){
        travel.setMap(null);
    }
    travel=new google.maps.Polyline({
        path: coords,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
    });
    travel.setMap(map);
    if(endMarker!=null){
        askWindow.open(map,startMarker);
        $('#datetimepicker10').datetimepicker();
    }
}
function resendEmail(){

}
function resenPhone(){

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
