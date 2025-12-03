package com.example.streaming_service.controller;

import com.example.streaming_service.model.User;
import com.example.streaming_service.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {

    private final UserRepository userRepository;

    public MemberController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/members/{userId}")
    public ResponseEntity<?> getMember(@PathVariable String userId) {
        User u = userRepository.findById(userId).orElse(null);
        if (u == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(u);
    }


    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable String userId,
            @RequestBody User updated
    ) {
        User u = userRepository.findById(userId).orElse(null);
        if (u == null) {
            return ResponseEntity.notFound().build();
        }


        if (updated.getName() != null) u.setName(updated.getName());
        if (updated.getStreetName() != null) u.setStreetName(updated.getStreetName());
        if (updated.getCity() != null) u.setCity(updated.getCity());
        if (updated.getState() != null) u.setState(updated.getState());
        if (updated.getZip() != null) u.setZip(updated.getZip());
        if (updated.getPhoneNum() != null) u.setPhoneNum(updated.getPhoneNum());
        if (updated.getPassword() != null) u.setPassword(updated.getPassword());

        userRepository.save(u);

        return ResponseEntity.ok(u);
    }
}
