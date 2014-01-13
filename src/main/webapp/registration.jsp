<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h2>Регистрация нового работника</h2>
<form action="register" method="post">
    <label>Имя пользователя: <input name="username" type="text"></label>
    <label>Пароль: <input name="password" type="password"></label>
    <label>ФИО: <input name="name" type="text"></label>
    <label>Должность: <input name="position" type="text"></label>
    <input type="submit" value="Зарегестрировать">
</form>
</body>
</html>
