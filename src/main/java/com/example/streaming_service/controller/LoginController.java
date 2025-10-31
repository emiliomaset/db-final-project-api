package com.example.streaming_service.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.streaming_service.model.UserAccount;
import com.example.streaming_service.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController
{
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserAccount request)
    {
        UserAccount user = loginService.checkLogin(request.getEmail(), request.getPassword());

        if(user != null)
            return ResponseEntity.ok("Successful login for: " + user.getEmail());
        else
            return ResponseEntity.status(401).body("Invalid email or password");
    }
}
