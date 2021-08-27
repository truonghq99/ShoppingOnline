src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"

$("#button").click(function(event) {
    event.preventDefault();
    $('.canEdit').prop("disabled", false);
});