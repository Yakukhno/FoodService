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

<body onload='document.f.username.focus();'>
    <div id="header">
      <div id="logo">
        <a href="${root}/view/public/index.jsp"><img src="${root}/view/res/images/foodservice.png"></a>
      </div>
      <div id="menu">
        <ul id="nav">
          <li><a href="${root}/view/public/index.jsp">Home</a></li>
          <li><a href="#">About</a></li>
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
        <h3>Login with Username and Password</h3>
        <h2><p>Sign in</p></h2>

        <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
          <div class="errorblock">
            Your login attempt was not successful, try again.<br/>
              ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
          </div>
        </c:if>
        
        
        <br>
        <form name='f' action="<c:url value='/j_spring_security_check' />" method='POST'>
          <p>Login</p>
          <p><input type='text' name='username' style="border-width: 1px; border-style: solid"></p>
          <p>Password</p>
          <p><input type='password' name='password' style="border-width: 1px; border-style: solid"></p>
          <p><input type="checkbox" name="remember-me" /> Remember me</p>
          <input name="submit" type="submit" value="Login"/><p>Sign in</p></button>

          <input type="hidden" name="${_csrf.parameterName}"
                 value="${_csrf.token}" />
        </form>
      </div>
      <div id="rightBar">
        <p>Rightbar</p>
      </div>
    </div>
</body>
</html>
