<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
    <meta charset="utf-8">
    <title>Register user</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
    <script src="${root}/view/res/js/load/REST_client.js"></script>
    <script>
        $(function() {
            $('#submitManagerUserForm').click(function (e) {
                ManagerUserCreate();
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
        <h2>Sign up as user</h2>
        <br>
        <form action="${root}/view/private/redirect" accept-charset=utf-8>
            <label for="firstName">First name</label>
            <p><input id="firstName"  type="text" placeholder="Enter your name" required></p>
            <label for="lastName">Last name</label>
            <p><input id="lastName"  type="text" placeholder="Enter your last name" required></p>
            <label for="shopAdminUserEmail">The login-email of your administrator</label>
            <p><input id="shopAdminUserEmail" type="text" placeholder="Enter there shop admin email" required></p>
            <label for="email">E-mail</label>
            <p><input id="email"  type="email" placeholder="Enter your e-mail" required></p>
            <label for="password">Password</label>
            <p><input id="password" onchange="checkPasswords()" type="password" placeholder="Enter password" required></p>
            <p><input id="passwordconfirm" onchange="checkPasswords()" type="password" placeholder="Confirm password" required></p>
            <label for="personalData">About you</label>
            <p><input id="personalData"  type="text" placeholder="Tell some words about you"></p><br>
            <button id="submitManagerUserForm" type="submit"><p>Send</p></button>
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
