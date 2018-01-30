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
<title>Basket</title>
<body>
Текущая дата: <%= new java.util.Date()%>
<table>
    <tr>
        <td>Название продукта</td>
        <td>Производитель</td>
        <td>Адрес производства</td>
        <td>Цена</td>
        <td>Количество</td>
    </tr>
    <c:forEach var="products" items="${basketList}">
        <tr>
            <td>${products.name}</td>
            <td>${products.manufacturer}</td>
            <td>${products.address}</td>
            <td>${products.price} руб.</td>
            <td>${products.count}
                <input name="id" value=${products.id} hidden>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td>В вашей корзине товара на сумму ${summa} руб.</td>
    </tr>
</table>
<c:out value="${sessionScope}"/>
<form action="${pageContext.request.contextPath}/basket" method="post">
    <button type="submit" name="target" value="ok_order" style="width: 120px; height:40px;">Оформить заказа</button>
</form>
<form action="${pageContext.request.contextPath}/basket" method="post">
    <button type="submit" name="target" value="cancel_order" style="width: 120px; height:40px;">Отменить заказ</button>
</form>

</body>
</html>
