package com.example.streaming_service.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiRequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();

        // Only log API calls to avoid noise
        if (uri != null && uri.startsWith("/api")) {
            String method = request.getMethod();
            String query = request.getQueryString();
            System.out.println("[DEBUG_LOG] Incoming API request: " + method + " " + uri + (query != null ? ("?" + query) : ""));
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            if (uri != null && uri.startsWith("/api")) {
                System.out.println("[DEBUG_LOG] API response status: " + response.getStatus() + " for " + request.getMethod() + " " + uri);
            }
        }
    }
}
