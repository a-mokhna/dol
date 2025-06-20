package com.example.del.servlet;

import com.example.del.dao.CategoryDAO;
import com.example.del.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/categories")
public class CategoryListServlet extends HttpServlet {
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("categories", categoryDAO.findAll());
        req.getRequestDispatcher("/WEB-INF/views/categories.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Category category = new Category();
        category.setCategoryName(req.getParameter("categoryName"));
        category.setDescription(req.getParameter("description"));
        categoryDAO.create(category);
        resp.sendRedirect(req.getContextPath() + "/categories");
    }
}