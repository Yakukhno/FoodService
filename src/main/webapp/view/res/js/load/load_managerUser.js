
function getContent(id) {
    $.ajax({
        type: 'GET',
        url: '/FoodService/resources/user/manager/byId/' + id,
        dataType: 'json',
        success: function (response) {
            $('#profileFName').html(response.firstName);
            $('#profileLName').html(response.lastName);
            $('#profileEmail').html(response.email);
            $('#profileInfo').html(response.personalData);
        }
    });
}
