package com.example.streaming_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.streaming_service.model.User;

public interface UserRepository extends JpaRepository<User, String>
{
    // Login
    User findByEmailAndPassword(String email, String password);

    // Fetch a user using only the email (used for profile page)
    User findByEmail(String email);
}
