package com.example.del.service;

import com.example.del.dao.ProductDAO;
import com.example.del.model.Product;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    // Получить список всех товаров
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    // Получить товар по id
    public Product getProductById(Long id) {
        return productDAO.findById(id);
    }

    // Создать новый товар
    public void createProduct(Product product) {
        productDAO.create(product);
    }

    // Обновить товар
    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    // Удалить товар по id
    public void deleteProduct(Long id) {
        productDAO.delete(id);
    }
}
