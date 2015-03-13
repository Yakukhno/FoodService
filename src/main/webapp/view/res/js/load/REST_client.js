
function ManagerUserByID(id) {
    $.ajax({
        type: 'GET',
        url: '/FoodService/resources/users/manager/byId/' + id,
        dataType: 'json',
        success: function (response) {
            $('#profileFName').html(response.firstName);
            $('#profileLName').html(response.lastName);
            $('#profileEmail').html(response.email);
            $('#profileInfo').html(response.personalData);
        }
    });
}

function SimpleUserByID(id) {
    $.ajax({
        type: 'GET',
        url: '/FoodService/resources/users/simple/byId/' + id,
        dataType: 'json',
        success: function (response) {
            $('#profileFName').html(response.firstName);
            $('#profileLName').html(response.lastName);
            $('#profileEmail').html(response.email);
            $('#profileInfo').html(response.personalData);
        }
    });
}

function ManagerUserCreate() {
    var formData = {
        firstName: $('#firstName').val(),
        lastName: $('#lastName').val(),
        email: $('#email').val(),
        contactTelephone: $('#telephone').val(),
        password: $('#password').val(),
        personalData: $('#personalData').val()
    }

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/FoodService/resources/users/manager",
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
}

function SimpleUserCreate() {
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
        url: "/FoodService/resources/users/simple",
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
}

function ShopCreate(managerUserID) {

    var location = {
        building: $('#building').val(),
        street: $('#street').val(),
        city: $('#city').val(),
        country: $('#country').val(),
        zipcode: $('#zipCode').val()
    }

    var shop = {
        name: $('#shopName').val(),
        location: location,
        description: $('#description').val()
    }

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/FoodService/resources/shops?managerUserID=" + managerUserID,
        data: JSON.stringify(shop),
        dataType: "json",
        success: function() {
            alert("SUCCESS")
        },
        error: function () {
            alert("ERROR");
        }
    });
    return false;
    
}