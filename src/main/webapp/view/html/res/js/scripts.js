
$(document).ready(function() {
	$("ul#nav li a").addClass("js");
	$("ul#nav li a").hover(
      function () {
        $(this).stop(true,true).animate({backgroundPosition:"(0 0)"}, 200);
        $(this).animate({backgroundPosition:"(0 -5px)"}, 150);
      }, 
      function () {
        $(this).animate({backgroundPosition:"(0 -149px)"}, 200);

      }
    );
});

function checkPasswords() {
    var pass = document.getElementById("password");
    var passconfirm = document.getElementById("passwordconfirm");
    var button = document.getElementById("submitSimpleUserForm");
    if (pass.value && passconfirm.value) {
        if (pass.value == passconfirm.value) {
            pass.setAttribute("style", "border-width: 1px; border-style: solid; border-color: #00FF00");
            passconfirm.setAttribute("style", "border-width: 1px; border-style: solid; border-color: #00FF00");
            button.removeAttribute("disabled");
        } else {
            pass.setAttribute("style", "border-width: 1px; border-style: solid; border-color: #FF0000");
            passconfirm.setAttribute("style", "border-width: 1px; border-style: solid; border-color: #FF0000");
            button.setAttribute("disabled", "true");
        }
    } else {
            pass.setAttribute("style", "border-width: 1px; border-style: dotted; border-color: #000000");
            passconfirm.setAttribute("style", "border-width: 1px; border-style: dotted; border-color: #000000");        
    }
}

function uploadShops() {
    var bar = document.getElementById("centralBar");
    for (var i = 0; i < 5; i++) {
        var p = document.createElement("div");
        p.setAttribute("id", "shop");
        p.innerHTML = '<h3>McDonalds ' + i + '</h3> <div id="shopText"> <p>Description: fddsfsdfsdfffsfssdsdfs sddf fsdfsdfsd ssdfsdf sddfsd ssdfsdfsdsdsdf sfsfs  sdfs dfsdsf </p> </div> <div id="profileImage"> <img height="150px" src="res/images/photo.jpg"/></div>';
        bar.appendChild(p);      
    }
}

function loadProfile() {
    document.getElementById("profileFName").innerHTML = "Grygoriy";
    document.getElementById("profileLName").innerHTML = "Rozhkov";
    document.getElementById("profileEmail").innerHTML = "grisha@gmail.com";
    document.getElementById("profileInfo").innerHTML = "...";
}

function moveImage(o) {
    o.style.backgroundColor = "firebrick";
}

function outImage(o){
    o.style.backgroundColor = "darksalmon";
}
