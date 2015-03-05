<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
    <meta charset="utf-8">
    <title>Manager profile</title>
    <link href="${root}/view/res/style.css" rel="stylesheet" type="text/css">

    <!--for posting data-->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
    <script src="${root}/view/res/js/scripts.js" type="text/javascript"></script>
    <script src="${root}/view/res/js/animate-bg.js" type="text/javascript"></script>
</head>
<body>
<div id="header">
    <div id="logo">
        <a href="${root}/view/public/index.jsp"><img src="${root}/view/res/images/foodservice.png"></a>
    </div>
    <div id="menu">
        <ul id="nav">
            <li><a href="${root}/view/public/index.jsp">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Work</a></li>
            <li><a href="${root}/view/private/redirect">Profile</a></li>
        </ul>
    </div>
</div>
<div id="content">
    <div id="leftBar">
        <p>Leftbar</p>
    </div>
    <div id="centralBar">
        <h2><p>Profile</p></h2>
        <br>
        <div id="profileImage">
            <img height="150px" src="${root}/view/res/images/photo.jpg"/>
        </div>
        <div id="profileText">
            <div id="profileTextKey">
                <p>First name : </p>
                <br>
                <p>Last name : </p>
                <br>
                <p>E-mail : </p>
                <br>
                <p>About you : </p>
            </div>
            <div id="profileTextValue">
                <p id="profileFName">Ivan</p>
                <br>
                <p id="profileLName">Yakukhno</p>
                <br>
                <p id="profileEmail">sokole2@yandex.ru</p>
                <br>
                <p id="profileInfo">Some information</p>
            </div>
            <div id="profileMenu">
                <form>
                    <button formaction="${root}/view/private/friends.jsp" name="imageButton" onmousemove="moveImage(this)" onmouseout="outImage(this)">
                        <img src="${root}/view/res/images/ic_action_user.png"/>
                    </button>
                    <button formaction="${root}/view/private/messages.jsp" name="imageButton" onmousemove="moveImage(this)" onmouseout="outImage(this)">
                        <img src="${root}/view/res/images/ic_action_mail.png"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
    <div id="rightBar">
        <p>Rightbar</p>
    </div>
</div>
</body>
</html>