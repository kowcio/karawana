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
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/greetings', function(greeting){
                	showWebSocketMsg(JSON.parse(greeting.body).content);
                });
            });
        }
        function connectTest() {
            var socket = new SockJS('/test');
            stompClient = Stomp.over(socket);
            console.log("Connecting to server.");
            stompClient.connect({}, function(frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/test', function(string){
                	showWebSocketMsg(string.body);
                });
            });
        }
        function disconnect() {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }

        function sendName() {
            var name = document.getElementById('name').value;
            stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
        }

        function showWebSocketMsg(message) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.textContent=message;
            response.insertBefore(p, response.firstChild);
            //response.appendChild(p);
        }
