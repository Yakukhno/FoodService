<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8">
		<title>Register manager</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
        <script src="${root}/view/res/js/load/REST_client.js"></script>
        <script>
            $(function() {
                $('#submitShopAdminUserForm').click(function (e) {
                    ShopAdminUserCreate();
                })
            })
        </script>
    </head>
	<body>
        
        <%--include header--%>
        <%@ include file="/view/public/common/header.jsp" %>
        
        <div id="content">   
            <div id="leftbar">
                <p>Leftbar</p>
            </div>
            <div id="centralbar">
                <h2>Sign up as shop administrator</h2>
                <br>
                <form action="${root}/view/private/redirect" accept-charset=utf-8>
                    <p>First name</p>
                    <p><input id="firstName"  type="text" placeholder="Your first name" required></p>
                    <p>Last name</p>
                    <p><input id="lastName"  type="text" placeholder="Your last name" required></p>
                    <p>E-mail</p>
                    <p><input id="email"  type="email" placeholder="Your e-mail" required></p>
                    <p>Contact telephone</p>
                    <p><input id="telephone"  type="text" placeholder="Your telephone" required></p>
                    <p>Password</p>
                    <p><input id="password" onchange="checkPasswords()" type="password" placeholder="Your password" required></p>
                    <p><input id="passwordconfirm" onchange="checkPasswords()" type="password" placeholder="Confirm password" required></p>
                    <p>About you</p>
                    <p><input id="personalData"  type="text" placeholder="Tell some words about you"></p><br>
                    <button id="submitShopAdminUserForm" type="submit"><p>Send</p></button>
                </form>
            </div>
            <div id="rightbar">
                <p>Rightbar</p>
            </div>
        </div>

        <%--include footer--%>
        <%@ include file="/view/public/common/footer.jsp" %>

	</body>
</html>