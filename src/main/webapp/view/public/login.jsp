<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />

<head>
  <title>Login Page</title>
  
  <link href="${root}/view/res/style.css" rel="stylesheet" type="text/css" media="screen" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
  <script src="${root}/view/res/js/animate-bg.js" type="text/javascript"></script>
  <script src="${root}/view/res/js/scripts.js" type="text/javascript"></script>
</head>
<body onload='document.f.j_username.focus();'>
    <div id="header">
      <div id="logo">
        <a href="index.jsp"><img src="../res/images/foodservice.png"></a>
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
    
    <h3>Login with Username and Password</h3>

<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
  <div class="errorblock">
    Your login attempt was not successful, try again.<br/>
    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
  </div>
</c:if>

<form name='f' action='${root}/j_spring_security_check' method='POST'>
  <table>
    <tr><td>User:</td><td><input type='text' name='username' value=''></td></tr>
    <tr><td>Password:</td><td><input type='password' name='password'/></td></tr>
    <tr><td>Remember me</td><td>Remember Me: <input type="checkbox" name="remember-me" /></td></tr>
    <tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
  </table>
</form></body></html>
