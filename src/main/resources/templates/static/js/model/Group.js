
var Group = function(group, position){
this.group = group;
this.currentUserPosition = position;
};

Group.prototype.updateMyLocation = function() {
    var thisObj = this;
    var position = this.currentUserPosition;
    $.ajax({
        url: '/api/updateMyLocation',
        type: 'POST',
        data: JSON.stringify( position),
        contentType: 'application/json; charset=utf-8',
        async: true,
        success: function(msg) {
        //gere we could return a rendered whole ajax html element in controller, no variables
        var users = msg.users
        thisObj.group = msg;
        console.log("Users");
        console.log(users);
        for (var i = 0 ; i < users.length ; i++) {
          $("#log").append("UserName:"+users[i].name+" -Position:- "+users[i].locations[0].lat+" -- "+users[i].locations[0].lng+"<br />");
          }

         thisObj.showLatestMarker();
         thisObj.showUsers();

        }

        })
    }
Group.prototype.showLatestMarker = function() {
        var users = this.group.users;
        for (var i = 0 ; i < users.length ; i++) {
        console.log(users[i]);
            var lastLoc = users[i].locations.length-1;
            var lat = parseFloat(users[i].locations[lastLoc].lat)+0.0003;
            var lng = parseFloat(users[i].locations[lastLoc].lng)+0.0003;
                    var marker = new google.maps.Marker({
                    position: {lat:lat,lng:lng},
                    map:window.map,
                     icon: {
                                path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
                                strokeColor: '#'+users[i].color,
                                scale: 3
                            }
                    });

          }

}


Group.prototype.getGroupLocation = function() {
    var position = this.currentUserPosition;
    console.log("Updating user location = " + JSON.stringify(position));
    $.ajax({
        url: '/api/getGroupLocation'+this.group.groupName,
        type: 'GET',
        data: this.group.password,
        contentType: 'application/json; charset=utf-8',
        async: true,
        success: function(response) {
        console.log(response);
        console.log("Got group update.");
        },
        error : function(response){
        console.log("Error in saving Locationfor user.");
        }
        })
}


Group.prototype.changeGroup = function(groupName) {
var thisObj = this;
    $.ajax({
        url: '/api/changeGroup/'+groupName,
        type: 'GET',
        data: groupName,
        contentType: 'application/json; charset=utf-8',
        async: true,
        success: function(response) {
        this.group = response;
        console.log(response);
        $("#groupId").text(response.id);
        $("#groupNameSh").text(response.groupName);
        console.log("Got new group update.");
        },
        error : function(response){
        console.log("Error in updating group for user.");
        }
        })


}



Group.prototype.showUsers = function() {
    var users = this.group.users;
    var html="";
        for (var i = 0 ; i < users.length ; i++) {
        var date = localDateTimePrint(users[i].createdDate);
        html += "<label class='label label-danger'>User: </label>";
        html += "<div class='notTransparent' style='color:#"+users[i].color+"'>"+users[i].name+"" + date +"</div>"
          }
    $("#users").html(html);

}

function localDateTimePrint(date){
var dateString=" ";
    dateString += "Date:";
    dateString += date.hour+":";
    dateString += date.minute+", ";
    dateString += date.dayOfMonth+"-";
    dateString += date.month+"-";
    dateString += date.year

return dateString;
}