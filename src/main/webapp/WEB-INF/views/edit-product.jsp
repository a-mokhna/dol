<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container mt-4">
    <h2>Редактирование товара</h2>

    <form action="${pageContext.request.contextPath}/products/edit" method="post">
        <input type="hidden" name="id" value="${product.id}">

        <div class="row g-3">
            <div class="col-md-6">
                <label class="form-label">Название товара</label>
                <input type="text" class="form-control" name="productName"
                       value="${product.productName}" required>
            </div>

            <div class="col-md-6">
                <label class="form-label">Категория</label>
                <select class="form-select" name="categoryId" required>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}"
                            ${product.category.id == category.id ? 'selected' : ''}>
                                ${category.categoryName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-md-4">
                <label class="form-label">Цена</label>
                <input type="number" step="0.01" class="form-control"
                       name="unitPrice" value="${product.unitPrice}" required>
            </div>

            <div class="col-md-4">
                <label class="form-label">На складе</label>
                <input type="number" class="form-control"
                       name="unitInStoke" value="${product.unitInStoke}" required>
            </div>

            <div class="col-md-4">
                <label class="form-label">В заказе</label>
                <input type="number" class="form-control"
                       name="unitInOrder" value="${product.unitInOrder}" required>
            </div>

            <div class="col-12">
                <div class="d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/products"
                       class="btn btn-secondary">Назад</a>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </div>
            </div>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>