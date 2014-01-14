<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<%
    if (request.getAttribute("userid") != null){
        session.setAttribute("userid", request.getAttribute("userid"));
        response.sendRedirect("menu.jsp");
    }
%>
</body>
</html>
