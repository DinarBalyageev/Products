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
    <p align="center"><strong>�����:</strong>
        <input align="center" type="text" value="user" name="userName"/>
    <p align="center"><strong>������:</strong>
        <input align="center" type="password" value="user" name="userPassword"/>
    <p align="center">
        <button type="submit" value="OK" style="width: 60px; height:25px;">OK</button>
        <input type="button" value="�����������" style="width: 100px; height:25px;"
               onclick=" location.href='/registration.jsp'  ">
</form>
<h3 align="center"><font color="red">
        <% String error = request.getParameter("error");%>
        <%="invalidauth".equals(error) ? "�������� ����� ��� ������ ":"" %>
        <%="enter".equals(error) ? "����������� ������ �������. /n ������� ����� � ������ ��� ����� � ������� ":"" %>

</h3></font>
</body>
</html>
