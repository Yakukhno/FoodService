<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8">
		<title>Register</title>
		<link href="${root}/view/res/style.css" rel="stylesheet" type="text/css">

        <!--for posting data-->
        <!--<script src="js/lib/JQuery-2.1.1.js"></script>-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
        <script src="${root}/view/res/js/register/SimpleUserPost.js"></script>
        <script src="${root}/view/res/js/animate-bg.js" type="text/javascript"></script>
	    <script src="${root}/view/res/js/scripts.js" type="text/javascript"></script>

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
                    <li><a href="${root}/view/private/user/profile.jsp">Profile</a></li>
                </ul>
            </div>
        </div>
        <div id="content">   
            <div id="leftbar">
                <p>Leftbar</p>
            </div>
            <div id="centralbar">
                <div id="title"><p>Sign up as user</p></div>
                <br>
                <form action="">
                    <p>First name</p>
                    <p><input id="firstName"  type="text" placeholder="Your first name" required></p>
                    <p>Last name</p>
                    <p><input id="lastName"  type="text" placeholder="Your last name" required></p>
                    <p>E-mail</p>
                    <p><input id="email"  type="email" placeholder="Your e-mail" required></p>
                    <p>Login</p>
                    <p><input id="login"  type="text" placeholder="Your login" required></p>
                    <p>Password</p>
                    <p><input id="password" onchange="checkPasswords()" type="password" placeholder="Your password" required></p>
                    <p><input id="passwordconfirm" onchange="checkPasswords()" type="password" placeholder="Confirm password" required></p>
                    <p>About you</p>
                    <p><input id="personalData"  type="text" placeholder="Tell some words about you"></p><br>
                    <p><button id="submitSimpleUserForm" type="submit">Send</button></p>
                </form>    
            </div>
            <div id="rightbar">
                <p>Rightbar</p>
            </div>
        </div>
	</body>
</html>