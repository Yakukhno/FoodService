<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8">
		<title>Add shop</title>
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
                <h2>Add shop</h2>
                <br>
                <form action="">
                    <p>Shop name</p>
                    <p><input id="shopName"  type="text" placeholder="Your shop name" required></p>
                    <p>Country</p>
                    <p><input id="country" type="text" placeholder="Country of your shop" required></p>
                    <p>Sity</p>
                    <p><input id="sity" type="text"  placeholder="Sity of your shop" required></p>
                    <p>Street</p>
                    <p><input id="street" type="text" placeholder="Street of your shop" required></p>
                    <p>Building</p>
                    <p><input id="building" type="number" placeholder="Building of your shop" required></p>
                    <p>ZIP code</p>
                    <p><input id="zipCode" type="text" placeholder="ZIP zode of your shop" required></p>
                    <p>Photo</p>
                    <p><input id="photoShop" type="file" accept="image/*" /></p>
                    <p>Description</p>
                    <p><input id="description"  type="text" placeholder="Description of your shop"></p>
                    <button id="submitAddShop" type="submit"><p>Send</p></button>
                </form>  
            </div>
            <div id="rightBar">
                <p>Rightbar</p>
            </div>
        </div>
	</body>
</html>