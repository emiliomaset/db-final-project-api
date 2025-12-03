package com.example.streaming_service.controller;

import com.example.streaming_service.model.User;
import com.example.streaming_service.model.EditProfileRequest;
import com.example.streaming_service.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:5173")
public class EditProfileController {

    private final UserRepository userRepository;

    public EditProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    public ResponseEntity<User> getProfile(@RequestParam("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


    @GetMapping("/{email}")
    public ResponseEntity<User> getProfileByPath(@PathVariable String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


    @PutMapping("/{email}")
    @Transactional
    public ResponseEntity<User> updateProfile(@PathVariable String email, @RequestBody User updatedUser) {
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


    @PostMapping("/{email}")
    @Transactional
    public ResponseEntity<User> updateProfilePartial(@PathVariable String email, @RequestBody EditProfileRequest request) {
        User existing = userRepository.findByEmail(email);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (request.getName() != null) existing.setName(request.getName());
        if (request.getStreetName() != null) existing.setStreetName(request.getStreetName());
        if (request.getCity() != null) existing.setCity(request.getCity());
        if (request.getState() != null) existing.setState(request.getState());
        if (request.getZip() != null) existing.setZip(request.getZip());
        if (request.getPhoneNum() != null) existing.setPhoneNum(request.getPhoneNum());

        userRepository.save(existing);
        return ResponseEntity.ok(existing);
    }
}
