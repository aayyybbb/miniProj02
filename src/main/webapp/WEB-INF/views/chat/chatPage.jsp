<%--
  Created by IntelliJ IDEA.
  User: y00nb
  Date: 2024-07-22
  Time: 오후 6:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat Application</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script>
        var stompClient = null;

        function connect() {
            var socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/messages', function (message) {
                    showMessage(JSON.parse(message.body));
                });
            });
        }

        function sendMessage() {
            var message = document.getElementById('message').value;
            stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(message));
        }

        function showMessage(message) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.appendChild(document.createTextNode(message));
            response.appendChild(p);
        }

        window.onload = connect;
    </script>
</head>
<body>
    <div>
        <input type="text" id="message" placeholder="Enter your message here" />
        <button onclick="sendMessage()">Send</button>
    </div>
    <div id="response"></div>
</body>
</html>
