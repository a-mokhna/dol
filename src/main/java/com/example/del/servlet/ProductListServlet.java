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

@WebServlet("/products")
public class ProductListServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("products", productService.getAllProducts());
        req.setAttribute("categories", categoryDAO.findAll());
        req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Product product = new Product();
        product.setProductName(req.getParameter("productName"));
        product.setUnitPrice(new BigDecimal(req.getParameter("unitPrice")));
        product.setUnitInStoke(Integer.parseInt(req.getParameter("unitInStoke")));
        product.setUnitInOrder(Integer.parseInt(req.getParameter("unitInOrder")));
        product.setCategory(categoryDAO.findById(
                Long.parseLong(req.getParameter("categoryId"))
        ));

        productService.createProduct(product);
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}