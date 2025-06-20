<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container mt-4">
    <h2>Управление заказами</h2>

    <!-- Форма создания нового заказа -->
    <div class="card mb-4">
        <div class="card-header">Добавить новый заказ</div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/orders" method="post">
                <div class="row g-3">
                    <div class="col-md-4">
                        <label class="form-label">Заказчик</label>
                        <input type="text" class="form-control" name="customer" required>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Сотрудник</label>
                        <input type="text" class="form-control" name="employee" required>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Дата заказа</label>
                        <input type="date" class="form-control" name="orderDate" required>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Дата отправки</label>
                        <input type="date" class="form-control" name="shipperDate" required>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Название доставки</label>
                        <input type="text" class="form-control" name="shipName" required>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Адрес доставки</label>
                        <input type="text" class="form-control" name="shipAddress" required>
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary mt-3">Добавить заказ</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- Таблица заказов -->
    <table class="table table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Заказчик</th>
            <th>Сотрудник</th>
            <th>Дата заказа</th>
            <th>Дата отправки</th>
            <th>Название доставки</th>
            <th>Адрес доставки</th>
            <th>Детализация</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.customer}</td>
                <td>${order.employee}</td>
                <td>${order.orderDate}</td>
                <td>${order.shipperDate}</td>
                <td>${order.shipName}</td>
                <td>${order.shipAddress}</td>
                <td>                    <a href="${pageContext.request.contextPath}/order-summary/edit?orderId=${order.id}"
                                           class="btn btn-sm btn-success">Детализация</a></td>
                <td>
                    <a href="${pageContext.request.contextPath}/orders/edit/${order.id}" class="btn btn-sm btn-warning">Редактировать</a>
                    <form action="${pageContext.request.contextPath}/orders/delete" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${order.id}">
                        <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Удалить заказ?')">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
