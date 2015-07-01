/**
 * Created by Alex on 6/18/2015.
 */

// Here goes user panel management.
$(document).ready(function () {
    initializeO();
});
/**
 * my current latitude
 * @type {double}
 */
var latitude = null;
/**
 * my current longitude
 * @type {double}
 */
var longitude = null;
/**
 * grid data
 * @type {Array}
 */
var records = [];
/**
 * fetches needed information from server
 */
function fetchEverything() {
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action: "getDriverInfo"},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.log(data);
        }
    });
}
/**
 * constructor for driver.js
 */
function initializeO() {
    // checkLoginState();
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
        data: {action: "getOrders"},
        cache: false,
        success: function (data) {
            records = data;
            for (var i = 0; i < records.length; ++i) {
                records[i]['recid']= "<button class='special fit' onclick='constructReview("+records[i].orderID+",false)'>+</button>";
                records[i]['userID'] = records[i].userID;
                if (records[i].revokedByDriver) {
                    records[i]['style'] = "background-color:red";
                }
                if (records[i].revokedByUser) {
                    records[i]['style'] = "background-color:green";
                }
                var recM = [records[i].startLocation.latitude, records[i].startLocation.longitude, records[i].endLocation.latitude, records[i].endLocation.longitude];
                console.log(recM);
                records[i]['startLocation'] = "<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint(" + recM[0] + ',' + recM[1] + ',' + recM[2] + ',' + recM[3] + ");'>";
                records[i]['endLocation'] = "<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint(" + recM[2] + ',' + recM[3] + ',' + recM[0] + ',' + recM[1] + ");'>";
            }
            console.log(data);
            generateGrid();
        },
        error: function (data) {
            console.log(data);
        }
    });
}
/**
 * updates current location and sets marker
 * @param position
 */
function updateLatLang(position) {
    latitude = position.coords.latitude;
    longitude = position.coords.longitude;
    if (curMarker != null) {
        removeFromMap(curMarker);
    }
    curMarker = new google.maps.Marker({
        position: {lat: position.coords.latitude, lng: position.coords.longitude},
        map: map,
        title: 'Taxi Map'
    });
    //map.setCenter(new google.maps.LatLng(position.coords.latitude,position.coords.longitude));
}
/**
 * initializes sockets should be called only once
 * @param mToken
 * @returns {WebSocket}
 */
