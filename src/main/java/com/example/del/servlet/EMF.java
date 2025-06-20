package com.example.del.servlet;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {
    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("delPU");

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }

    public static void close() {
        if (emfInstance.isOpen()) {
            emfInstance.close();
        }
    }
}
