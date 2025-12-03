package com.example.streaming_service.controller;

import com.example.streaming_service.model.User;
import com.example.streaming_service.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userRepository.findByEmailAndPassword(email, password);

        if (user == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        return ResponseEntity.ok(user);
    }
}
