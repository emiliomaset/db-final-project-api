package com.example.streaming_service.repository;

import com.example.streaming_service.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series, String> {
    Series findByContentId(String contentId);
}
