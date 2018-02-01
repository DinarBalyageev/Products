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
<form action="${pageContext.request.contextPath}/registration" method="post">
    <table align="center">
        <tr>
            <td>Введите логин:</td>
            <td><input type="text" name="userName" required/></td>
        </tr>
        <tr>
            <td>Введите пароль:</td>
            <td><input align="center" type="password" name="userPassword" required/></td>
        </tr>
        <tr>
            <td>Повторный ввод:</td>
            <td><input align="center" type="password" name="retryPassword" required/></td>
        </tr>
        <tr>
        <tr>
            <td>Введите фамилию:</td>
            <td><input align="center" type="text" name="firstname" required/></td>
        </tr>
        <tr>
            <td>Введите имя:</td>
            <td><input align="center" type="text" name="name" required/></td>
        </tr>
        <tr>
            <td>Введите отчество:</td>
            <td><input align="center" type="text" name="lastname" required/></td>
        </tr>
        <tr>
            <td>Введите адрес доставки:</td>
            <td><input align="center" type="text" name="address" required/></td>
        </tr>
            <td align="center">
                <button type="submit" value="OK" style="width: 60px; height:25px;">OK</button>
            </td>
            <td align="center">
                <button type="reset" value="RESET" style="width: 60px; height:25px;">RESET</button>
            </td>
        </tr>
    </table>
</form>
<h3 align="center"><font color="red">
    <% Object error = request.getAttribute("error");%>
    <%="invalidlogin".equals(error) ? "Пользователь с таким логином уже зарегистрирован в системе" : "" %>
    <%="passworderror".equals(error) ? "Введенные пароли отличаются" : "" %>
<font>
    <% request.setAttribute("error", "");%>
        </font>
</h3>
</body>
</html>
