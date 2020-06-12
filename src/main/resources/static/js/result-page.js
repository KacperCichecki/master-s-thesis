
$(document).ready(function () {
    setTimeout(function() {
        console.log("hide alert")
        $('#alert').hide();
    }, 5000);
});

function saveJson() {
    console.log("json saved")
}