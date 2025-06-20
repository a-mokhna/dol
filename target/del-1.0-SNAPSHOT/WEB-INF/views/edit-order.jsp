<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container mt-4">
  <h2>Редактирование заказа</h2>

  <form action="${pageContext.request.contextPath}/orders/edit" method="post">
    <input type="hidden" name="id" value="${order.id}">

    <div class="row g-3">
      <div class="col-md-4">
        <label class="form-label">Заказчик</label>
        <input type="text" class="form-control" name="customer" value="${order.customer}" required>
      </div>
      <div class="col-md-4">
        <label class="form-label">Сотрудник</label>
        <input type="text" class="form-control" name="employee" value="${order.employee}" required>
      </div>
      <div class="col-md-4">
        <label class="form-label">Дата заказа</label>
        <input type="date" class="form-control" name="orderDate" value="${order.orderDate}" required>
      </div>
      <div class="col-md-4">
        <label class="form-label">Дата отправки</label>
        <input type="date" class="form-control" name="shipperDate" value="${order.shipperDate}" required>
      </div>
      <div class="col-md-4">
        <label class="form-label">Название доставки</label>
        <input type="text" class="form-control" name="shipName" value="${order.shipName}" required>
      </div>
      <div class="col-md-4">
        <label class="form-label">Адрес доставки</label>
        <input type="text" class="form-control" name="shipAddress" value="${order.shipAddress}" required>
      </div>
      <div class="col-12">
        <div class="d-flex justify-content-between mt-3">
          <a href="${pageContext.request.contextPath}/orders" class="btn btn-secondary">Назад</a>
          <button type="submit" class="btn btn-primary">Сохранить</button>
        </div>
      </div>
    </div>
  </form>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
