package com.example.streaming_service.service;

import com.example.streaming_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchService
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SearchService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> search(String query, boolean awardCheck, boolean watchedCheck) {
        String sql;
        if (query.equals("")) {
            sql = "SELECT * FROM content";
        }
        else {
            sql = "SELECT * FROM content WHERE title LIKE ?";
        }
        if(awardCheck){
            sql += " INNER JOIN award";
        }
        if(watchedCheck){
            sql += """
                    WHERE id NOT IN(
                        SELECT content_id
                        FROM movie_history
                        WHERE content_id IS NOT NULL)
                    """ ;
        }
        sql += ";";
        System.out.println(sql);

        return jdbcTemplate.queryForList(sql);


    }
}
