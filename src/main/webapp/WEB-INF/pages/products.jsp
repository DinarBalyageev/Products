<%--
  Created by IntelliJ IDEA.
  User: DA
  Date: 22.01.2018
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head></head>
    <title>Products</title>
<body>
Текущая дата: <%= new java.util.Date()%>
<table>
        <tr>
            <td>Название продукта </td>
            <td>Производитель </td>
            <td>Адрес производства </td>
            <td>Цена </td>
            <td>Количество </td>
        </tr>
        <%--<jsp:useBean id="productList" scope="request" type="java.util.List"/>--%>

        <c:forEach var="products" items="${productsList}">
            <tr>
                <td>${products.name}</td>
                <td>${products.manufacturer}</td>
                <td>${products.address}</td>
                <td>${products.price} руб.</td>
                <td><input type="number" value="1" size=50 min="1" max=${products.count} ></td>
                <td><button value=${products.id}>Добавить</button></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
