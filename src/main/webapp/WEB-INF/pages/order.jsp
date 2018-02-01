<%--
  Created by IntelliJ IDEA.
  User: DA
  Date: 22.01.2018
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="order" class="POJO.Order" scope="page"/>
<jsp:useBean id="products" class="POJO.Products" scope="page"/>

<html>
<head></head>
<title>Заказы</title>
<body>

<table border="1">
    <tr>
        <td>Нoмер заказа</td>
        <td>Статус заказа</td>
        <td>Наименование</td>
        <td>Цена</td>
        <td>Количество</td>
    </tr>
    <c:set var="id" value="0"/>
    <c:set var="sum" value="0.0"/>
    <c:forEach var="orderStatus" items="${orderStatusList}">
        <tr>
        <c:if test="${orderStatus.idOrder==id}">
            <td></td>
            <td></td>
        </c:if>
        <c:if test="${orderStatus.idOrder!=id}">
            <tr></tr>
            <td>${orderStatus.idOrder}</td>
            <td>${orderStatus.status}</td>
            <c:set var="id" value="${orderStatus.idOrder}"/>
        </c:if>
        <td>${orderStatus.order.products.name}</td>
        <td>${orderStatus.order.count}</td>
        <td>${orderStatus.order.products.price}</td>
        <c:set var="sum" value="${orderStatus.order.products.price*orderStatus.order.count+sum}"/>
        <c:if test="${orderStatus.idOrder!=id}">
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>Сумма заказа <c:out value="${sum}"/></td>
                <c:set var="sum" value="0.0"/>
            </tr>
        </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>
