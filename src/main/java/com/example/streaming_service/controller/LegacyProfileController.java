package com.example.streaming_service.controller;

import com.example.streaming_service.model.User;
import com.example.streaming_service.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class LegacyProfileController {

    private final UserRepository userRepository;

    public LegacyProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/by-email")
    public ResponseEntity<User> getByEmailQuery(@RequestParam("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


    @GetMapping("/by-email/{email}")
    public ResponseEntity<User> getByEmailPath(@PathVariable String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


    @PutMapping("/by-email/{email}")
    @Transactional
    public ResponseEntity<User> updateByEmailPut(
            @PathVariable String email,
            @RequestBody User updatedUser
    ) {
        User existing = userRepository.findByEmail(email);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setName(updatedUser.getName());
        existing.setStreetName(updatedUser.getStreetName());
        existing.setCity(updatedUser.getCity());
        existing.setState(updatedUser.getState());
        existing.setZip(updatedUser.getZip());
        existing.setPhoneNum(updatedUser.getPhoneNum());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            existing.setPassword(updatedUser.getPassword());
        }

        userRepository.save(existing);
        return ResponseEntity.ok(existing);
    }
}
