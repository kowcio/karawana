var Group = function (group, position) {
    this.group = group;
    this.currentUserPosition = position;
};

Group.prototype.updateMyLocation = function () {
    var position = this.currentUserPosition;
    var dataToSend = {
        // userName: "testAjaxRequestUpdateToServerUser",
        // groupName: "groupTestFromFront",
        //     id: 6,
        //     user_id: 1,
        lat: position.latitude,
        lng: position.longitude,
    };
    console.log(dataToSend);

    $.ajax({
        url: '/api/updateMyLocation',
        type: 'POST',
        data: JSON.stringify(dataToSend),
        contentType: 'application/json; charset=utf-8',
        headers: {
            'Accept': "application/json;charset=UTF-8",
            'Content-Type': "application/json;charset=UTF-8"
        },
        async: true,
        success: function (msg) {
            //gere we could return a rendered whole ajax html element in controller, no variables
            var users = msg.users;
            var group = msg;
            var mapx, mapy;

//clearing the map from all the layers so we can add from scratch the latest 10 locations per user
            for (i in window.map._layers) {
                if (window.map._layers[i]._path != undefined) {
                    try {
                        window.map.removeLayer(window.map._layers[i]);
                    }
                    catch (e) {
                        console.log("problem with " + e + window.map._layers[i]);
                    }
                }
            }
            mapx = position.latitude;
            mapy = position.longitude;
            var items = [mapx, mapy];

            for (var i = 0; i < users.length; i++) {
                $("#test").append("UserName:" + users[i].name + " L_ID:" + users[i].locations[0].id + "  " + " -Position:- " + users[i].locations[0].lat + " -- " + users[i].locations[0].lng + "<br />");
                // L.marker([users[i].locations[0].lat, users[i].locations[0].lng]).addTo(window.map);
                var latlngs = [];//  = new Array(10);
                for (var j = 0; j < users[i].locations.length; j++) {
                    var items = [  users[i].locations[j].lat  ,   users[i].locations[j].lng  ];
                    items = [users[i].locations[j].lat, users[i].locations[j].lng];
                    latlngs.push(items);
                    // console.log(j + ":" + latlngs[j]);
                    // if(j == users[i].locations.length-1)
                    // var popup = L.popup()
                    //     .setLatLng([mapx, mapy])
                    //     .setContent('<p>Hello'+users[i].name +'</p>Locs:'+users[i].locations.length+'User:'+1+'/'+users.length)
                    //     .openOn(map)
                    //     .update();
                }
                var line = L.polyline(latlngs, {color: "#" + users[i].color, weight: 2}).addTo(window.map);
                // line.addLatLng(items);//adding latlng and redraw the line
                line.setLatLngs(latlngs);
                // lines.push(line);
                // window.map.fitBounds(line.getBounds());


            }
            // window.map.panTo([ users[i].locations[0].lat,users[i].locations[0].lng]);
            // window.map.flyTo([position.latitude,position.longitude]);
            window.map.flyTo(items);
            this.group = group
            // thisObj.showLatestMarker();
            // thisObj.showUsers();


        }

    })
}
Group.prototype.showLatestMarker = function () {
    var users = this.group.users;
    for (var i = 0; i < users.length; i++) {
        console.log(users[i]);
        var lastLoc = users[i].locations.length - 1;
        var lat = parseFloat(users[i].locations[lastLoc].lat) + 0.0003;
        var lng = parseFloat(users[i].locations[lastLoc].lng) + 0.0003;
        var marker = new google.maps.Marker({
            position: {lat: lat, lng: lng},
            map: window.map,
            icon: {
                path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
                strokeColor: '#' + users[i].color,
                scale: 3
            }
        });

    }

}


Group.prototype.getGroupLocation = function () {
    var position = this.currentUserPosition;
    console.log("Updating user location = " + JSON.stringify(position));
    $.ajax({
        url: '/api/getGroupLocation' + this.group.groupName,
        type: 'GET',
        // data: this.group.password,
        contentType: 'application/json; charset=utf-8',
        async: true,
        success: function (response) {
            console.log("Got new group update and changed session variables.");
            console.log(response);

        },
        error: function (response) {
            console.log("Error in saving Locationfor user.");
        }
    })
}


Group.prototype.changeGroup = function () {
    var groupName = $("#groupName").text();
    console.log("GettingGroupName " + groupName)
    $.ajax({
        url: '/api/changeGroup/' + groupName,
        type: 'GET',
        data: groupName,
        contentType: 'application/json; charset=utf-8',
        async: true,
        success: function (response) {
            this.group = response;
            console.log(response);
            $("#groupId").text(response.id);
            $("#groupNameSh").text(response.groupName);
            console.log("Got new group update.");
        },
        error: function (response) {
            console.log("Error in updating group for user.");
        }
    })


}


Group.prototype.showUsers = function () {
    var users = this.group.users;
    var html = "";
    for (var i = 0; i < users.length; i++) {
        var date = localDateTimePrint(users[i].createdDate);
        html += "<label class='label label-danger'>User: </label>";
        html += "<div class='notTransparent' style='color:#" + users[i].color + "'>" + users[i].name + "" + date + "</div>"
    }
    $("#users").html(html);

}

function localDateTimePrint(date) {
    var dateString = " ";
    dateString += "Date:";
    dateString += date.hour + ":";
    dateString += date.minute + ", ";
    dateString += date.dayOfMonth + "-";
    dateString += date.month + "-";
    dateString += date.year

    return dateString;
}