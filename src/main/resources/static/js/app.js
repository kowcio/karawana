/**
Initiate base function.
*/
var lat,lon,map;



function initMap() {
 window.map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 45.518, lng: -122.672},
    zoom: 18,
    mapTypeId: 'satellite',
    heading: 90,
    tilt: 45
  });
 var marker = new google.maps.Marker({
    position: {lat: 45.518, lng: -122.672},
    map: map,
    title: 'Test !',
     icon: {
            path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
            strokeColor: "red",
            scale: 3
        }
  });
}

function showPosition(position) {
     window.lat = parseFloat(position.coords.latitude);
     window.lon = parseFloat(position.coords.longitude);
         console.log(window.lat + " -x- " + window.lon);
                  console.log(lat + " -x- " + lon);
//       var myLatlng = new google.maps.LatLng(parseFloat(window.lat),parseFloat(window.lon));
     $("#location").text("Latitude: " + window.lat +    "   Longitude: " + window.lon);

}
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.watchPosition(showPosition);
    } else {
        $("#location").text("Geolocation is not supported by this browser.");
    }

}

function moveMap(){
    console.log(window.lat + " -ZZZ- " + window.lon);
    window.map.setCenter(new google.maps.LatLng( 54.4136058  , 18.5827882 ));
     var marker = new google.maps.Marker({
        position: {lat: 54.4136058, lng: 18.5827882},
        map: map,
        title: 'Test !',
         icon: {
                path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
                strokeColor: "red",
                scale: 3
            }
      });
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
getLocation();
console.log(window.lat + " -- " + window.lon);
console.log(lat + " -- " + lon);

moveMap();
//init websockt

});