
$(document).ready(function () {
    setTimeout(function() {
        $('#alertToHide').removeClass("in");
    }, 2000);
});

function saveJson() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4){

            let result = $('#result');
            if (xhr.status === 200) {
                result.addClass("alert alert-success fade in");
                document.getElementById('result')
            } else {
                result.addClass("alert alert-danger fade in");
            }
            document.getElementById('result').innerText = xhr.response;
            document.getElementById('saveResultButton').style.display = "none";

            setTimeout(function() {
                result.removeClass("in");
            }, 2000);
        }
    };
    xhr.open('GET', 'save-json');
    xhr.send()

}