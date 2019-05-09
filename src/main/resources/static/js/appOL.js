/**
 Initiate base function.
 */

var lat, lng, map, pos, iw, latlngs;

$(document).ready(function () {

    window.map = L.map("map").setView([37.75, -122.23], 10);
    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
        // maxZoom: 15,
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
        '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
        'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        id: 'mapbox.streets'
    }).addTo(window.map);

});

setInterval(initMap, 5000);
function initMap() {
    console.log("Update Map func - " + window.isTest);

    var x = document.getElementById("userFrontTestLocation");
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
    function showPosition(position) {
        x.innerHTML = "Latitude: " + position.coords.latitude +
            "<br>Longitude: " + position.coords.longitude;
    }

    window.isTest = "test";
    if (navigator.geolocation) {
//        navigator.geolocation.watchPosition(function(position) {
        navigator.geolocation.getCurrentPosition(function (position) {

            // var polyline = L.polyline(window.latlngs, {color: 'red'}).addTo(map);
            // window.map.panTo([window.pos.lat, window.pos.lng]);
            // L.marker([window.pos.lat, window.pos.lng]).addTo(window.map);

            if (window.pos != "undefined") {
                var group = new Group(window.group, position.coords);
                group.updateMyLocation();
            }


//            var marker = new google.maps.Marker({
//            position: window.pos,
//            map:window.map,
//             icon: {
//                        path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
//                        strokeColor: '#'+window.user.color,
//                        scale: 3
//                    }
//            });


//            infoWindow.setPosition(pos);
//            infoWindow.setContent('Twoja lokalizacja.');
//            + window.pos.lat + " - " + window.pos.lng   );
//             map.setCenter(pos);
        }, function (failure) {
            console.log(failure);
            $("#test").text("Browser not suporting geo, user denied or unsecure location = NOT HTTPS");
//            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        $("#test").text("Browser not suporting geo.");
//          handleLocationError(false, infoWindow, map.getCenter());
    }
//CHECK IT AFTER
//        map = new google.maps.Map(document.getElementById("map_canvas"), map.options);
//    TestMarker();
}


//function updateMap(location){
//    console.log("addMarker new position");
//    window.pos.lng+=0.001;
//    console.log("window.pos.longitude = " + window.pos.lng);
//    console.log("window.pos.longitude = " + window.pos.lat);
//  var marker = new google.maps.Marker({
//    position: {lat: parseFloat(window.pos.lat) ,
//               lng: parseFloat(window.pos.lng)
//              },
//    map: window.map,
//    title: 'Test !',
//    icon: {
//            path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
//            strokeColor: "red",
//            scale: 3
//        }
//  });
//};


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


//
//var Group = function(group, position){
//this.group = group;
//this.currentUserPosition = position;
//};
//
//Group.prototype.updateMyLocation = function() {
//    var position = this.currentUserPosition;
//    console.log("Updating user location = " + JSON.stringify(position));
//    $.ajax({
//        url: '/api/updateMyLocation',
//        type: 'POST',
//        data: JSON.stringify( {lat:54.4136058,lng:18.613688199999928}),
//        contentType: 'application/json; charset=utf-8',
//        async: true,
//        success: function(msg) {
//        console.log("Position of user updated.");
//        }
//    });
//};




















