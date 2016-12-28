$(document).ready(function () {
    $('#messageForm').on('submit', function (event) {
        var msgLen = $('#message').val().length;
        if (msgLen < 1 || msgLen > 255) {
            var errorList = $('#errorList');
            if (errorList.children().length === 0) {
                var error = $('<li/>');
                error.text("the message needs to be between 1 and 255 characters")
                errorList.append(error);
            }
            event.preventDefault();
        }
    });
});

