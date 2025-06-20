<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container mt-4">
  <h2>Редактирование заказа № ${summary.order.id}</h2>

  <form action="${pageContext.request.contextPath}/order-summary/edit" method="post">
    <input type="hidden" name="orderId" value="${summary.order.id}"/>

    <table class="table">
      <thead>
      <tr>
        <th>Товар</th>
        <th>Цена</th>
        <th>Скидка (%)</th>
        <th>Удалить</th>     <!-- новый столбец -->
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${summary.details}" var="d">
        <tr>
          <td>
              ${d.product.productName}
            <input type="hidden" name="productId" value="${d.product.id}"/>
          </td>
          <td>
            <input type="number" step="0.01" class="form-control"
                   name="unitPrice" value="${d.unitPrice}" required/>
          </td>
          <td>
            <input type="number" step="0.01" class="form-control"
                   name="discount" value="${d.discount * 100}" required/>
          </td>
          <td class="text-center">
            <input type="checkbox" name="deleteProductId" value="${d.product.id}"/>
          </td>
        </tr>
      </c:forEach>
      </tbody>

    </table>

    <div class="d-flex justify-content-between">
      <a href="${pageContext.request.contextPath}/order-details" class="btn btn-secondary">Отмена</a>
      <button type="submit" class="btn btn-primary">Сохранить все</button>
    </div>
  </form>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
