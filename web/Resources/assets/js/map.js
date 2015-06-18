/**
 * Created by Alex on 6/18/2015.
 */

// Add map element to the background.
var map;
function initialize() {
    //geolocation
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
function showPosition(position) {
	var mapOptions = { //your location
		center: {lat: position.coords.latitude, lng: position.coords.longitude},
		zoom: 13
	};
	map = new google.maps.Map(document.getElementById('map'), mapOptions);
}

google.maps.event.addDomListener(window, 'load', initialize);