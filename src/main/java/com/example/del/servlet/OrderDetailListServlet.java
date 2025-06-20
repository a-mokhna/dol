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

@WebServlet("/order-details")
public class OrderDetailListServlet extends HttpServlet {
    private final OrderDetailService orderDetailService = new OrderDetailService();

    private final OrderService orderService = new OrderService();
    private final ProductService productService = new ProductService();

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        req.setAttribute("orderDetails", orderDetailService.getAllOrderDetails());
//        req.setAttribute("orders", orderService.getAllOrders());
//        req.setAttribute("products", productService.getAllProducts());
//        req.getRequestDispatcher("/WEB-INF/views/order-details.jsp").forward(req, resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("orderSummaries", orderDetailService.getOrderSummaries());
        req.getRequestDispatcher("/WEB-INF/views/order-details.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            Long orderId = Long.parseLong(req.getParameter("orderId"));
            Long productId = Long.parseLong(req.getParameter("productId"));
            BigDecimal unitPrice = new BigDecimal(req.getParameter("unitPrice"));
            BigDecimal discount = new BigDecimal(req.getParameter("discount"));

            // Формируем составной ключ
            OrderDetailPK pk = new OrderDetailPK(productId, orderId);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(pk);
            orderDetail.setUnitPrice(unitPrice);
            orderDetail.setDiscount(discount);

            orderDetail.setOrder(orderService.getOrderById(orderId));
            orderDetail.setProduct(productService.getProductById(productId));

            orderDetailService.createOrderDetail(orderDetail);
            resp.sendRedirect(req.getContextPath() + "/order-details");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверные параметры создания детализации");
        }
    }
}
