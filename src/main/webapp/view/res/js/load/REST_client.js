
function ShopAdminUserByID(id) {
    $.ajax({
        type: 'GET',
        url: '/FoodService/resources/users/admin/byId/' + id,
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

function ShopAdminUserCreate() {
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
        url: "/FoodService/resources/users/admin",
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

function ManagerUserCreate() {
    var formData = {
        firstName: $('#firstName').val(),
        lastName: $('#lastName').val(),
        //manager is not accepted by shopAdmin user yet
        state: "NEW",
        email: $('#email').val(),
        password: $('#password').val(),
        personalData: $('#personalData').val()
    }

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/FoodService/resources/users/manager?shopAdminUserEmail=" + $('#shopAdminUserEmail').val(),
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

function ManagerUserChangeState(ManagerID, state) {
    $.ajax({
        type: "PUT",
        contentType: "application/json; charset=utf-8",
        url: "/FoodService/resources/users/manager/state/" + ManagerID + "?state=" + state,
        success: function() {
            alert("State is changed")
        },
        error: function () {
            alert("ERROR");
        }
    });
    return false;
}


function ManagerUsersGetByShopAdminID(shopAdminID) {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: "/FoodService/resources/users/manager/byShopAdminID?shopAdminID=" + shopAdminID,
        success: function(results) {
            $.each(results, function() {
                $("#managerContainer").append(
                    $('<div id="managerRight">' +
                    '<div class="managerText">' +
                    '<a href="/FoodService/view/public/service.jsp" class="shopRightName">' + this.firstName + ' ' + this.lastName + '</a>' +
                    '<p class="smallText">' + this.systemStatus + '</p></div>' +
                    '<div class="shopPhoto">' +
                    '<img id="thumbnailShop"  width="40px" height="40px">' +
                    '</div></div>'));

                var shop = jQuery.parseJSON(this.photo.image);
                for (var key in shop) {
                    //the results is a base64 string.  convert it to an image and assign as 'src'
                    document.getElementById("thumbnailShop").src = shop[key];
                }
            });
        },
        error: function () {
            alert("ERROR");
        }
    });
    return false;
}


function previewFile() {
    var file    = document.querySelector('input[type=file]').files[0];
    var reader  = new FileReader();

    return reader.result
}

function ShopCreate(shopAdminUserID) {

    var location = {
        building: $('#building').val(),
        street: $('#street').val(),
        city: $('#city').val(),
        country: $('#country').val(),
        zipcode: $('#zipCode').val()
    }

    //var img = new Image();
    //img.src = $('#photoShop').val();
    var photo = {
        image: previewFile()
    }

    var shop = {
        name: $('#shopName').val(),
        location: location,
        photo: photo,
        description: $('#description').val()
    }

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/FoodService/resources/shops?shopAdminUserID=" + shopAdminUserID,
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
function ShopsByShopAdminUserID(shopAdminID) {

    $.ajax({
        type: "GET",
        url: "/FoodService/resources/shops/byShopAdminID/" + shopAdminID,
        dataType: 'json',
        success: function(results) {
            $.each(results, function() {
                $("#shopContainer").append(
                    $('<div id="shopRight">' +
                    '<div class="shopText">' +
                    '<a href="/FoodService/view/public/service.jsp" class="shopRightName">' + this.name + '</a>' +
                    '<p class="smallText">' + this.location.city + ', ' + this.location.street + ' ' + this.location.building + '</p></div>' +
                    '<div class="shopPhoto">' +
                    '<img id="thumbnailShop"  width="40px" height="40px">' +
                    '</div></div>'));

                var shop = jQuery.parseJSON(this.photo.image);
                for (var key in shop) {
                    //the results is a base64 string.  convert it to an image and assign as 'src'
                    document.getElementById("thumbnailShop").src = shop[key];
                }
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

/**
 * Returns system statuses for all users with present identifiers in
 * {@code identifiers}
 * @param identifiers array of user identifiers
 */
function UserGetServerStatuses(identifiers) {
    $.ajax({
        type: "GET",
        url: "/FoodService/resources/users/statuses",
        dataType: 'json',
        data: identifiers,
        success: function(results) {
            alert(results);
            $.each(results, function() {

            });
            return results;
        },
        error: function () {
            return null;
        }
    });
}

/**
 * Changes the system status of particular user
 * @param id identifier of user
 * @param status System Status
 * @constructor
 */
function UserChangeSystemStatus(id, status) {
    $.ajax({
        type: "PUT",
        url: "/FoodService/resources/users/statuses?id=" + id + "&status=" + status,
        dataType: 'json',
        success: function() {
            alert("Changed")
        },
        error: function () {
            alert("error")
            return null;
        }
    });
}
