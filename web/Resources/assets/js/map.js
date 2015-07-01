/**
 * Created by Alex on 6/18/2015.
 */

// Add map element to the background.
var map;
var isUser;//if user
var startMarker = null;//for user
var endMarker = null;//for user
var askWindow = null;//for user
/**
 * initializes map
 * @param isUser1 true if for user
 */
function initializeMap(isUser1) {
	//geolocation
	isUser = isUser1;
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition);
	} else {
		var mapOptions = { //latitiude and longitude of freedom square
			center: {lat: 41.693353, lng: 44.801529},
			zoom: 13
		};
		map = new google.maps.Map(document.getElementById('map'), mapOptions);
	}
}
/**
 * testing pursposes
 * @type {*[]}
 */
var randomdrivers = [{lat: 41.732539, lang: 44.768887}, {lat: 41.721457, lang: 44.769402}, {
	lat: 41.732039,
	lang: 44.768587
}, {lat: 41.732000, lang: 44.768187}];
/**
 * updates Modal
 * @returns {string}
 */
function updateAsker(){
	var dateAsker = "<form id='mapOrder' style='background-color:#FFD800;margin:5px;padding:5px;'>"+$("#prefForm").html()+
		"<br></form><button id='addOrderM' onclick='addOrderJ()' class='special'> Add Order</button>";
	return dateAsker;
}
/**
 * marker for my current location
 * @type {google.maps.marker}
 */
var curMarker=null;
function showPosition(position) {
	var mapOptions = { //your location
		center: {lat: position.coords.latitude, lng: position.coords.longitude},
		zoom: 13
	};
	map = new google.maps.Map(document.getElementById('map'), mapOptions);
	curMarker = new google.maps.Marker({
		position: {lat: position.coords.latitude, lng: position.coords.longitude},
		map: map,
		title: 'Taxi Map'
	});
	if (isUser) {
		google.maps.event.addListener(map, 'click', function (event) {
			placeMarker(event.latLng);
		});
		var dateAsker = updateAsker();
		askWindow = new google.maps.InfoWindow({
			content: dateAsker
		});

	}
}
/**
 * markers for orders (clearing purposes)
 * @type {Array[google.maps.marker]}
 */
var orderMarkers = [];
function addOrder(orderInfo) {
	console.log("addOrder");
	console.log(orderInfo);
	while (orderMarkers.length > 0) {
		removeFromMap(orderMarkers.pop());
	}
	for (var i = 0; i < orderInfo.length; i++) {
		var start = orderInfo[i].start;
		console.log(orderInfo[i]);
		var end = orderInfo[i].end;

		var tmpMarker = new google.maps.Marker({
			map: map,
			draggable: true,
			animation: google.maps.Animation.DROP,
			position: new google.maps.LatLng(start.latitude, start.longitude)
		});
		var dateAsker = '';
		cont = new google.maps.InfoWindow({
			content: dateAsker
		});
		google.maps.event.addListener(tmpMarker, 'click', function () {
			openOrder(cont, tmpMarker);
		});
		orderMarkers.push(tmpMarker);
		tmpMarker.setAnimation(google.maps.Animation.BOUNCE);
	}
}
/**
 * opens order
 * @param cont
 * @param mark
 */
function openOrder(cont,mark){
	cont.open(map,mark);
}
/**
 * places marker for given location
 * @param loc
 */
function placeMarker(loc) {
	if (drivers != undefined && drivers.length > 0) return;
	if (startMarker == null) {
		startMarker = new google.maps.Marker({
			position: loc,
			map: map,
			draggable: true,
			animation: google.maps.Animation.DROP
		});
		google.maps.event.addListener(startMarker, 'dragend', function () {
			askForDate();
		});
		google.maps.event.addListener(startMarker, 'click', function () {
			askForDate();
		});
	} else if (endMarker == null) {
		endMarker = new google.maps.Marker({
			position: loc,
			map: map,
			draggable: true,
			animation: google.maps.Animation.DROP
		});
		google.maps.event.addListener(endMarker, 'dragend', function () {
			askForDate();
		});
		google.maps.event.addListener(endMarker, 'click', function () {
			askForDate();
		});
		askForDate();
	} else {
		askForDate();
	}
}
/**
 * gets car icon used for icons
 * @param glyph
 * @param color
 * @returns {string}
 */
function getIcon(glyph, color) {
	var canvas, ctx;
	canvas = document.createElement('canvas');
	canvas.width = 25;
	canvas.height = 20;
	ctx = canvas.getContext('2d');
	ctx.fillStyle = "#333300";
	ctx.font = '20px FontAwesome';
	ctx.fillText(glyph, 0, 16);
	return canvas.toDataURL();
}
/**
 * fetches map updates
 */
function updateMapLocations() {
	$.ajax({
		url: "/updateMap",
		method: "post",
		data: "",
		cache: false,
		success: function (data) {
			console.log(data);
		},
		error: function (data) {
			console.error("fetch Data");
		}
	});
}
/**
 * minimizes main div (window)
 */
function minimize(){
	$('.prefPanel').toggleClass('zero');$('#map').toggleClass('mOP');$('#panelToggle').toggleClass('hidden');
}
/**
 * sub procedure for pinpoint clears previous line and markers
 */
function clearPinPoint(){
	removeFromMap(travel);
	removeFromMap(marker1);
	removeFromMap(marker2);
}
function removeFromMap(obj){
	if(obj!=null){
		obj.setMap(null);
	}
}
var marker1=null;
var marker2=null;
var travel=null;
/**
 * pinpoints given place on map draws line between them
 * @param lat latitude of first point
 * @param lng longitude of first point
 * @param lat1 latitude of second point
 * @param lng1 longitude of second point
 * @returns {google.maps.Marker} first marker
 */
function pinpoint(lat,lng,lat1,lng1){
	console.log("pinpointing:"+lat+" "+lng+" "+lat1+" "+lng1+" ");
	minimize();
	var coords=[
		new google.maps.LatLng(lat, lng),
		new google.maps.LatLng(lat1, lng1)
	];
	travel=new google.maps.Polyline({
		path: coords,
		geodesic: true,
		strokeColor: '#FF0000',
		strokeOpacity: 1.0,
		strokeWeight: 2
	});
	travel.setMap(map);
	map.setCenter(new google.maps.LatLng(lat,lng));
	marker1 = new google.maps.Marker({
		position: new google.maps.LatLng(lat,lng),
		map: map,
		title: 'Start Position'
	});
	marker2 = new google.maps.Marker({
		position: new google.maps.LatLng(lat1,lng1),
		map: map,
		title: 'End Position'
	});
	google.maps.event.addListener(map, 'click', function (event) {
		removeFromMap(marker1);
		removeFromMap(marker2);
		removeFromMap(travel);
	});
	return marker1;
}