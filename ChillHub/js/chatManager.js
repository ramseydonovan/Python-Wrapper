$(document).ready(function() {

    //------- chat text box for seding messages to group chat area ---------

    // var sendMessage = function() {
    //     $("#chat-display").append("<p>" + $("#textarea1").val() + "</p>");
    //     $('#textarea1').val('');
    //     $('#textarea1').trigger('autoresize');
    // };

    // //Event for sending a chat message when send button is clicked
    // $("#send-chat-message").click(sendMessage);

    // // triggers when the user presses enter while typing a message
    // $('#textarea1').keypress(function(e) {
    //     if (e.keyCode == 13 && !e.shiftKey) {
    //     	e.preventDefault();
    //         sendMessage();
    //     }
    // });
    // -----------------------------------------------------------------------

    // ask for group name and then chnage it
    $("#group-name").click(function() {
        //.addEventListener("change", addActivityItem, false);
        $('#dropdown1').append('<li><textarea id="change-group-name-text-area"></textarea></li>')

        //need to make this stay open and either add a button to fire the next event or make enter
        // or i could replace LOGO with a text box  and have them enter it there
    });

    // triggers when the user presses enter
    $('#group-name').keypress(function(e) {
        if (e.keyCode == 13 && !e.shiftKey) {
            e.preventDefault();
            $(this).blur();
        }
    });

    // ask for group name and then chnage it
    $("#change-group-name-text-area").change(function() {
        $('#group-name').text($(this).val());
        $('textarea').autoResize();
    });

});