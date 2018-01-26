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
        <% String error = request.getParameter("error");%>
        <%="invalidlogin".equals(error) ? "Пользователь с таким логином уже зарегистрирован в системе":"" %>
        <%="passworderror".equals(error) ? "Введенные пароли отличаются":"" %>
        <% request.setAttribute("error", "");%>

</h3></font>
</body>
</html>
