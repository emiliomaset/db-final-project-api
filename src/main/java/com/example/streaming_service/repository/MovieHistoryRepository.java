package com.example.streaming_service.repository;

import com.example.streaming_service.model.MovieHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieHistoryRepository extends JpaRepository<MovieHistory, String> {
    int countByContentId(String contentId);
}
