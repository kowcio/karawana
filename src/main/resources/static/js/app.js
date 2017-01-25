/**
Initiate base function.
*/

//var x = document.getElementById("location");

$( document ).ready(function() {

console.log("Doc ready funcs. ");
initMap();
getLocation();
//init websockt

});


function initMap() {
 var map = new google.maps.Map(document.getElementById('map'), {
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


/**
        LOCATION for browser
*/

var x = document.getElementById("location");
function showPosition(position) {
    document.getElementById("location").innerHTML = "Latitude: " + position.coords.latitude +
    "<br>Longitude: " + position.coords.longitude;
}
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.watchPosition(showPosition);
    } else {
        document.getElementById("location").innerHTML = "Geolocation is not supported by this browser.";
    }
}


/**
        COUNTER
*/
var timestamp = $("countdown").text();
timestamp /= 1000;
$("#test").text(timestamp);

function component(x, v) {
    return Math.floor(x / v);
}
setInterval(function() { // execute code each second

    timestamp--; // decrement timestamp with one second each second

    var days    = component(timestamp, 24 * 60 * 60),      // calculate days from timestamp
        hours   = component(timestamp,      60 * 60) % 24, // hours
        minutes = component(timestamp,           60) % 60, // minutes
        seconds = component(timestamp,            1) % 60; // seconds

    $("#countdown").html(days + " days, HH" + hours + ":" + minutes + ":" + seconds); // display

}, 1000); // interval each second = 1000 ms



