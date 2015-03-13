$(function() {
    $('#submitSimpleUserForm').click(function (e) {

        var formData = {
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            personalData: $('#personalData').val()
        }

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/FoodService/resources/user/simple",
            data: JSON.stringify(formData),
            dataType: "json",
            success: function() {
                alert("SUCCESS")
            },
            error: function () {
                alert("ERROR");
            }
        });
        return false;
    });
});

