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

@WebServlet("/orders/edit/*")
public class OrderEditServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {

            Long id = Long.parseLong(req.getPathInfo().substring(1));
            OrderEntity order = orderService.getOrderById(id);
            if (order != null) {
                req.setAttribute("order", order);
                req.getRequestDispatcher("/WEB-INF/views/edit-order.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Заказ не найден");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат id");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            OrderEntity order = orderService.getOrderById(id);
            if (order == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Заказ не найден");
                return;
            }
            order.setCustomer(req.getParameter("customer"));
            order.setEmployee(req.getParameter("employee"));
            order.setOrderDate(LocalDate.parse(req.getParameter("orderDate")));
            order.setShipperDate(LocalDate.parse(req.getParameter("shipperDate")));
            order.setShipName(req.getParameter("shipName"));
            order.setShipAddress(req.getParameter("shipAddress"));

            orderService.updateOrder(order);
            resp.sendRedirect(req.getContextPath() + "/orders");
        } catch (NumberFormatException | DateTimeParseException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ошибка при обработке данных");
        }
    }
}
