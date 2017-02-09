
var Group = function(group, position){
this.group = group;
this.currentUserPosition = position;
};

Group.prototype.updateMyLocation = function() {
    var position = this.currentUserPosition;
    console.log("Updating user location = " + JSON.stringify(position));
    $.ajax({
        url: '/api/updateMyLocation',
        type: 'POST',
        data: JSON.stringify( position),
        contentType: 'application/json; charset=utf-8',
        async: true,
        success: function(msg) {
            console.log(msg);
            console.log(msg);
        var users = msg.users

        for (var i = 0 ; i < users.length ; i++) {
          $("#log").append(users[i].id+" -- "+users[i].locations[i].lat+"<br />");
          }
        }

//        $("#log").append(msg+"<br />");
//        console.log(msg);
        })
    }



Group.prototype.getGroupLocation = function() {
    var position = this.currentUserPosition;
    console.log("Updating user location = " + JSON.stringify(position));

    $.ajax({
        url: '/api/getGroupLocation'+this.group.groupName,
        type: 'GET',
        data: this.group.password,
        contentType: 'application/json; charset=utf-8',
        async: false,
        success: function(response) {
        console.log(response);
        console.log("Got group update.");
        },
        error : function(response){
        console.log("Error in saving Locationfor user.");
        }
        })
}

Group.prototype.updateMapGroupLocations = function() {


}

Group.prototype.changeGroup = function(groupName) {

    $.ajax({
        url: '/api/changeGroup/'+groupName,
        type: 'GET',
        data: groupName,
        contentType: 'application/json; charset=utf-8',
        async: false,
        success: function(response) {
        this.group = response;
        console.log(response);
        $("#groupId").text(response.id);
        $("#userId").text(user.id);
        $("#groupNameSh").text(response.groupName]);
        console.log("Got new group update.");
        },
        error : function(response){
        console.log("Error in updating group for user.");
        }
        })


}