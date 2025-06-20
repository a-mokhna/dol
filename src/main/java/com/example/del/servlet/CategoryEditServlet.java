package com.example.del.servlet;

import com.example.del.dao.CategoryDAO;
import com.example.del.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/categories/edit/*")
public class CategoryEditServlet extends HttpServlet {
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if (pathInfo != null) {
                Long id = Long.parseLong(pathInfo.substring(1));
                Category category = categoryDAO.findById(id);

                if (category != null) {
                    req.setAttribute("category", category);
                    req.getRequestDispatcher("/WEB-INF/views/edit-category.jsp").forward(req, resp);
                    return;
                }
            }
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    // Обработать отправку формы
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Category category = categoryDAO.findById(id);

            if (category == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            category.setCategoryName(req.getParameter("categoryName"));
            category.setDescription(req.getParameter("description"));
            categoryDAO.update(category);

            resp.sendRedirect(req.getContextPath() + "/categories");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}