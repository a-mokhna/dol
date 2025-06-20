<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container mt-4">
    <h2>Управление детализацией заказов</h2>

    <!-- Форма добавления остаётся без изменений -->

    <table class="table table-striped">
        <thead class="table-dark">
        <tr>
            <th>Заказ (ID)</th>
            <th>Заказчик</th>
            <th>Дата заказа</th>
            <th>Товары в заказе</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderSummaries}" var="summary">
            <tr>
                <td>${summary.order.id}</td>
                <td>${summary.order.customer}</td>
                <td>${summary.order.orderDate}</td>
                <td>
                    <ul class="mb-0">
                        <c:forEach items="${summary.details}" var="d">
                            <li>
                                ${d.product.productName}
                                — цена: ${d.unitPrice}, скидка: ${d.discount}
                            </li>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/order-summary/edit?orderId=${summary.order.id}"
                       class="btn btn-sm btn-warning">Редактировать</a>
<%--                    <form action="${pageContext.request.contextPath}/order-details/delete" method="post" style="display:inline;">--%>
<%--                        <input type="hidden" name="orderId" value="${summary.order.id}">--%>
<%--                        <button type="submit" class="btn btn-sm btn-danger"--%>
<%--                                onclick="return confirm('Удалить все детали этого заказа?')">--%>
<%--                            Удалить--%>
<%--                        </button>--%>
<%--                    </form>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
