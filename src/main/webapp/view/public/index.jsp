<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <c:set var="root" value="${pageContext.request.contextPath}" />
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
        <title>Food Service</title> 
        <link href="${root}/view/res/style.css" rel="stylesheet" type="text/css" media="screen" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script> 
        <script src="${root}/view/res/js/animate-bg.js" type="text/javascript"></script>
        <script src="${root}/view/res/js/scripts.js" type="text/javascript"></script>
    </head> 
    <body> 
        <div id="header">
            <div id="logo">
                <a href="${root}/view/public/index.jsp"><img src="${root}/view/res/images/foodservice.png"></a>
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
        <div id="content">   
            <div id="leftbar">
                <p>Leftbar</p>
            </div>
            <div id="centralbar">
                <p>Welcome!</p>
            </div>
            <div id="rightbar">
                <form action="${root}/view/public/register_user.jsp">
                    <p><button id="userSignUp"><font color="#9c5959" size="4">Sign up as user</font></button></p> <br>
                </form>
                <form action="${root}/view/public/register_manager.jsp">
                    <p><button id="managerSignUp"><font color="#9c5959" size="4">Sign up as manager</font></button></p>
                </form>
            </div>
        </div>
    </body> 
</html>