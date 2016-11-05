
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 45.518, lng: -122.672},
    zoom: 18,
    mapTypeId: 'satellite',
    heading: 90,
    tilt: 45

  });
}


    $( document ).ready(function() {
console.log("Moading map .... ? ");
    initMap();


//init websockt



    });



