package com.example.streaming_service.repository;

import com.example.streaming_service.model.EpisodeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeHistoryRepository extends JpaRepository<EpisodeHistory, String> {
    int countByEpisodeId(String episodeId);
}
