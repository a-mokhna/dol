package com.example.del.dao;

import com.example.del.model.OrderDetail;
import com.example.del.model.OrderDetailPK;
import com.example.del.servlet.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class OrderDetailDAO {

    private EntityManagerFactory emf = EMF.get();
    // Получение списка всех записей детализации заказов
    public List<OrderDetail> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<OrderDetail> query = em.createQuery("SELECT od FROM OrderDetail od", OrderDetail.class);
        List<OrderDetail> orderDetails = query.getResultList();
        em.close();
        return orderDetails;
    }

    // Поиск детализации заказа по составному ключу
    public OrderDetail findById(OrderDetailPK id) {
        EntityManager em = emf.createEntityManager();
        OrderDetail orderDetail = em.find(OrderDetail.class, id);
        em.close();
        return orderDetail;
    }

    public void create(OrderDetail orderDetail) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(orderDetail);
        em.getTransaction().commit();
        em.close();
    }

    public void update(OrderDetail orderDetail) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(orderDetail);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(OrderDetailPK id) {
        EntityManager em = emf.createEntityManager();
        OrderDetail orderDetail = em.find(OrderDetail.class, id);
        if (orderDetail != null) {
            em.getTransaction().begin();
            em.remove(orderDetail);
            em.getTransaction().commit();
        }
        em.close();
    }
}
