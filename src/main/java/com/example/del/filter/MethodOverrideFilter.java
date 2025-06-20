package com.example.del.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter("/*")
public class MethodOverrideFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        if ("POST".equals(request.getMethod())) {
            String method = request.getParameter("_method");
            if (method != null) {
                request = new HttpServletRequestWrapper(request) {
                    @Override
                    public String getMethod() {
                        return method.toUpperCase();
                    }
                };
            }
        }
        chain.doFilter(request, res);
    }
}