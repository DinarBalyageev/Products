<%--
  Created by IntelliJ IDEA.
  User: DA
  Date: 22.01.2018
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head></head>
<title>Products</title>
<body>
Текущая дата: <%= new java.util.Date()%>
<% Integer i=1; %>
<table>
    <tr>
        <td>Название продукта</td>
        <td>Производитель</td>
        <td>Адрес производства</td>
        <td>Цена</td>
        <td>Количество</td>
    </tr>
    <%--<jsp:useBean id="productList" scope="request" type="java.util.List"/>--%>

    <c:forEach var="products" items="${productsList}">
        <tr>
            <form action="${pageContext.request.contextPath}/products" method="post">
            <td>${products.name}</td>
            <td>${products.manufacturer}</td>
            <td>${products.address}</td>
            <td>${products.price} руб.</td>
            <td><input name="count" type="number" value="0" size=50 min="0" max=${products.count}>
                <input name="id" value=${products.id} hidden>
            </td>
            <td>
                <button type="submit" value=${products.id}>Добавить</button>
            </td>
            </form>
        </tr>
    </c:forEach>
    <c:out value="${sessionScope}"/>
</table>
</body>
</html>
