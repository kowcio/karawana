var Group = function (group, position) {
    this.group = group;
    this.currentUserPosition = position;
};

Group.prototype.updateMyLocation = function () {
    var thisObj = this;
    var position = this.currentUserPosition;
    var dataToSend = {
        // userName: "testAjaxRequestUpdateToServerUser",
        // groupName: "groupTestFromFront",
        //     id: 6,
        //     userId: 1,
        lat: position.latitude,
        lng: position.longitude,
        // createdDate: "2019-03-03T03:03"
    };
    console.log(dataToSend);

    //$("#test").load("/api/updateMyLocation");

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
            for (var i = 0; i < users.length; i++) {
                $("#test").append("UserName:" + users[i].name + " L_ID:" + users[i].locations[0].id + "  " + " -Position:- " + users[i].locations[0].lat + " -- " + users[i].locations[0].lng + "<br />");

                console.log(users[i].locations[0].lat);
                console.log(users[i].locations[0].lng);

                mapx = users[i].locations[0].lat;
                mapy = users[i].locations[0].lat;

                // L.marker([users[i].locations[0].lat, users[i].locations[0].lng]).addTo(window.map);
                var latlngs  = new Array(2);

                for (var j = 0; j < users[i].locations.length; j++) {
                    latlngs[j] = [  users[i].locations[j].lat  ,   users[i].locations[j].lng  ];
                }
                var line = L.polyline(latlngs, {color: "#" + users[i].color, weight: 20}).addTo(window.map);
                window.map.fitBounds(line.getBounds());

            }

            // window.map.panTo([ users[i].locations[0].lat,users[i].locations[0].lng]);


            window.map.panTo([mapx, mapy]);

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
        data: this.group.password,
        contentType: 'application/json; charset=utf-8',
        async: true,
        success: function (response) {
            console.log(response);
            console.log("Got group update.");
        },
        error: function (response) {
            console.log("Error in saving Locationfor user.");
        }
    })
}


Group.prototype.changeGroup = function (groupName) {
    var thisObj = this;
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