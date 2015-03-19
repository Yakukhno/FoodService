
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

//returns List of Shops
/**
 * @return {null}
 */
function ShopsByManagerUserID(managerId) {

    $.ajax({
        type: "GET",
        url: "/FoodService/resources/shops/byManagerID/" + managerId,
        dataType: 'json',
        success: function(results) {
            $.each(results, function() {
                $("#shopContainer").append(
                    $('<div id="shopRight">' +
                    '<div class="shopText">' +
                    '<a href="/FoodService/view/public/service.jsp" class="shopRightName">' + this.name + '</a>' +
                    '<p class="smallText">' + this.location.city + ', ' + this.location.street + ' ' + this.location.building + '</p></div>' +
                    '<div class="shopPhoto">' +
                    '<img src="/FoodService/view/res/images/mcdonalds.jpeg" width="40px">' +
                    '</div></div>'));
            });
            return results;
        },
        error: function () {
            alert("ERROR");
            return null;
        }
    });
    return null;

}

function ShopsByCriterion() {

    var nameLike = $('#searchName').val();
    var countryLike = $('#searchCountry').val();
    var cityLike = $('#searchCity').val();
    var streetLike = $('#searchStreet').val();
    var buildingLike = $('#searchBuilding').val();
    var minRating = $('#searchMinRating').val();
    var maxRating = $('#searchMaxRating').val();

    var url = "/FoodService/resources/shops/byCriterion";

    var count = 0;
    if(nameLike != "") {url += ((count==0?"?":"&") + "nameLike=" + nameLike); count++}
    if(minRating != "") {url += ((count==0?"?":"&") + "minRating=" + minRating); count++}
    if(maxRating != "") {url += ((count==0?"?":"&") + "maxRating=" + maxRating); count++}
    if(countryLike != "") {url += ((count==0?"?":"&") + "countryLike=" + countryLike); count++}
    if(cityLike != "") {url += ((count==0?"?":"&") + "cityLike=" + cityLike); count++}
    if(streetLike != "") {url += ((count==0?"?":"&") + "streetLike=" + streetLike); count++}
    if(buildingLike != "") {url += ((count==0?"?":"&") + "buildingLike=" + buildingLike); count++}

    $.ajax({
        type: "GET",
        url: url,
        dataType: 'json',
        success: function(results) {
            var bar = document.getElementById("centralBar");
            $.each(results, function() {
                $("#resultShopList").append(
                    $('<div id="shop">' +
                    '<h3><a href="/FoodService/view/public/service.jsp">' + this.name + '</a></h3>' +
                    '<div id="shopText">' +
                    '<p class="middleText">' + this.location.city + ', ' + this.location.street + ' ' + this.location.building + '</p>' +
                    '<p><b>Description</b> : ' + this.description + '</p></div>' +
                    '<div id="shopImage">' +
                    '<img height="130px" src="/FoodService/view/res/images/mcdonalds.jpeg"/></div>'));
            });
            //alert(results);
            return results;
        },
        error: function () {
            //alert("ERROR");
            return null;
        }
    });
}
