package com.example.streaming_service.repository;

import com.example.streaming_service.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, String> {
    List<Episode> findByContentIdAndSeasonNum(String contentId, short seasonNum);

    int countByContentId(String contentId);
}
