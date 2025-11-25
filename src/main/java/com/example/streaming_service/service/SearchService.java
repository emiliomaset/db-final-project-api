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

    public List<Map<String, Object>> search(String query, boolean awardCheck, boolean watchedCheck, String userID) {
        String sql = "";

        //Neither box is checked
        if(!awardCheck && !watchedCheck){
            sql += """
                    SELECT DISTINCT c.title,
                                    c.genre,
                                    c.release_date,
                                    c.imdb_link
                    FROM content c
                    LEFT JOIN movie m
                           ON c.content_id = m.content_id
                    LEFT JOIN series s
                           ON c.content_id = s.content_id
                                        
                    -- Join directors
                    LEFT JOIN directs d
                           ON c.content_id = d.content_id
                    LEFT JOIN person dp
                           ON d.p_id = dp.p_id
                                        
                    -- Join actors
                    LEFT JOIN plays_in pi
                           ON c.content_id = pi.content_id
                    LEFT JOIN person ap
                           ON pi.p_id = ap.p_id
                    """;
            if(!query.isEmpty()){
                sql += "WHERE";
                sql += " c.title LIKE CONCAT('%', '" + query + "' ,'%')";
                sql += " OR c.genre LIKE CONCAT('%', '" + query + "', '%')";
                sql += " OR ap.name LIKE CONCAT('%', '" + query + "', '%')";
                sql += " OR dp.name LIKE CONCAT('%', '" + query + "', '%')";
            }
            sql += ";";
            return jdbcTemplate.queryForList(sql);
        }

        else if(awardCheck && !watchedCheck){
            sql += """
                    SELECT DISTINCT c.title,
                                    c.genre,
                                    c.release_date,
                                    c.imdb_link
                    FROM content c
                    
                    -- Filters non-award winners out
                    INNER JOIN award aw
                            ON c.content_id = aw.content_id
                            
                    LEFT JOIN movie m
                           ON c.content_id = m.content_id
                    LEFT JOIN series s
                           ON c.content_id = s.content_id
                                        
                    -- Join directors
                    LEFT JOIN directs d
                           ON c.content_id = d.content_id
                    LEFT JOIN person dp
                           ON d.p_id = dp.p_id
                                        
                    -- Join actors
                    LEFT JOIN plays_in pi
                           ON c.content_id = pi.content_id
                    LEFT JOIN person ap
                           ON pi.p_id = ap.p_id
                    """;
            if(!query.isEmpty()){
                sql += "WHERE";
                sql += " c.title LIKE CONCAT('%', '" + query + "' ,'%')";
                sql += " OR c.genre LIKE CONCAT('%', '" + query + "', '%')";
                sql += " OR ap.name LIKE CONCAT('%', '" + query + "', '%')";
                sql += " OR dp.name LIKE CONCAT('%', '" + query + "', '%')";
            }
            sql += ";";
            return jdbcTemplate.queryForList(sql);
        }
        else if(!awardCheck && watchedCheck) {
            sql += """
                    SELECT DISTINCT c.title,
                                    c.genre,
                                    c.release_date,
                                    c.imdb_link
                    FROM content c
                            
                    LEFT JOIN movie m
                           ON c.content_id = m.content_id
                    LEFT JOIN series s
                           ON c.content_id = s.content_id
                                        
                    -- Join directors
                    LEFT JOIN directs d
                           ON c.content_id = d.content_id
                    LEFT JOIN person dp
                           ON d.p_id = dp.p_id
                                        
                    -- Join actors
                    LEFT JOIN plays_in pi
                           ON c.content_id = pi.content_id
                    LEFT JOIN person ap
                           ON pi.p_id = ap.p_id
                           
                    -- Filter out content that user has watched before
                    LEFT JOIN movie_history mh
                           ON mh.content_id = c.content_id""";
            sql += " AND mh.user_id = " + userID;
            sql += " LEFT JOIN episode_history eh";
            sql += " ON eh.content_id = c.content_id";
            sql += " AND eh.user_id = " + userID;
            if(!query.isEmpty()){
                sql += " WHERE";
                sql += " (c.title LIKE CONCAT('%', '" + query + "' ,'%')";
                sql += " OR c.genre LIKE CONCAT('%', '" + query + "', '%')";
                sql += " OR ap.name LIKE CONCAT('%', '" + query + "', '%')";
                sql += " OR dp.name LIKE CONCAT('%', '" + query + "', '%'))";
                sql += " AND mh.content_id IS NULL";
                sql += " AND eh.content_id IS NULL";
            }
            else{
                sql += " WHERE";
                sql += " mh.content_id IS NULL";
                sql += " AND eh.content_id IS NULL";
            }

            //sql += " AND c.content_id NOT mh.content_id";
            sql += ";";
            return jdbcTemplate.queryForList(sql);
        }

        //Both boxes checked
        else{
            sql += """
                    SELECT DISTINCT c.title,
                                    c.genre,
                                    c.release_date,
                                    c.imdb_link
                    FROM content c
                    
                    -- Filters non-award winners out
                    INNER JOIN award aw
                            ON c.content_id = aw.content_id
                            
                    LEFT JOIN movie m
                           ON c.content_id = m.content_id
                    LEFT JOIN series s
                           ON c.content_id = s.content_id
                                        
                    -- Join directors
                    LEFT JOIN directs d
                           ON c.content_id = d.content_id
                    LEFT JOIN person dp
                           ON d.p_id = dp.p_id
                                        
                    -- Join actors
                    LEFT JOIN plays_in pi
                           ON c.content_id = pi.content_id
                    LEFT JOIN person ap
                           ON pi.p_id = ap.p_id
                           
                    -- Filter out content that user has watched before
                    LEFT JOIN movie_history mh
                           ON mh.content_id = c.content_id""";
            sql += " AND mh.user_id = " + userID;
            sql += " LEFT JOIN episode_history eh";
            sql += " ON eh.content_id = c.content_id";
            sql += " AND eh.user_id = " + userID;
            if(!query.isEmpty()){
                sql += " WHERE";
                sql += " (c.title LIKE CONCAT('%', '" + query + "' ,'%')";
                sql += " OR c.genre LIKE CONCAT('%', '" + query + "', '%')";
                sql += " OR ap.name LIKE CONCAT('%', '" + query + "', '%')";
                sql += " OR dp.name LIKE CONCAT('%', '" + query + "', '%'))";
                sql += " AND mh.content_id IS NULL";
                sql += " AND eh.content_id IS NULL";
            }
            else{
                sql += " WHERE";
                sql += " mh.content_id IS NULL";
                sql += " AND eh.content_id IS NULL";
            }
            sql += ";";
            return jdbcTemplate.queryForList(sql);
        }
    }
}
