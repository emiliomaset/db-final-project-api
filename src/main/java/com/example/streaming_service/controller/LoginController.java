package com.example.streaming_service.controller;

import com.example.streaming_service.model.Administrator;
import com.example.streaming_service.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.streaming_service.model.User;
import com.example.streaming_service.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController
{
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User request) {
        System.out.println("HEREEE" + request.toString());
        User user = loginService.checkLogin(request.getEmail(), request.getPassword());
        System.out.println(user);

        if (user != null) { // if found in user table
            Member member = loginService.checkIfMember(user.getUserId());
            if (member != null) {
                return ResponseEntity.ok("Member");
            }
            else {
                Administrator admin = loginService.checkIfAdmin(user.getUserId());

                if (admin != null) {
                    return ResponseEntity.ok("Admin");
                }
            }


    }
        return ResponseEntity.status(401).body("Invalid email or password");
    }
}
