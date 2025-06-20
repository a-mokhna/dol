<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Главная страница</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
  <meta charset="UTF-8">
  <style>
    .navbar {
      box-shadow: 0 2px 4px rgba(0,0,0,.1);
    }
    .jumbotron {
      background: linear-gradient(45deg, #6c5ce7, #a66efa);
      border-radius: 15px;
    }
    .card-hover:hover {
      transform: translateY(-5px);
      transition: transform 0.3s ease;
    }
  </style>
</head>
<body class="d-flex flex-column min-vh-100">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">
      <i class="fa-solid fa-store"></i> Магазин
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="/categories">
            <i class="fa-solid fa-list"></i> Категории
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/orders">
            <i class="fa-solid fa-cart-shopping"></i> Заказы
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/products">
            <i class="fa-solid fa-box"></i> Товары
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/order-details">
            <i class="fa-solid fa-file-invoice"></i> Детали заказов
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>