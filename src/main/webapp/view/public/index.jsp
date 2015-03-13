<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Food Service</title>
</head>
<body>

    <%--include header--%>
    <%@ include file="/view/public/common/header.jsp" %>
    
    <div id="content">
        <div id="leftBar">
            <p>Leftbar</p>
        </div>
        <div id="centralBar">
            <p style="text-align: center;">Welcome!</p>
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