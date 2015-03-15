<%@ page import="com.kpi.education.security.CustomUserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link href="${root}/view/res/style.css" rel="stylesheet" type="text/css" media="screen" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
  <script src="${root}/view/res/js/animate-bg.js" type="text/javascript"></script>
  <script src="${root}/view/res/js/scripts.js" type="text/javascript"></script>
  
  <%--for loading user icon-data--%>
  <%--<script src="${root}/view/res/js/load/load_user_info_icon_data.js" type="text/javascript"></script>--%>
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

    <c:set var="show" value="<%=SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomUserDetails %>"/>
    <c:if test="${show}">
        <div id="profileHeader">
              <div id="profileHeaderText">
                    <p id="lNameIconData" style="display: inline-block; vertical-align: top; width: 120px">
                        <a href="${root}/view/private/redirect">
                            <sec:authentication property="principal.firstName" />
                            <sec:authentication property="principal.lastName" />
                        </a>
                    </p>
                    <p id="emailIconData" style="vertical-align: top; width: 120px"><sec:authentication property="principal.username" /></p>
              </div>
              <button style="display: inline-block; vertical-align: top;" onclick="javascript:formSubmit()"><p>Logout</p></button>
              <c:url value="/j_spring_security_logout" var="logoutUrl" />
              <!-- csrt for log out-->
              <form id="logoutForm" action="${logoutUrl}" method="post" >
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
              </form>
    
              <script>
                function formSubmit() {
                  document.getElementById("logoutForm").submit();
                }
              </script>
    
            </div>
        </div>
    </c:if>



</body>
</html>