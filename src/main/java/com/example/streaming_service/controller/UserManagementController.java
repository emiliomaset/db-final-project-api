package com.example.streaming_service.controller;

import com.example.streaming_service.dto.UserMemberDTO;
import com.example.streaming_service.repository.MemberRepository;
import com.example.streaming_service.repository.UserRepository;
import com.example.streaming_service.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserManagementController {


    @Autowired
    private UserManagementService userManagementService;

    @GetMapping("/getallmembers")
    public ResponseEntity<?> getAllMembers() {
        List<?> allMembers = userManagementService.getAllMembers();

        if (allMembers.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allMembers, HttpStatus.OK);
        }

    } // end of getAllMembers function

    @PostMapping("/removemember/{userId}")
    public ResponseEntity<HttpStatus> removeAMember(@PathVariable String userId) {
        return userManagementService.removeAMember(userId);
    }

    @PostMapping("/addmember")
    public ResponseEntity<String> addAMember(@RequestBody UserMemberDTO userMemberDTO) {
        return userManagementService.addAMember(userMemberDTO);
    }

} // end of UserManagementController
