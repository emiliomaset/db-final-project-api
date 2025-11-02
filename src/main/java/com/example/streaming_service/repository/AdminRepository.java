package com.example.streaming_service.repository;

import com.example.streaming_service.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Administrator, String> {

    Administrator findByUserId(String userId);
}

