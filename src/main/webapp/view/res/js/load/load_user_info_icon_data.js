function getUserInfoIconData(id) {
    alert("gettingUserInfo")
    $.ajax({
        type: 'GET',
        url: '/FoodService/resources/user/manager/byId/' + id + '/attributes',
        dataType: 'json',
        success: function (response) {
            $('#fNameIconData').html(response.firstName);
            $('#lNameIconData').html(response.lastName);
            $('#emailIconData').html(response.email);
        }
    });
}