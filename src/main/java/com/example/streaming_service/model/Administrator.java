package com.example.streaming_service.model;

import jakarta.persistence.*;

@Entity
public class Administrator {

    @Id
    private String userId; //Primary key

    @Column(
            name = "admin_role"
    )
    private String adminRole;


    public String getUserId() {
        return userId;
    }

    public String getAdminRole() {
        return adminRole;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }
}