function initializeSockets(mToken) {
    var websocket = new WebSocket("ws://" + window.location.host + "/wsapp/" + 1 + "/" + mToken);

    websocket.onopen = function (arg) {
        console.log("success", "connected");
        fetchEverything();
    };

    websocket.onmessage = function (arg) {
        console.log("success", arg.data);
        var obj = JSON.parse(arg.data);
        if (obj.inCar == undefined) {
            addOrder(obj);
        } else {
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
/**
 * latMap
 * @type {map["lat,lang"] = name}
 */
var latMap = {};
/**
 * holds current orderInfo
 */
var orderInfo;
geocoder = new google.maps.Geocoder();
geocoderNames = new google.maps.Geocoder();
function addOrder(ordr) {
    console.log("addOrder");
    orderInfo = ordr;
    console.log(orderInfo);
    var out = "";
    for (var i = 0; i < orderInfo.length; i++) {
        var start = orderInfo[i].start.latitude + "," + orderInfo[i].start.longitude;
        var end = orderInfo[i].end.latitude + "," + orderInfo[i].end.longitude;
        console.log(orderInfo[i]);
        if (latMap[start] == undefined) {
            geocodeQuery.push(start);
        }
        if (latMap[end] == undefined) {
            geocodeQuery.push(end);
        }
    }
    if (geocodeQuery.length > 0) {
        geocodeTimer = setInterval(function () {
            fetchGeocode();
        }, 1000);
    }
    if (orderInfo.length == 0) {
        $("#orderModalCont").html(generateModal(orderInfo));
        $("#orderModal").modal("hide");


        clearInterval(geocodeTimer);
    }
}
var geocodeQuery = [];
var geocodeTimer;
/**
 * asynchroniously fetches names with locations
 * pops geocodeQuery and fills up latMap
 */
function fetchGeocode() {
    if (geocodeQuery.length == 0) {
        $("#orderModalCont").html(generateModal(orderInfo));
        $("#orderModal").modal("show");
        clearInterval(geocodeTimer);
    }
    var start = geocodeQuery[geocodeQuery.length - 1];
    var arr = start.split(",");
    var startM = new google.maps.LatLng(arr[0], arr[1]);
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
/**
 * object size method
 * @param obj
 * @returns {number}
 */
function size(obj) {
    var size = 0, key;
    for (key in obj) {
        if (obj.hasOwnProperty(key)) size++;
    }
    return size;
}
/**
 * generates modal
 * @param orderInfo
 * @returns {string}
 */
function generateModal(orderInfo) {
    var out = "";
    for (var i = 0; i < orderInfo.length; i++) {
        out += '<div class="orderDiv">';
        out += '<span>' + orderInfo[i].user.firstName + " " + orderInfo[i].user.lastName + '</span>';
        out += '<span> requires your assistance in getting from:</span>';
        out += '<span>' + latMap[orderInfo[i].start.latitude + "," + orderInfo[i].start.longitude] + " to " + latMap[orderInfo[i].end.latitude + "," + orderInfo[i].end.longitude] + '</span>';
        out += '<span> and offers:' + orderInfo[i].maxPrice + '</span>';
        if (orderInfo[i].user.preference.wantsAlone) {
            out += '<span> He wants to travel alone</span>';
        }
        if (orderInfo[i].user.gender = "MALE") {
            out += '<span> And is currently with his ' + orderInfo[i].user.preference.passengersCount + ' friends</span>';
        } else {
            out += '<span> And is currently with her ' + orderInfo[i].user.preference.passengersCount + ' friends</span><br>';
        }
        out += '<span> and offers:' + orderInfo[i].maxPrice + '</span><br>';
        out += '<button onclick="acceptOffer(' + i + ')" class="special">Accept Offer</button><br>';
        out += '<button onclick="rejectOffer(' + i + ')" class="special">Reject Offer</button><br>';
        out += '</div>'
    }
    return out;
}
/**
 * rejects index'th offer from list (left top corner)
 * @param index
 */
function rejectOffer(index) {
    $.ajax({
        url: "/order",
        method: "post",
        data: {action: "driverReject", orderID: orderInfo[index].orderID, userID: orderInfo[index].user.userID},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(formData));
        }
    });
}
/**
 * rejects offer from user (orderInfo should be initialized)
 * @param index
 */
function revokeOffer(index) {
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action: "revokeOrderDriver", orderID: orderInfo[index].orderID, userID: orderInfo[index].user.userID},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(formData));
        }
    });
}
/**
 * orderInfo should be initialized
 * accept offer from user orderInfo[index]
 * @param index
 */
function acceptOffer(index) {
    console.log(orderInfo[index].orderID, orderInfo[index].user.userID);
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action: "driverAccept", orderID: orderInfo[index].orderID, userID: orderInfo[index].user.userID},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(formData));
        }
    });
}
/**
 * curRoute.route[index]
 * carryRoute(index) identifies current part of order as done and sends information to server
 * @param index
 */
function carryRoute(index) {
    var elem = curRoute.route[index];
    var action = "leaveUser";
    if (elem.pickUser) {
        action = "pickUser";
    }
    $.ajax({
        url: "/orderinfo",
        method: "post",
        data: {action: action, userID: elem.orderInfo.user.userID, orderID: elem.orderInfo.orderID},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error(data);
        }
    });
}
/**
 * resends email (verification)
 */
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
/**
 * send request to server in order to send new sms to phone of driver
 */
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
/**
 * creates preference listeners
 */
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
/**
 * generated grid containing driver history
 */
