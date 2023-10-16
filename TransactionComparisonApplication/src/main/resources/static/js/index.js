$( window ).ready(function() {
  connect();
});

function connect() {
  var socket = new SockJS('/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
      stompClient.subscribe('/topic/pushResult', function (notification) {
      console.log(JSON.parse(notification.body));
      notification=JSON.parse(notification.body);
         console.log(notification.transactionId);
             $("#transactionId").text(notification.transactionId);
    $("#comparisonStatus").text(notification.comparisonStatus);
    $("#originalAmount").text(notification.originalAmount);
    $("#receivedAmount").text(notification.receivedAmount);
       });
  });
}