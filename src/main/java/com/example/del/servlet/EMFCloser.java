package com.example.del.servlet;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class EMFCloser implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EMF.close();
    }
}
