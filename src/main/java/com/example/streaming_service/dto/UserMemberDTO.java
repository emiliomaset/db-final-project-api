package com.example.streaming_service.dto;

import com.example.streaming_service.config.CustomJSONDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;



public class UserMemberDTO {
    private String email;
    private String name;

    @JsonDeserialize(using = CustomJSONDeserializer.class)
    private String street_name;

    @JsonDeserialize(using = CustomJSONDeserializer.class)
    private String city;

    @JsonDeserialize(using = CustomJSONDeserializer.class)
    private String state;

    @JsonDeserialize(using = CustomJSONDeserializer.class)
    private String zip;

    @JsonDeserialize(using = CustomJSONDeserializer.class)
    private String phone_num;

    private String password;
    private String subscription_type;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubscription_type() {
        return subscription_type;
    }

    public void setSubscription_type(String subscription_type) {
        this.subscription_type = subscription_type;
    }


}

