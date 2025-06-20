<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="d-flex justify-content-between align-items-center mb-4">
  <h1>Управление категориями</h1>
  <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createModal">
    Создать новую
  </button>
</div>

<table class="table table-striped table-hover">
  <thead class="table-dark">
  <tr>
    <th>ID</th>
    <th>Название</th>
    <th>Описание</th>
    <th>Действия</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${categories}" var="category">
    <tr>
      <td>${category.id}</td>
      <td>${category.categoryName}</td>
      <td>${category.description}</td>
      <td class="action-buttons">
        <a href="${pageContext.request.contextPath}/categories/edit/${category.id}"
           class="btn btn-sm btn-warning">Редактировать</a>
        <button class="btn btn-sm btn-danger"
                onclick="confirmDelete(${category.id})">Удалить</button>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<div class="modal fade" id="createModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="/categories" method="POST">
        <div class="modal-header">
          <h5 class="modal-title">Создание категории</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label class="form-label">Название категории</label>
            <input type="text" class="form-control" name="categoryName" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Описание</label>
            <textarea class="form-control" name="description" rows="3"></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
          <button type="submit" class="btn btn-primary">Сохранить</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script>
  function confirmDelete(id) {
    if (confirm('Удалить категорию?')) {
      const form = document.createElement('form');
      form.method = 'POST';
      form.action = 'categories/delete';

      const method = document.createElement('input');
      method.type = 'hidden';
      method.name = '_method';
      method.value = 'DELETE';

      const idInput = document.createElement('input');
      idInput.type = 'hidden';
      idInput.name = 'id';
      idInput.value = id;

      form.appendChild(method);
      form.appendChild(idInput);
      document.body.appendChild(form);
      form.submit();
    }
  }
</script>
<%@ include file="/WEB-INF/views/footer.jsp" %>