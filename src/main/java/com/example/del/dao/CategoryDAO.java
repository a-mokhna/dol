package com.example.del.dao;

import com.example.del.model.Category;
import com.example.del.servlet.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class CategoryDAO {

    private EntityManagerFactory emf = EMF.get();

    public List<Category> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
        List<Category> categories = query.getResultList();
        System.out.println("Found categories: " + categories.size());
        em.close();
        return categories;
    }

    public Category findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Category category = em.find(Category.class, id);
        em.close();
        return category;
    }

    public void create(Category category) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Category category) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.getTransaction().begin();
            em.remove(category);
            em.getTransaction().commit();
        }
        em.close();
    }
}
