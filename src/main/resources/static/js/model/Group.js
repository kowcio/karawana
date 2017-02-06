
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
        console.log("Position of user updated.");
        }
    });
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
        success: function(msg) {
        console.log("Got group update.");
        }
    });
}