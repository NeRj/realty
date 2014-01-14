<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Выход</title>
</head>
<body>
<%
    session.setAttribute("userid", null);
    session.invalidate();
    response.sendRedirect("index.jsp");
%>
</body>
</html>
