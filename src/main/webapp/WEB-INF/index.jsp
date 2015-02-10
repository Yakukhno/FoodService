<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Animated Label Navigation Menu</title>

  <link href="${pageContext.request.contextPath}/res/style.css" rel="stylesheet" type="text/css" media="screen" />

  <%--<style type="text/css">--%>
    <%--<%@include file="resources/style.css"%>--%>
  <%--</style>--%>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/res/js/animate-bg.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/res/js/scripts.js" type="text/javascript"></script>

</head>

<body>

<div id="container">
  <ul id="nav">
    <li><a href="#">Home</a></li>
    <li><a href="#">About</a></li>
    <li><a href="#">Work</a></li>
    <li><a href="#">Contact</a></li>
    <%--<li><a href="#">Join</a></li>--%>
  </ul>
</div>

</body>
</html>