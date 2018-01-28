<%--
  Created by IntelliJ IDEA.
  User: DA
  Date: 19.01.2018
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <p align="center"><strong>Логин:</strong>
        <input align="center" type="text" value="user" name="userName"/>
    <p align="center"><strong>Пароль:</strong>
        <input align="center" type="password" value="user" name="userPassword"/>
    <p align="center">
        <button type="submit" value="OK" style="width: 60px; height:25px;">OK</button>
    <button value="Регистрация" hidden>
</form>
<form action="${pageContext.request.contextPath}/registration" method="get">
    <button type="submit" value="ОК" style="width: 100px; height:25px;">Регистрация</button>
</form>
<h3 align="center">
    <font color="red">
        <% Object error = request.getAttribute("error");%>
        <%="invalidauth".equals(error) ? "Неверный логин или пароль " : "" %>
        <%="enter".equals(error) ? "Регистрация прошла успешно. /n Введите логин и пароль для входа в систему " : "" %>
    </font>
</h3>

</body>
</html>
