package com.example.del.servlet;


import com.example.del.dao.OrderSummaryDAO;
import com.example.del.model.OrderDetail;
import com.example.del.model.OrderDetailPK;
import com.example.del.service.OrderDetailService;
import com.example.del.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@WebServlet("/order-summary/edit")
public class OrderSummaryEditServlet extends HttpServlet {
    private final OrderDetailService odService = new OrderDetailService();
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.parseLong(req.getParameter("orderId"));
        // Извлекаем все сводки и выбираем нужную
        OrderSummaryDAO summary = odService.getOrderSummaries().stream()
                .filter(s -> s.getOrder().getId().equals(orderId))
                .findFirst()
                .orElse(null);

        if (summary == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Заказ не найден");
            return;
        }

        req.setAttribute("summary", summary);
        req.getRequestDispatcher("/WEB-INF/views/edit-order-summary.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long orderId = Long.parseLong(req.getParameter("orderId"));
        String[] prodIds    = req.getParameterValues("productId");
        String[] unitPrices = req.getParameterValues("unitPrice");
        String[] discounts  = req.getParameterValues("discount");
        String[] delIds     = req.getParameterValues("deleteProductId");

        // 1) Удаляем отмеченные
        if (delIds != null) {
            for (String pid : delIds) {
                Long productId = Long.parseLong(pid);
                OrderDetailPK pk = new OrderDetailPK(productId, orderId);
                odService.deleteOrderDetail(pk);
            }
        }

        // 2) Обновляем оставшиеся (пропускаем удалённые)
        for (int i = 0; i < prodIds.length; i++) {
            Long productId = Long.parseLong(prodIds[i]);
            // если этот продукт был в списке delIds — пропускаем
            if (delIds != null && Arrays.asList(delIds).contains(prodIds[i])) {
                continue;
            }
            BigDecimal price    = new BigDecimal(unitPrices[i]);
            BigDecimal discount = new BigDecimal(discounts[i]).divide(BigDecimal.valueOf(100));

            OrderDetailPK pk = new OrderDetailPK(productId, orderId);
            OrderDetail detail = odService.getOrderDetailById(pk);
            detail.setUnitPrice(price);
            detail.setDiscount(discount);
            odService.updateOrderDetail(detail);
        }

        List<OrderDetail> remaining = odService.getDetailsByOrderId(orderId);
        if (remaining.isEmpty()) {
            orderService.deleteOrder(orderId);
        }

        resp.sendRedirect(req.getContextPath() + "/order-details");
    }

}
