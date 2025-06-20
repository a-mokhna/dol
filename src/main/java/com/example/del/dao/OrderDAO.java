package com.example.del.dao;

import com.example.del.model.OrderEntity;
import com.example.del.servlet.EMF;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class OrderDAO {

    private EntityManagerFactory emf = EMF.get();

    // Получение списка всех заказов
    public List<OrderEntity> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<OrderEntity> query = em.createQuery("SELECT o FROM OrderEntity o", OrderEntity.class);
        List<OrderEntity> orders = query.getResultList();
        em.close();
        return orders;
    }

    // Поиск заказа по id
    public OrderEntity findById(Long id) {
        EntityManager em = emf.createEntityManager();
        OrderEntity order = em.find(OrderEntity.class, id);
        em.close();
        return order;
    }

    public void create(OrderEntity order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
    }

    public void update(OrderEntity order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(order);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        OrderEntity order = em.find(OrderEntity.class, id);
        if (order != null) {
            em.getTransaction().begin();
            em.remove(order);
            em.getTransaction().commit();
        }
        em.close();
    }
}
