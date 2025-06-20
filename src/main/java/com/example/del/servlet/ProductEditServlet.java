package com.example.del.servlet;

import com.example.del.dao.CategoryDAO;
import com.example.del.model.Product;
import com.example.del.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/products/edit/*")
public class ProductEditServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getPathInfo().substring(1));
            Product product = productService.getProductById(id);

            if (product != null) {
                req.setAttribute("product", product);
                req.setAttribute("categories", categoryDAO.findAll());
                req.getRequestDispatcher("/WEB-INF/views/edit-product.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Product product = productService.getProductById(id);

            product.setProductName(req.getParameter("productName"));
            product.setUnitPrice(new BigDecimal(req.getParameter("unitPrice")));
            product.setUnitInStoke(Integer.parseInt(req.getParameter("unitInStoke")));
            product.setUnitInOrder(Integer.parseInt(req.getParameter("unitInOrder")));
            product.setCategory(categoryDAO.findById(
                    Long.parseLong(req.getParameter("categoryId"))
            ));

            productService.updateProduct(product);
            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}