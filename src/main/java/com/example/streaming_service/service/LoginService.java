package com.example.streaming_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.streaming_service.model.User;
import com.example.streaming_service.repository.UserRepository;

import java.util.List;

@Service
public class LoginService
{
    @Autowired
    private UserRepository userRepository;

    public User checkLogin(String email, String password)
    {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> getAllMyEntities() {
        return userRepository.findAll();
    }
}
