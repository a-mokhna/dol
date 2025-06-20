package com.example.del.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private BigDecimal unitPrice;
    private Integer unitInStoke;
    private Integer unitInOrder;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "products")
    private List<OrderEntity> orders;

    public Product() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Integer getUnitInStoke() {
        return unitInStoke;
    }
    public void setUnitInStoke(Integer unitInStoke) {
        this.unitInStoke = unitInStoke;
    }
    public Integer getUnitInOrder() {
        return unitInOrder;
    }
    public void setUnitInOrder(Integer unitInOrder) {
        this.unitInOrder = unitInOrder;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public List<OrderEntity> getOrders() {
        return orders;
    }
    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
