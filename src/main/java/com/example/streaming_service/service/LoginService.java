package com.example.streaming_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.streaming_service.model.UserAccount;
import com.example.streaming_service.repository.UserRepository;

@Service
public class LoginService
{
    @Autowired
    private UserRepository userRepository;

    public UserAccount checkLogin(String email, String password)
    {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
