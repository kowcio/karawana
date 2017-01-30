/**
Initiate base function.
*/
var lat,lon,map,pos;

function initMap() {

 window.map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 45.518, lng: -122.672},
    zoom: 18,
    mapTypeId: 'satellite',
    heading: 90,
    tilt: 45
  });

        var infoWindow = new google.maps.InfoWindow({map: map});

        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
           window.pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
            infoWindow.setPosition(pos);
            infoWindow.setContent('Twoja lokalizacja.'
            + window.pos.lat + " - " + window.pos.lng   );
            map.setCenter(pos);
          }, function() {
          console.log("Error.");
            handleLocationError(true, infoWindow, map.getCenter());
          });
        } else {
          // Browser doesn't support Geolocation
          handleLocationError(false, infoWindow, map.getCenter());
        }

//CHECK IT AFTER
        map = new google.maps.Map(document.getElementById("map_canvas"), map.options);
    TestMarker();

}

function addMarker(location){
    console.log("addMarker");

  marker = new google.maps.Marker({
    position: location,
    map: map,
    title: 'Test !',
     icon: {
            path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
            strokeColor: "red",
            scale: 3
        }
  });

};
    function TestMarker() {
           CentralPark = new google.maps.LatLng(window.pos);
           addMarker(CentralPark);
    };


/**
        COUNTER
*/
//var timestamp = $("countdown").text();
//timestamp /= 1000;
//$("#test").text(timestamp);
//function component(x, v) {
//    return Math.floor(x / v);
//}
//setInterval(function() { // execute code each second
//    timestamp--; // decrement timestamp with one second each second
//    var days    = component(timestamp, 24 * 60 * 60),      // calculate days from timestamp
//        hours   = component(timestamp,      60 * 60) % 24, // hours
//        minutes = component(timestamp,           60) % 60, // minutes
//        seconds = component(timestamp,            1) % 60; // seconds
//    $("#countdown").html(days + " days, HH" + hours + ":" + minutes + ":" + seconds); // display
//}, 1000); // interval each second = 1000 ms



$( document ).ready(function() {
initMap();
//getLocation();
console.log(window.lat + " -- " + window.lon);
console.log(lat + " -- " + lon);

//moveMap();
//init websockt

});