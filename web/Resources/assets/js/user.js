/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

/**
 * Created by Alex on 6/18/2015.
 */
$(document).ready(function(){
    initializeO();
});
var records=[];
var travel=null;
var drivers;
function fetchEverything(){
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action:"getUserInfo"},
        cache: false,
        success: function(data){
            console.log(data);
        },
        error: function(data){
            console.error(data);
        }
    });
}
var driverMarkersT=[];
function initializeSockets(mToken){
    var websocket = new WebSocket("ws://" + window.location.host + "/wsapp/" + 0 + "/" + mToken);

    websocket.onopen = function (arg) {
        console.log("success", "connected");
        fetchEverything();
    };

    websocket.onmessage = function (arg) {
        console.log("success", arg.data);
        drivers=JSON.parse(arg.data);
        driverList="";

        while (driverMarkersT.length > 0) {
            removeFromMap(driverMarkersT.pop());
        }

        if(drivers==null){
            return;
        }
        while(driverMarkersT.length>0){removeFromMap(driverMarkersT);}
        for(var i=0;i<drivers.length;i++){
            var cont="";
            cont = "<div style='background-color:#FFD800;width:200px;height:200px' id='driversList'>";
            cont += "Price:" + drivers[i].maxPrice + " Phone:" + drivers[i].driver.phoneNumber + " CAR:" + drivers[i].driver.car.carID + "<br>" + "Rating:" + drivers[i].driver.rating + "<br>";
            cont += "<button onclick='acceptDriver(" + drivers[i].orderID + "," + drivers[i].driver.driverID + ")' class='special'>Accept</button>";
            cont += "<button onclick='rejectDriver(" + drivers[i].orderID + "," + drivers[i].driver.driverID + ")' class='special'>Reject</button>";

            cont += "</div>";
            var tmpWindow = new google.maps.InfoWindow({
                content: cont
            });
            var tmpMarker = new google.maps.Marker({
                position: new google.maps.LatLng(drivers[i].location.latitude, drivers[i].location.longitude),
                map: map,
                title: 'Taxi Map',
                icon: getIcon("\uf1ba"),
                labelContent: '<i class="fa fa-send fa-3x" style="color:rgba(153,102,102,0.8);"></i>'
            });
            tmpWindow.open(map, tmpMarker);
            driverMarkersT.push(tmpMarker);
        }
        if(drivers.length == 0){
            var dateAsker = updateAsker();
            clearPinPoint();
            removeFromMap(startMarker);
            removeFromMap(endMarker);
            startMarker = null;
            endMarker=null;
            askWindow.setContent(dateAsker);
            if(askWindow.getMap()==null) {
                askWindow.setMap(map);
                askWindow.close();
            }
        }
        if(askWindow!=null){
            if(drivers.length > 0){
                askWindow.setContent(driversList);
                if(askWindow.getMap()==null){
                    askWindow.setMap(map);
                    askWindow.close();
                }
            }
        }
        removeFromMap(startMarker);
        removeFromMap(endMarker);

        $("input").change(function (e) {
            $(e.target).attr("value", $(e.target).val());
        });
        $("#revokeOrderBTN").removeClass("hidden");
    };

    websocket.onclose = function (arg) {
        console.log("success", "disconnected");
    };

    websocket.error = function (arg) {
        console.error(arg.data);
    };

    return websocket;
}
function initializeO(){
    //checkLoginState();
    $("input").change(function(e){
        $(e.target).attr("value",$(e.target).val());
    });
    $(".headCaption").removeClass("hidden");
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
                records[i]['recid']= "<button class='special fit' onclick='constructReview("+records[i].orderID+",true)'>+</button>";
                if(records[i].revokedByDriver){
                    records[i]['style']="background-color:red";
                }
                if(records[i].revokedByUser){
                    records[i]['style']="background-color:green";
                }
                var recM=[records[i].startLocation.latitude,records[i].startLocation.longitude,records[i].endLocation.latitude,records[i].endLocation.longitude];
                console.log(recM);
                records[i]['startLocation']="<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint("+recM[0]+','+recM[1]+','+recM[2]+','+recM[3]+");'>";
                records[i]['endLocation']="<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint("+recM[2]+','+recM[3]+','+recM[0]+','+recM[1]+");'>";
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
function revokeOrder() {
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action: "revokeOrderUser"},
        cache: false,
        success: function (data) {
            console.log(data);
            clearPinPoint();
            removeFromMap(startMarker);
            removeFromMap(endMarker);
            askWindow.close();
            removeFromMap(askWindow);
            startMarker = null;
            endMarker = null;
        },
        error: function (data) {
            clearPinPoint();
            removeFromMap(startMarker);
            removeFromMap(endMarker);
            askWindow.close();
            removeFromMap(askWindow);
            startMarker = null;
            endMarker = null;
        }
    });
}
var driversList = "<img src='Resources/images/loading.gif' style='width:400px;height:auto' />";
function addOrderJ(){
    var arr = $("#mapOrder input");
    var data = {};
    for (var i = 1; i < arr.length; i++) {
        data[$(arr[i]).attr("name")] = $(arr[i]).val();
    }
    data["action"] = "addOrder";
    data["startLatitude"] = startMarker.position.A;
    data["startLongitude"] = startMarker.position.F;
    data["endLatitude"] = endMarker.position.A;
    data["endLongitude"] = endMarker.position.F;
    console.log(data);
    $.ajax({
        url: "/order",
        method: "post",
        cache: false,
        data: data,
        success: function (data) {
            if(askWindow!=null) {
                askWindow.setContent(driversList);
                if(askWindow.getMap()==null){
                    askWindow.close();
                    askWindow.setMap(map);
                }
                removeFromMap(startMarker);
                removeFromMap(endMarker);
            }
        },
        error: function (data) {
            console.error(data);
        }
    });
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
            { field: 'recid', caption: 'Review', size: '10%', sortable: true, attr: 'align=center' },
            { field: 'driverID', caption: 'driverID', size: '5%', sortable: true, resizable: false },
            {field: 'numPassengers', caption: 'Passangers', size: '5%', resizable: true},
            { field: 'startLocation', caption: 'startLocation', size: '5%', resizable: true },
            {field: 'endTime', caption: 'Ended', size: '30%', resizable: true},
            {field: 'paymentAmount', caption: 'Amount', size: '10%', resizable: true},
            {field: 'callTime', caption: 'Call', size: '30%', resizable: true},
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
    removeFromMap(travel);
    travel=new google.maps.Polyline({
        path: coords,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
    });
    travel.setMap(map);
    if(endMarker!=null && askWindow!=null){
        askWindow.open(map,startMarker);
        $("input").change(function(e){
            $(e.target).attr("value",$(e.target).val());
        });
    }
}
function acceptDriver(orderID, driverID) {
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action: "userAccept", orderID: orderID, driverID: driverID},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error(JSON.stringify(formData));
        }
    });
}
function revokeOffer(index){
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action: "revokeOrderUser"},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(formData));
        }
    });
}
function rejectDriver(orderID, driverID) {
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action: "userReject", orderID: orderID, driverID: driverID},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error(JSON.stringify(formData));
        }
    });
}
function resendEmail(){
    $.ajax({
        url: "/sendverification",
        method: "post",
        data: {action: "uEmail"},
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
        data: {action: "uPhone"},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error(data);
        }
    });
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
                console.error(JSON.stringify(formData));
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
                var dateAsker = updateAsker();

                askWindow.setContent(dateAsker);
                askWindow.setMap(map);
            },
            error: function (data) {
                console.error(JSON.stringify(data));
            }
        });
    });
}
