package com.example.streaming_service.repository;

import com.example.streaming_service.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByUserId(String userId);
}
