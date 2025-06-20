package com.example.del.service;

import com.example.del.dao.OrderDAO;
import com.example.del.dao.OrderDetailDAO;
import com.example.del.model.OrderDetail;
import com.example.del.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    // Получить список всех заказов
    public List<OrderEntity> getAllOrders() {
        return orderDAO.findAll();
    }

    // Получить заказ по id
    public OrderEntity getOrderById(Long id) {
        return orderDAO.findById(id);
    }

    // Создать новый заказ
    public void createOrder(OrderEntity order) {
        orderDAO.create(order);
    }

    // Обновить заказ
    public void updateOrder(OrderEntity order) {
        orderDAO.update(order);
    }

    // Удалить заказ по id
    public void deleteOrder(Long id) {
        orderDAO.delete(id);
    }


}
