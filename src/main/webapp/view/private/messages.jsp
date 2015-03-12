<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8">
		<title>Sign up</title>
        <%--<link href="${root}/view/res/style.css" rel="stylesheet" type="text/css">--%>

        <%--<!--for posting data-->--%>
        <%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>--%>
        <%--<script src="${root}/view/res/js/scripts.js" type="text/javascript"></script>--%>
        <%--<script src="${root}/view/res/js/animate-bg.js" type="text/javascript"></script>--%>
	</head>
	<body>

        <%--include header--%>
        <%@ include file="/view/public/common/header.jsp" %>

        <div id="content">
            <div id="leftBar">
                <p>Leftbar</p>
            </div>
            <div id="centralBar">
                <h2><p>Messages</p></h2> 
            </div>
            <div id="rightBar">
                <p>Rightbar</p>
            </div>
        </div>
	</body>
</html>