function generateGrid() {
    $("#grid").removeClass("hidden");
    $('#grid').w2grid({
        name: 'grid',
        header: 'Orders',
        show: {
            toolbar: false,
            footer: false
        },
        columns: [
            {field: 'recid', caption: 'orderID', size: '10%', sortable: true, attr: 'align=center'},
            {field: 'userID', caption: 'userID', size: '5%', sortable: true, resizable: false},
            {field: 'numPassengers', caption: 'numPassengers', size: '5%', resizable: true},
            {field: 'startLocation', caption: 'startLocation', size: '5%', resizable: true},
            {field: 'endLocation', caption: 'endLocation', size: '5%', resizable: true},
            {field: 'endTime', caption: 'endTime', size: '30%', resizable: true},
            {field: 'paymentAmount', caption: 'paymentAmount', size: '10%', resizable: true},
            {field: 'callTime', caption: 'callTime', size: '30%', resizable: true},
        ],
        searches: [
            {field: 'userID', caption: 'userID', type: 'text'},
            {field: 'orderID', caption: 'orderID', type: 'text'},
        ],
        sortData: [{field: 'orderID', direction: 'ASC'}],
        records: records,
        onLoad: function (event) {
            //because i am too lazy to read docs, hoping to make a difference
            console.log("grid loaded");
            $("#grid").addClass("hidden");//needs fixing somehow fucks grid up dunno why
        }
    });
}
/**
 * current route json
 * consists of .inCar and .route
 */
var curRoute;
/**
 * route marker
 * @type {google.maps.marker}
 */
var routeMarker = null;

/**
 * displays curRoute
 * fills nameQueue and triggers fetchNames
 * also sets nameTimer
 */
function defineRoute(route) {
    curRoute = route;
    directionsDisplay.setMap(null);
    displayRoute();
    var out = "";
    for (var i = 0; i < curRoute.route.length; ++i) {
        if (latMap[curRoute.route[i].loc.latitude + "," + curRoute.route[i].loc.longitude] == undefined) {
            namequeue.push(curRoute.route[i].loc.latitude + "," + curRoute.route[i].loc.longitude);
        }
    }
    nameTimer = setInterval(function () {
        fetchNames();
    }, 1000);
}
/**
 * displays curRoute
 */
function displayRoute() {
    if (routeMarker != null) {
        routeMarker.setMap(null);
    }
    if (curRoute.route.length == 0) return;
    var routeElem = curRoute.route[0];
    routeMarker = new google.maps.Marker({
        map: map,
        title: 'Taxi Map'
    });
    routeMarker.setPosition(new google.maps.LatLng(routeElem.loc.latitude + "", routeElem.loc.longitude + ""));
    console.log("displayRoute");
    while (curMarker == null) {
    }
    drawRoute(curMarker, routeMarker);
}
/**
 * used in drawRoute procedure
 * @type {google.maps.DirectionsRenderer}
 */
var directionsDisplay = new google.maps.DirectionsRenderer();
/**
 * draws directions on map fetches information from google directionservice
 * in order to use this service directionsDisplay should be initialized
 * @param marker1
 * @param marker2
 */
function drawRoute(marker1, marker2) {
    var request = {
        origin: marker1.position,
        destination: marker2.position,
        travelMode: google.maps.TravelMode.DRIVING
    };
    var directionsService = new google.maps.DirectionsService();
    directionsService.route(request, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        }
    });
    directionsDisplay.setMap(map);
}
var namequeue = [];
var nameTimer;
function fetchNames() {
    if (namequeue.length == 0) {
        $("#routeDiv").html(generateRouteDiv());
        clearInterval(nameTimer);
        return;
    }
    var start = namequeue[namequeue.length - 1];
    var arr = start.split(",");
    var startM = new google.maps.LatLng(arr[0], arr[1]);
    geocoderNames.geocode({'latLng': startM}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            if (results[1]) {
                latMap[start] = results[1].formatted_address;
                namequeue.pop();
                if (namequeue.length == 0) {
                    $("#routeDiv").html(generateRouteDiv());
                    clearInterval(nameTimer);
                }
            } else {
                latMap[start] = "unknown";
            }
        }
    });
}
/**
 * generates div with text from curRoute ready to display
 * @returns {string}
 */
function generateRouteDiv() {
    var out = "";
    for (var i = 0; i < curRoute.route.length; ++i) {
        if (curRoute.route[0].pickUser) {
            out += "<span> pick user </span>";
        }
        out += latMap[curRoute.route[i].loc.latitude + "," + curRoute.route[i].loc.longitude];
        out += "<button onclick='carryRoute(" + i + ")' class='special fa fa-check'>.</button><br>";
    }
    return out;
}