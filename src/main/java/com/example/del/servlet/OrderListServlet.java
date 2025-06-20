package com.example.del.servlet;

import com.example.del.model.OrderEntity;
import com.example.del.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/orders")
public class OrderListServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("orders", orderService.getAllOrders());
        req.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            OrderEntity order = new OrderEntity();
            order.setCustomer(req.getParameter("customer"));
            order.setEmployee(req.getParameter("employee"));

            order.setOrderDate(LocalDate.parse(req.getParameter("orderDate")));
            order.setShipperDate(LocalDate.parse(req.getParameter("shipperDate")));

            order.setShipName(req.getParameter("shipName"));
            order.setShipAddress(req.getParameter("shipAddress"));

            orderService.createOrder(order);
            resp.sendRedirect(req.getContextPath() + "/orders");
        } catch (DateTimeParseException | NullPointerException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат даты или отсутствуют обязательные параметры");
        }
    }
}
