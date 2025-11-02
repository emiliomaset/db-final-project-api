package com.example.streaming_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.streaming_service.model.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, String>
{

    // This automatically generates the SQL:
    // SELECT * FROM user_account WHERE email = ? AND password = ?
    UserAccount findByEmailAndPassword(String email, String password);
}
