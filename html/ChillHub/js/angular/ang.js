var myApp = angular.module('chatroom', []);

myApp.controller('message_controller', function theController($scope) {
    $scope.chat_messages = [{
            "username": "John Davis",
            "message" : "default message"
        }
    ]

    $scope.addMessage = function(message) {
        $scope.chat_messages.push(message);
    };

    // triggers when the user presses enter while typing a message
    $scope.enter_keypress = function(e) {
        if (e.which == 13 && !e.shiftKey) {
            e.preventDefault();
            var message = { "username" : "default", "message": $("#textarea1").val() }
            $scope.addMessage(message);
            $('#textarea1').val('');
            $('#textarea1').trigger('autoresize');
        }
    };
});