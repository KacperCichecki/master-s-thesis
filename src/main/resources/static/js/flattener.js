$(document).ready(function(){
/*    let windoHeight = $(window).height();
    $('.header').height(windoHeight);*/


    // create indentation in result
    var elms = document.getElementById("nestedJson");
    if (elms) {
        let modifiedElms = elms.innerHTML.replace(/\s\s/gi, "<span id='indentation'></span>");
        document.getElementById("nestedJson").innerHTML = modifiedElms;
    }
});




