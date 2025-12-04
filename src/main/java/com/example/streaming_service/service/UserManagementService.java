package com.example.streaming_service.service;

import com.example.streaming_service.dto.UserMemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import java.util.Random;

@Service
public class UserManagementService {

    private final JdbcTemplate jdbcTemplate;

    public UserManagementService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HashMap> getAllMembers() {
        String sql = "SELECT * FROM user NATURAL JOIN member";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            final HashMap<String, String> member = new HashMap<>();
            member.put("userId", rs.getString("user_id"));
            member.put("name", rs.getString("name"));
            member.put("email", rs.getString("email"));
            member.put("subscriptionType", rs.getString("subscription_type"));
            return member;
        });
    } // end of getAllMembers function

    public ResponseEntity<HttpStatus> removeAMember(String userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";

        int updateResult = jdbcTemplate.update(sql, userId);

        // trigger
//        DELIMITER $$
//        create trigger removeMemberFromUserTable
//        before delete on user
//        for each row
//        BEGIN
//        DELETE FROM episode_history
//        WHERE OLD.user_id = user_id;
//
//        DELETE FROM movie_history
//        WHERE OLD.user_id = user_id;
//
//        DELETE FROM member
//        WHERE OLD.user_id = user_id;
//        END;$$
        // fires before user is actually deleted from user table so that there is no foreign key violation
        // with member when user is deleted

        if (updateResult == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    } // end of removeAMember function

    public ResponseEntity<String> addAMember(UserMemberDTO userMemberDTO) {
        Random random = new Random();
        String sql;
        String num_available_logins;
        int insertResult;

        String generatedID = "U" + random.nextInt(10000);
        if (userMemberDTO.getSubscription_type().equals("Basic")) {
            num_available_logins = "1";
        } else {
            num_available_logins = "3";
        }


        sql = "INSERT INTO user (user_id, email, name, street_name, city, state, zip, phone_num, password, is_admin) VALUES (?,?,?,?,?,?,?,?,?, ?)";
        insertResult = jdbcTemplate.update(sql, generatedID, userMemberDTO.getEmail(), userMemberDTO.getName(), userMemberDTO.getStreet_name(),
                userMemberDTO.getCity(), userMemberDTO.getState(), userMemberDTO.getZip(),
                userMemberDTO.getPhone_num(), userMemberDTO.getPassword(), 0);

        if (insertResult == 0) {
            return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED); // collision in PKs
        } else {
            sql = "INSERT INTO member (user_id, subscription_type, num_available_logins) VALUES (?, ?, ?)";
            insertResult = jdbcTemplate.update(sql, generatedID, userMemberDTO.getSubscription_type(), num_available_logins);
            if (insertResult == 0) {
                return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
            } else {
                return new ResponseEntity<String>(generatedID, HttpStatus.OK);
            }
        }
    } // end of addAMember function


} // end of UserManagementService
