
/**

Initiate base function.

*/

    $( document ).ready(function() {
console.log("Moading map .... ? ");
    initMap();

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



