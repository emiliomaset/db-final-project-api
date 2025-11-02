package com.example.streaming_service.model;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    private String userId; //Primary key

    @Column(
            name = "subscription_type"
    )
    private String subscriptionType;

    @Column(
            name = "num_available_logins"
    )
    private short numAvailableLogins;


    public Member() {
    }

    public String getUserId() {
        return userId;
    }

    public String getSubscription_type() {
        return subscriptionType;
    }

    public short getNumAvailableLogins() {
        return numAvailableLogins;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSubscription_type(String subscription_type) {
        this.subscriptionType = subscription_type;
    }

    public void setNumAvailableLogins(short num_available_logins) {
        this.numAvailableLogins = num_available_logins;
    }
}
