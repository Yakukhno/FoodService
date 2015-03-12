<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
        <meta charset="utf-8">
		<title>Register User</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
        <script src="${root}/view/res/js/register/SimpleUserPost.js"></script>

	</head>
	<body>

        <%--include header--%>
        <%@ include file="/view/public/common/header.jsp" %>
        
        <div id="content">   
            <div id="leftbar">
                <p>Leftbar</p>
            </div>
            <div id="centralbar">
                <div id="title"><p>Sign up as user</p></div>
                <br>
                <form action="${root}/view/private/redirect" accept-charset=utf-8>
                    <p>First name</p>
                    <p><input id="firstName"  type="text" placeholder="Your first name" required></p>
                    <p>Last name</p>
                    <p><input id="lastName"  type="text" placeholder="Your last name" required></p>
                    <p>E-mail</p>
                    <p><input id="email"  type="email" placeholder="Your e-mail" required></p>
                    <p>Password</p>
                    <p><input id="password" onchange="checkPasswords()" type="password" placeholder="Your password" required></p>
                    <p><input id="passwordconfirm" onchange="checkPasswords()" type="password" placeholder="Confirm password" required></p>
                    <p>About you</p>
                    <p><input id="personalData"  type="text"  placeholder="Tell some words about you"></p><br>
                    <p><button id="submitSimpleUserForm" type="submit">Send</button></p>
                </form>    
            </div>
            <div id="rightbar">
                <p>Rightbar</p>
            </div>
        </div>
	</body>
</html>