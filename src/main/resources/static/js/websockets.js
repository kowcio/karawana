        var stompClient = null;

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            //don`t clear the data after disconnect 
            //document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
            //document.getElementById('response').innerHTML = '';
        }

        function connect() {
            var socket = new SockJS('/hello');
            stompClient = Stomp.over(socket);
            console.log("Connecting to server.");
            stompClient.connect({}, function(frame) {
                setConnected(true);

                var name = document.getElementById('name').value;
                var sub = "/topic/greetings";

                console.log('Connected: ' + frame);
                console.log('subscribing to ' + sub);

                stompClient.subscribe(sub, function(callback){
                console.log("we subscribed to  " + sub +"\n the callback is = " + callback);
                console.log(callback);

//                	showWebSocketMsg(JSON.parse(callback).content);
                });
            });
        }
               function sendName() {
                    var name = document.getElementById('name').value;
                    var res = stompClient.send("/app/hello/"+name, {}, JSON.stringify({ 'name': name }));
                    console.log(res);


                }
//        function connectTest() {
//            var socket = new SockJS('/test');
//            stompClient = Stomp.over(socket);
//            console.log("Connecting to server.");
//            stompClient.connect({}, function(frame) {
//                setConnected(true);
//                console.log('Connected: ' + frame);
//                stompClient.subscribe('/test', function(string){
//                console.log("Resp - "+string);
//                	showWebSocketMsg(string.body);
//                });
//            });
//        }
        function disconnect() {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }



        function showWebSocketMsg(message) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.textContent=message;
            response.insertBefore(p, response.firstChild);
            response.appendChild(p);
        }
