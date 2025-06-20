package com.example.del.service;
import java.util.LinkedHashMap;

import com.example.del.dao.OrderDetailDAO;
import com.example.del.model.OrderDetail;
import com.example.del.model.OrderDetailPK;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDetailService {
    private final OrderDetailDAO dao = new OrderDetailDAO();
    private final OrderDetailDAO orderDetailDAO;

    public OrderDetailService() {
        this.orderDetailDAO = new OrderDetailDAO();
    }

    // Получить список всех детализаций заказов
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailDAO.findAll();
    }

    // Получить запись детализации по составному ключу
    public OrderDetail getOrderDetailById(OrderDetailPK id) {
        return orderDetailDAO.findById(id);
    }

    // Создать новую запись детализации заказа
    public void createOrderDetail(OrderDetail orderDetail) {
        orderDetailDAO.create(orderDetail);
    }

    // Обновить запись детализации заказа
    public void updateOrderDetail(OrderDetail orderDetail) {
        orderDetailDAO.update(orderDetail);
    }

    // Удалить запись детализации по составному ключу
    public void deleteOrderDetail(OrderDetailPK id) {
        orderDetailDAO.delete(id);
    }
    // в OrderDetailService

    public List<com.example.del.dao.OrderSummaryDAO> getOrderSummaries() {
        List<OrderDetail> allDetails = dao.findAll();  // или getAllOrderDetails()
        Map<Long, List<OrderDetail>> grouped = allDetails.stream()
                .collect(Collectors.groupingBy(d -> d.getOrder().getId(), LinkedHashMap::new, Collectors.toList()));

        List<com.example.del.dao.OrderSummaryDAO> summaries = new ArrayList<>();
        for (List<OrderDetail> details : grouped.values()) {
            summaries.add(new com.example.del.dao.OrderSummaryDAO(details.get(0).getOrder(), details));
        }
        return summaries;
    }

    // в OrderDetailService
    public List<OrderDetail> getDetailsByOrderId(Long orderId) {
        return dao.findAll().stream()
                .filter(d -> d.getOrder().getId().equals(orderId))
                .collect(Collectors.toList());
    }

}
