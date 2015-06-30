/**
 * Created by Alex on 6/18/2015.
 */

// Here goes user panel management.
$(document).ready(function () {
    initializeO();
});
var latitude = null;
var longitude = null;
var records=[];
function fetchEverything(){
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action:"getDriverInfo"},
        cache: false,
        success: function(data){
            console.log(data);
        },
        error: function(data){
            console.log(data);
        }
    });
}
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
                //console.log(data);
            },
            error: function (data) {
                console.log(data);
            }
        });
    }, 3000);

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
                records[i]['endLocation']="<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint("+recM[2]+','+recM[3]+','+recM[0]+','+recM[1]+");'>";
            }
            console.log(data);
            generateGrid();
        },
        error: function (data) {
            console.log(data);
        }
    });
}
function updateLatLang(position) {
    latitude = position.coords.latitude;
    longitude = position.coords.longitude;
    if(curMarker!=null){curMarker.setMap(null);}
    curMarker = new google.maps.Marker({
        position: {lat: position.coords.latitude, lng: position.coords.longitude},
        map: map,
        title: 'Taxi Map'
    });
}
function initializeSockets(mToken) {
    var websocket = new WebSocket("ws://" + window.location.host + "/wsapp/" + 1 + "/" + mToken);

    websocket.onopen = function (arg) {
        console.log("success", "connected");
        fetchEverything();
    };

    websocket.onmessage = function (arg) {
        console.log("success", arg.data);
        var obj = JSON.parse(arg.data);
        if(obj.inCar==undefined){
            addOrder(obj);
        }else{
            defineRoute(obj);
        }
    };

    websocket.onclose = function (arg) {
        console.log("success", "disconnected");
    };

    websocket.error = function (arg) {
        console.error(arg.data);
    };

    return websocket;
}
var latMap = {};
var orderInfo;
geocoder = new google.maps.Geocoder();
function addOrder(ordr) {
    console.log("addOrder");
    orderInfo=ordr;
    console.log(orderInfo);
    var out = "";
    latMap = {};
    for (var i = 0; i < orderInfo.length; i++) {
        var start = orderInfo[i].start.latitude + "," + orderInfo[i].start.longitude;
        var end = orderInfo[i].end.latitude + "," + orderInfo[i].end.longitude;
        console.log(orderInfo[i]);
        if(latMap[start]==undefined) {
            geocodeQuery.push(start);
        }
        if(latMap[end]==undefined) {
            geocodeQuery.push(end);
        }
    }
    if(geocodeQuery.length>0){
        geocodeTimer=setInterval(function(){fetchGeocode();}, 1000);
    }
    if (orderInfo.length == 0) {
        $("#orderModalCont").html(generateModal(orderInfo));
        $("#orderModal").modal("hide");


        clearInterval(geocodeTimer);
    }
}
var geocodeQuery=[];
var geocodeTimer;
function fetchGeocode(){
    if(geocodeQuery.length==0){
        $("#orderModalCont").html(generateModal(orderInfo));
        $("#orderModal").modal("show");
        clearInterval(geocodeTimer);
    }
    var start=geocodeQuery[geocodeQuery.length-1];
    var arr=start.split(",");
    var startM=new google.maps.LatLng(arr[0],arr[1]);
    geocoder.geocode({'latLng': startM}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            if (results[1]) {
                latMap[start] = results[1].formatted_address;
                geocodeQuery.pop();
                if (geocodeQuery.length == 0) {
                    $("#orderModalCont").html(generateModal(orderInfo));
                    $("#orderModal").modal("show");
                    clearInterval(geocodeTimer);
                }
            } else {
                latMap[start] = "unknown";
            }
        }
    });
}
function size(obj) {
    var size = 0, key;
    for (key in obj) {
        if (obj.hasOwnProperty(key)) size++;
    }
    return size;
}
function generateModal(orderInfo) {
    var out="";
    for(var i=0;i<orderInfo.length;i++){
        out+='<div class="orderDiv">';
        out+='<span>'+ orderInfo[i].user.firstName + " " + orderInfo[i].user.lastName +'</span>';
        out+='<span>requires your assistance in getting from:</span>';
        out+='<span>'+ latMap[orderInfo[i].start.latitude+","+orderInfo[i].start.longitude] + " to " + latMap[orderInfo[i].end.latitude+","+orderInfo[i].end.longitude] +'</span>';
        out+='<span> and offers:'+orderInfo[i].maxPrice+'</span>';
        if(orderInfo[i].user.preference.wantsAlone){
            out+='<span> He wants to travel alone</span>';
        }
        if(orderInfo[i].user.gender="MALE") {
            out += '<span> And is currently with his ' + orderInfo[i].user.preference.passengersCount + ' friends</span>';
        }else{
            out += '<span> And is currently with her ' + orderInfo[i].user.preference.passengersCount + ' friends</span><br>';
        }
        out+='<span> and offers:'+orderInfo[i].maxPrice+'</span><br>';
        out+='<button onclick="acceptOffer('+i+')" class="special">Accept Offer</button><br>';
        out+='<button onclick="rejectOffer('+i+')" class="special">Reject Offer</button><br>';
        out+='</div>'
    }
    return out;
}
function rejectOffer(index){
    $.ajax({
        url: "/order",
        method: "post",
        data: {action: "driverReject",orderID: orderInfo[index].orderID,userID: orderInfo[index].user.userID},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(formData));
        }
    });
}
function revokeOffer(index){
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action: "revokeOrderDriver",orderID: orderInfo[index].orderID,userID: orderInfo[index].user.userID},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(formData));
        }
    });
}
function acceptOffer(index){
    console.log(orderInfo[index].orderID,orderInfo[index].user.userID);
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action: "driverAccept",orderID: orderInfo[index].orderID,userID: orderInfo[index].user.userID},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(formData));
        }
    });
}
function carryRoute(index){
    var elem = curRoute[index];
    var action="leaveUser";
    if(elem.pickUser){
        action = "pickUser";
    }
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action: action,userID: elem.orderInfo.user.userID,userID: elem.orderInfo.orderID},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error(data);
        }
    });
}
function resendEmail() {
    $.ajax({
        url: "/sendverification",
        method: "post",
        data: {action: "dEmail"},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error(data);
        }
    });
}
function resendPhone() {
    $.ajax({
        url: "/sendverification",
        method: "post",
        data: {action: "dPhone"},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error(data);
        }
    });
}
function createPreferencesSaves() {
    $("#passChange").click(function (e) {
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
    $("#savePref").click(function (e) {
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
    $("#companyCodeBtn").click(function (e) {
        var formData = $("#companyCodeForm").serialize();
        $.ajax({
            url: "/sendverification",
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
    $("#cPrefBtn").click(function (e) {
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
    $("#cCarBtn").click(function (e) {
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
            { field: 'recid', caption: 'orderID', size: '10%', sortable: true, attr: 'align=center' },
            { field: 'driverID', caption: 'driverID', size: '5%', sortable: true, resizable: false },
            { field: 'numPassengers', caption: 'numPassengers', size: '5%', resizable: true },
            { field: 'startLocation', caption: 'startLocation', size: '5%', resizable: true },
            { field: 'endLocation', caption: 'endLocation', size: '5%', resizable: true },
            { field: 'endTime', caption: 'endTime', size: '30%', resizable: true },
            { field: 'paymentAmount', caption: 'paymentAmount', size: '10%', resizable: true },
            { field: 'callTime', caption: 'callTime', size: '30%', resizable: true },
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
var curRoute;
var routeMarker=null;
function defineRoute(route){
    curRoute=route;
    displayRoute();
    var out="";
    for(var i=0;i<curRoute.length;++i){
        out+=latMap[curRoute[i].loc.latitude + "," + curRoute[i].loc.longitude];
        out+="<button onclick='carryRoute' class='special fa fa-check'>.</button>";
    }
    $("#routeDiv").html(out);
}
function displayRoute(){
    if(routeMarker!=null){
        routeMarker.setMap(null);
    }
    if(curRoute.route.length == 0) return;
    var routeElem = curRoute.route[0];
    routeMarker = new google.maps.Marker({
        position: {lat: routeElem.loc.latitude, lng: routeElem.loc.longitude},
        map: map,
        title: 'Taxi Map'
    });
    drawRoute(curMarker,routeMarker);
}
function drawRoute(marker1,marker2) {
    var request = {
        origin:marker1,
        destination:marker2,
        travelMode: google.maps.TravelMode.DRIVING
    };
    directionsService.route(request, function(response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        }
    });
}