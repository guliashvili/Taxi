/**
 * Created by Alex on 6/18/2015.
 */

// Add map element to the background.

function initialize() {
    var mapOptions = { //latitiude and longitude of freedom square
        center: {lat: 41.693353, lng: 44.801529},
        zoom: 13
    };
    //geolocation
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            console.log("aaa");
        });
    } else {
        //handle error
    }
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);
}

google.maps.event.addDomListener(window, 'load', initialize);