<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Food Service</title>
    <link href="${root}/view/res/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
    <script src="${root}/view/res/js/animate-bg.js" type="text/javascript"></script>
    <script src="${root}/view/res/js/scripts.js" type="text/javascript"></script>
</head>
<body>
<div id="header">
    <div id="logo">
        <a href="index.jsp"><img src="${root}/view/res/images/foodservice.png"></a>
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
        <p style="text-align: center;">Welcome My FRIEND!</p>
        <br>
        <p><img src="${root}/view/res/images/welcome_page.png" style="margin-left: 22px" /></p>
    </div>
    <div id="rightBar">
        <form>
            <button id="userSignUp" formaction="${root}/view/public/register_user.jsp"><p>Sign up as user</p></button>
            <br>
            <button id="managerSignUp" formaction="${root}/view/public/register_manager.jsp"><p>Sign up as manager</p></button>
        </form>
    </div>
</div>
</body>
</html>