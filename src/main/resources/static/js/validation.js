$(document).ready(function () {
    $('#messageForm').on('submit', function (event) {
        var msgLen = $('#message').val().length;
        var errorList = $('#errorList');
        var nameError = "the message needs to be between 1 and 255 characters";
        if (msgLen < 1 || msgLen > 255) {
            error(errorList, nameError, event);
        }
    });

    $('#new-event-form').on('submit', function (event) {eventValidation(event);});
    $('#event-edit-form').on('submit', function (event) {eventValidation(event);});

});

function eventValidation(event) {
    var titleLen = $('#title').val().length;
    var titleMin = 1;
    var titleMax = 40;
    var placeLen = $('#place').val().length;
    var placeMax = 40;
    var descLen = $('#description').val().length;
    var descMax = 200;
    var errorStrTitle = "the title needs to be between " + titleMin + " and " + titleMax + " characters";
    var errorListTitle = $('#error-title');
    var errorStrPlace = "the place needs to be shorter than " + placeMax + " characters";
    var errorListPlace = $('#error-place');
    var errorStrDesc = "the description needs to be shorter than " + descMax + " characters";
    var errorListDesc = $('#error-description');
    if (titleLen < titleMin || titleLen > titleMax) {
        error(errorListTitle, errorStrTitle, event);
    } else {
        errorListTitle.empty();
    }
    if (placeLen > placeMax) {
        error(errorListPlace, errorStrPlace, event);
    } else {
        errorListPlace.empty();
    }
    if (descLen > descMax) {
        error(errorListDesc, errorStrDesc, event);
    } else {
        errorListDesc.empty();
    }
}

function error(errorList, errorStr, event) {
    if (errorList.children().length === 0) {
        var error = $('<li/>');
        error.text(errorStr);
        errorList.append(error);
    }
    event.preventDefault();
}
