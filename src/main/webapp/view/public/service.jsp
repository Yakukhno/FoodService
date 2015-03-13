<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8">
		<title>Service</title>
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
                    <li><a href="${root}/view/public/service.jsp">Service</a></li>
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
                <h2>Shops</h2>
                <script>uploadShops();</script>
            </div>
            <div id="rightBar">
                <p>Rightbar</p>
            </div>
        </div>
	</body>
</html>