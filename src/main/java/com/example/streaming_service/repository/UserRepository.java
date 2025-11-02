package com.example.streaming_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.streaming_service.model.User;

public interface UserRepository extends JpaRepository<User, String>
{

    // This automatically generates the SQL:
    // SELECT * FROM user WHERE email = ? AND password = ?
    User findByEmailAndPassword(String email, String password);

}
