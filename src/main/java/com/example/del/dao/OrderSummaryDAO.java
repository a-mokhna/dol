package com.example.del.dao;

import com.example.del.model.OrderEntity;
import com.example.del.model.OrderDetail;
import java.util.List;

public class OrderSummaryDAO {
    private OrderEntity order;
    private List<OrderDetail> details;

    public OrderSummaryDAO(OrderEntity order, List<OrderDetail> details) {
        this.order = order;
        this.details = details;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }
}
