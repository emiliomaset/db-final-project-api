package com.example.streaming_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HealthController {

    private final JdbcTemplate jdbcTemplate;

    public HealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }


    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "UP");
        String dbStatus = "UP";
        String error = null;
        try {
            Integer one = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            if (one == null || one != 1) {
                dbStatus = "DOWN";
                body.put("status", "DEGRADED");
            }
        } catch (Exception ex) {
            dbStatus = "DOWN";
            body.put("status", "DOWN");
            error = ex.getClass().getSimpleName() + ": " + ex.getMessage();
        }
        body.put("db", dbStatus);
        if (error != null) body.put("error", error);
        return ResponseEntity.ok(body);
    }
}
