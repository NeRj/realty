<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
</head>
<body>
<%=request.getAttribute("error_msg")%>
<a href="javascript:history.back()">Назад</a>
</body>
</html>
