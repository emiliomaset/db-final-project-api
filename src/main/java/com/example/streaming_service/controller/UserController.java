package com.example.streaming_service.controller;

import com.example.streaming_service.model.User;
import com.example.streaming_service.model.EditProfileRequest;
import com.example.streaming_service.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")  // your frontend
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get user by email
    @GetMapping("/by-email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user);
    }

    // Update user profile
    @PutMapping("/by-email/{email}")
    public ResponseEntity<User> updateUserByEmail(
            @PathVariable String email,
            @RequestBody User updatedUser) {

        User existing = userRepository.findByEmail(email);
        if (existing == null)
            return ResponseEntity.notFound().build();

        existing.setName(updatedUser.getName());
        existing.setStreetName(updatedUser.getStreetName());
        existing.setCity(updatedUser.getCity());
        existing.setState(updatedUser.getState());
        existing.setZip(updatedUser.getZip());
        existing.setPhoneNum(updatedUser.getPhoneNum());

        if (updatedUser.getPassword() != null &&
                !updatedUser.getPassword().isBlank()) {
            existing.setPassword(updatedUser.getPassword());
        }

        userRepository.save(existing);
        return ResponseEntity.ok(existing);
    }

    // Support POST for clients that send profile edits as POST (avoid HTTP 405)
    @PostMapping("/by-email/{email}")
    public ResponseEntity<User> updateUserByEmailPost(
            @PathVariable String email,
            @RequestBody EditProfileRequest request) {

        User existing = userRepository.findByEmail(email);
        if (existing == null)
            return ResponseEntity.notFound().build();

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
