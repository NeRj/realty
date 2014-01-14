<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню</title>
</head>
<body>
<%
    if (session.getAttribute("userid") == null) response.sendRedirect("index.jsp");
%>
<a href="realty">Вся недвижимость</a>
<a href="customers">Список клиентов</a>
</body>
</html>
