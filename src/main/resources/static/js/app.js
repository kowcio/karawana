/**
Initiate base function.
*/
var lat,lon,map,pos, iw;
//var window.pos;

$( document ).ready(function() {

window.isTest = "prod";

if(window.isTest=="prod"){
console.log("Init run for map creation.");
 window.map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 45.518, lng: -122.672},
    zoom: 16,
    mapTypeId: 'roadmap',
    heading: 90,
    tilt: 45
  });
window.iw = new google.maps.InfoWindow({map: window.map});
}

////musi byc w onload doc
//$( "#changePosition" ).click( changePosition );
//function changePosition(){
//console.log("Moved position from " + window.pos.lat);
//window.pos.lat+=0.001;
//console.log("Moved position to " + window.pos.lat);
//};
//test model map access
console.log("Test start");
console.log(window.group);
console.log(window.pos);
//var group = new Group(window.group,window.pos);
//console.log(group);
//group.updateMyLocation();
//console.log(window.group.id);
//console.log(window.group.users);
//console.log(window.group.createdDate);



$( "#groupChangeSubmit" ).click(function() {
var newGroupName = $("#groupName").val();
var group = new Group(window.group,window.pos);
console.log("Changing group name to = "+ newGroupName);
group.changeGroup(newGroupName);

});





}); //END DOCUMENT ON LOAD



setInterval(initMap,7000);
function initMap() {
console.log("Update Map func - " + window.isTest );

        if (navigator.geolocation) {
//        navigator.geolocation.watchPosition(function(position) {
          navigator.geolocation.getCurrentPosition(function(position) {
          if (window.isTest == "test"){
           window.pos.lng = window.pos.lng  +  0.0003;
           window.pos = {
              lat: window.pos.lat,
              lng: window.pos.lng
            };
            }
            if (window.isTest == "prod"){
           window.pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
            }
          if (window.isTest == "prod")
                window.isTest = "test";

            if (window.pos != "undefined"){
            var group = new Group(window.group,window.pos);
            console.log("Position : " + JSON.stringify(window.pos));
            console.log(group.currentUserPosition);
            group.updateMyLocation();
            console.log("Updating map position to Lat:"+pos.lat+" Lng:"+pos.lng);
}

            var marker = new google.maps.Marker({
            position: window.pos,
            map:window.map,
             icon: {
                        path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
                        strokeColor: '#457803',
                        scale: 3
                    }
            });
//            infoWindow.setPosition(pos);
//            infoWindow.setContent('Twoja lokalizacja.');
//            + window.pos.lat + " - " + window.pos.lng   );
            map.setCenter   (pos);
          }, function(failure) {
          console.log("Error.");
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




















