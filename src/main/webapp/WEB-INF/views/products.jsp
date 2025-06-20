<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container mt-4">
  <h2>Управление товарами</h2>

  <!-- Форма создания -->
  <div class="card mb-4">
    <div class="card-header">Добавить новый товар</div>
    <div class="card-body">
      <form action="${pageContext.request.contextPath}/products" method="post">
        <div class="row g-3">
          <div class="col-md-6">
            <label class="form-label">Название товара</label>
            <input type="text" class="form-control" name="productName" required>
          </div>
          <div class="col-md-6">
            <label class="form-label">Категория</label>
            <select class="form-select" name="categoryId" required>
              <c:forEach items="${categories}" var="category">
                <option value="${category.id}">${category.categoryName}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-md-3">
            <label class="form-label">Цена</label>
            <input type="number" step="0.01" class="form-control" name="unitPrice" required>
          </div>
          <div class="col-md-3">
            <label class="form-label">На складе</label>
            <input type="number" class="form-control" name="unitInStoke" required>
          </div>
          <div class="col-md-3">
            <label class="form-label">В заказе</label>
            <input type="number" class="form-control" name="unitInOrder" required>
          </div>
          <div class="col-md-3">
            <button type="submit" class="btn btn-primary mt-4">Добавить</button>
          </div>
        </div>
      </form>
    </div>
  </div>

  <!-- Таблица товаров -->
  <table class="table table-striped">
    <thead class="table-dark">
    <tr>
      <th>ID</th>
      <th>Название</th>
      <th>Категория</th>
      <th>Цена</th>
      <th>На складе</th>
      <th>В заказе</th>
      <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product">
      <tr>
        <td>${product.id}</td>
        <td>${product.productName}</td>
        <td>${product.category.categoryName}</td>
        <td>${product.unitPrice}</td>
        <td>${product.unitInStoke}</td>
        <td>${product.unitInOrder}</td>
        <td>
          <a href="${pageContext.request.contextPath}/products/edit/${product.id}"
             class="btn btn-sm btn-warning">Редактировать</a>
          <form action="${pageContext.request.contextPath}/products/delete"
                method="post" style="display:inline;">
            <input type="hidden" name="id" value="${product.id}">
            <button type="submit" class="btn btn-sm btn-danger"
                    onclick="return confirm('Удалить товар?')">Удалить</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>