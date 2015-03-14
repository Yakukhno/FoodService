<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8">
		<title>Service</title>
        
	</head>
	<body>

        <%--include header--%>
        <%@ include file="/view/public/common/header.jsp" %>
    
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