<%@ page import="com.foodservice.security.CustomUserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="show" value="<%=SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomUserDetails %>"/>

  <link href="${root}/view/res/style.css" rel="stylesheet" type="text/css" media="screen" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
  <script src="${root}/view/res/js/animate-bg.js" type="text/javascript"></script>
  <script src="${root}/view/res/js/scripts.js" type="text/javascript"></script>

<div id="header">
  <div id="logo">
    <a href="${root}/view/public/index.jsp"><img src="${root}/view/res/images/foodservice.png"></a>
  </div>
  <div id="menu">
    <ul id="nav">
      <li><a href="${root}/view/public/index.jsp">Home</a></li>
      <li><a href="${root}/view/public/service.jsp">Service</a></li>
      <li><a href="#">Work</a></li>
    <c:if test="${!show}">
        <li><a href="${root}/view/private/redirect">Sign in</a></li>
    </c:if>
    <c:if test="${show}">
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
        <li><a href="${logoutUrl}" >Sign out</a></li>
    </c:if>
    </ul>
  </div>

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
    </c:if>
</div>