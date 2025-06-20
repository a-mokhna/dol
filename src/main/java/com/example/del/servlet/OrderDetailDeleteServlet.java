package com.example.del.servlet;

import com.example.del.model.OrderDetailPK;
import com.example.del.service.OrderDetailService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/order-details/delete")
public class OrderDetailDeleteServlet extends HttpServlet {
    private final OrderDetailService orderDetailService = new OrderDetailService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            Long orderId = Long.parseLong(req.getParameter("orderId"));
            Long productId = Long.parseLong(req.getParameter("productId"));
            OrderDetailPK pk = new OrderDetailPK(productId, orderId);
            orderDetailService.deleteOrderDetail(pk);
            resp.sendRedirect(req.getContextPath() + "/order-details");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверные параметры для удаления");
        }
    }
}
