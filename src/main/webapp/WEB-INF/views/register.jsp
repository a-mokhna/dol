<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="d-flex justify-content-center align-items-center vh-100">
    <div class="card p-4 shadow" style="min-width: 300px;">
        <h2 class="mb-3">Регистрация</h2>


        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="mb-3">
                <label class="form-label">Имя пользователя</label>
                <input type="text" class="form-control" name="username" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Пароль</label>
                <input type="password" class="form-control" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Зарегистрироваться</button>
        </form>

        <div class="mt-3 text-center">
            Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Войти</a>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
