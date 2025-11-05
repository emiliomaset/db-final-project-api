package com.example.streaming_service.service;

import com.example.streaming_service.model.Administrator;
import com.example.streaming_service.model.Member;
import com.example.streaming_service.repository.AdminRepository;
import com.example.streaming_service.repository.MemberRepository;
import com.example.streaming_service.model.User;
import com.example.streaming_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService
{

    @Autowired
    private UserRepository userRepository;
    public User checkLogin(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Autowired
    private AdminRepository adminRepository;
    public Administrator checkIfAdmin(String userId) {
        return adminRepository.findByUserId(userId);
    }

    @Autowired
    private MemberRepository memberRepository;
    public Member checkIfMember(String userId) {
        return memberRepository.findByUserId(userId);
    }

}
