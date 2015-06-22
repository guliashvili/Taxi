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
var randomdrivers = [{lat:41.732539, lang: 44.768887},{lat:41.721457, lang: 44.769402},{lat:41.732039, lang: 44.768587},{lat:41.732000, lang: 44.768187}];
function showPosition(position) {
	var mapOptions = { //your location
		center: {lat: position.coords.latitude, lng: position.coords.longitude},
		zoom: 13
	};
	map = new google.maps.Map(document.getElementById('map'), mapOptions);
	var marker = new google.maps.Marker({
      position: {lat: position.coords.latitude, lng: position.coords.longitude},
      map: map,
      title: 'Hello World!'
	});
	for (var i=0;i<randomdrivers.length;++i){
		var driver = new google.maps.Marker({
			position: {lat: randomdrivers[i].lat, lng: randomdrivers[i].lang},
			raiseOnDrag: true,
			map: map,
			icon: getIcon("\uf1ba"),
			labelContent: '<i class="fa fa-send fa-3x" style="color:rgba(153,102,102,0.8);"></i>'
		});
		driver.setMap(map);
	}
}
google.maps.event.addDomListener(window, 'load', initialize);
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