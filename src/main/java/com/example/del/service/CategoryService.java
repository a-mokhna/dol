package com.example.del.service;

import com.example.del.dao.CategoryDAO;
import com.example.del.model.Category;

import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    // Получить список всех категорий
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    // Получить категорию по id
    public Category getCategoryById(Long id) {
        return categoryDAO.findById(id);
    }

    // Создать новую категорию
    public void createCategory(Category category) {
        categoryDAO.create(category);
    }

    // Обновить существующую категорию
    public void updateCategory(Category category) {
        categoryDAO.update(category);
    }

    // Удалить категорию по id
    public void deleteCategory(Long id) {
        categoryDAO.delete(id);
    }
}
