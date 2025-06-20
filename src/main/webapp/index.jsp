<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<div class="container my-5">
    <div class="jumbotron text-white p-5 mb-4">
        <h1 class="display-4">Добро пожаловать в наш магазин!</h1>
        <p class="lead">Управляйте своими категориями, товарами и заказами</p>
        <a class="btn btn-light btn-lg" href="/categories" role="button">
            <i class="fa-solid fa-chart-line"></i> Перейти к категориям
        </a>
    </div>

    <div class="row g-4">
        <div class="col-md-3">
            <div class="card h-100 card-hover">
                <div class="card-body text-center">
                    <i class="fa-solid fa-list fa-3x text-primary mb-3"></i>
                    <h5 class="card-title">Категории</h5>
                    <p class="card-text">Управление категориями товаров</p>
                    <a href="/categories" class="btn btn-primary">Перейти</a>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card h-100 card-hover">
                <div class="card-body text-center">
                    <i class="fa-solid fa-cart-shopping fa-3x text-success mb-3"></i>
                    <h5 class="card-title">Заказы</h5>
                    <p class="card-text">Просмотр и управление заказами</p>
                    <a href="/orders" class="btn btn-success">Перейти</a>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card h-100 card-hover">
                <div class="card-body text-center">
                    <i class="fa-solid fa-box fa-3x text-warning mb-3"></i>
                    <h5 class="card-title">Товары</h5>
                    <p class="card-text">Управление товарной базой</p>
                    <a href="/products" class="btn btn-warning">Перейти</a>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card h-100 card-hover">
                <div class="card-body text-center">
                    <i class="fa-solid fa-file-invoice fa-3x text-info mb-3"></i>
                    <h5 class="card-title">Детали заказов</h5>
                    <p class="card-text">Детальная информация о заказах</p>
                    <a href="/order-details" class="btn btn-info">Перейти</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>
