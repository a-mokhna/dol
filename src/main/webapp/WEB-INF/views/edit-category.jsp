<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container mt-4">
    <h2>Редактирование категории</h2>

    <form action="${pageContext.request.contextPath}/categories/edit" method="post">
        <input type="hidden" name="id" value="${category.id}">

        <div class="mb-3">
            <label for="categoryName" class="form-label">Название категории</label>
            <input type="text" class="form-control" id="categoryName"
                   name="categoryName" value="${category.categoryName}" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Описание</label>
            <textarea class="form-control" id="description"
                      name="description" rows="3">${category.description}</textarea>
        </div>

        <div class="d-flex justify-content-between">
            <a href="${pageContext.request.contextPath}/categories"
               class="btn btn-secondary">Назад</a>
            <button type="submit" class="btn btn-primary">Сохранить изменения</button>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>