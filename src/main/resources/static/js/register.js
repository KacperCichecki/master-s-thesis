$(document).ready(function () {

    $(":password").keyup(function () {
        if ($("#password").val() != $("#matchPassword").val()) {
            $("#globalError").show().html("Password does not match!");
        } else {
            $("#globalError").html("").hide();
        }
    });

    options = {
        common: {minChar: 8},
        ui: {
            showVerdictsInsideProgressBar: true,
            showErrors: true,
            errorMessages: {
                wordLength: "Your password is too short",
                wordNotEmail: "Do not use your email as your password",
                wordSequences: "Your password contains sequences",
                wordLowercase: "Use lower case characters",
                wordUppercase: "Use upper case characters",
                wordOneNumber: "Use numbers",
                wordOneSpecialChar: "Use special characters"
            }
        }
    };
    $('#password').pwstrength(options);

});


