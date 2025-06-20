<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container mt-4">
  <h2>Редактирование детализации заказа</h2>

  <form action="${pageContext.request.contextPath}/order-details/edit" method="post">
    <input type="hidden" name="orderId" value="${orderDetail.order.id}">
    <input type="hidden" name="productId" value="${orderDetail.product.id}">

    <div class="row g-3">
      <div class="col-md-6">
        <label class="form-label">Цена</label>
        <input type="number" step="0.01" class="form-control" name="unitPrice" value="${orderDetail.unitPrice}" required>
      </div>
      <div class="col-md-6">
        <label class="form-label">Скидка</label>
        <input type="number" step="0.01" class="form-control" name="discount" value="${orderDetail.discount}" required>
      </div>

      <div class="col-md-6">
        <label class="form-label">Заказ</label>
        <select class="form-select" name="orderId" required>
          <c:forEach items="${orders}" var="order">
            <option value="${order.id}" ${order.id == orderDetail.order.id ? 'selected' : ''}>
              ID: ${order.id} - ${order.customer}
            </option>
          </c:forEach>
        </select>
      </div>
      <div class="col-md-6">
        <label class="form-label">Товар</label>
        <select class="form-select" name="productId" required>
          <c:forEach items="${products}" var="product">
            <option value="${product.id}" ${product.id == orderDetail.product.id ? 'selected' : ''}>
                ${product.productName}
            </option>
          </c:forEach>
        </select>
      </div>

      <div class="col-12">
        <div class="d-flex justify-content-between mt-3">
          <a href="${pageContext.request.contextPath}/order-details" class="btn btn-secondary">Назад</a>
          <button type="submit" class="btn btn-primary">Сохранить</button>
        </div>
      </div>
    </div>
  </form>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
