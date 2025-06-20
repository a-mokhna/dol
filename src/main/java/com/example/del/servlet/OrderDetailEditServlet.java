package com.example.del.servlet;

import com.example.del.model.OrderDetail;
import com.example.del.model.OrderDetailPK;
import com.example.del.service.OrderDetailService;
import com.example.del.service.OrderService;
import com.example.del.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/order-details/edit")
public class OrderDetailEditServlet extends HttpServlet {
    private final OrderDetailService orderDetailService = new OrderDetailService();
    private final OrderService orderService = new OrderService();
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {

            Long orderId = Long.parseLong(req.getParameter("orderId"));
            Long productId = Long.parseLong(req.getParameter("productId"));
            OrderDetailPK pk = new OrderDetailPK(productId, orderId);
            OrderDetail orderDetail = orderDetailService.getOrderDetailById(pk);
            if (orderDetail != null) {
                req.setAttribute("orderDetail", orderDetail);

                req.setAttribute("orders", orderService.getAllOrders());
                req.setAttribute("products", productService.getAllProducts());
                req.getRequestDispatcher("/WEB-INF/views/edit-order-detail.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Детализация заказа не найдена");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверные параметры запроса");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            Long orderId = Long.parseLong(req.getParameter("orderId"));
            Long productId = Long.parseLong(req.getParameter("productId"));
            BigDecimal unitPrice = new BigDecimal(req.getParameter("unitPrice"));
            BigDecimal discount = new BigDecimal(req.getParameter("discount"));

            OrderDetailPK pk = new OrderDetailPK(productId, orderId);
            OrderDetail orderDetail = orderDetailService.getOrderDetailById(pk);

            if (orderDetail == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Детализация заказа не найдена");
                return;
            }

            orderDetail.setUnitPrice(unitPrice);
            orderDetail.setDiscount(discount);
            orderDetail.setOrder(orderService.getOrderById(orderId));
            orderDetail.setProduct(productService.getProductById(productId));

            orderDetailService.updateOrderDetail(orderDetail);
            resp.sendRedirect(req.getContextPath() + "/order-details");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ошибка при обновлении детализации заказа");
        }
    }
}
