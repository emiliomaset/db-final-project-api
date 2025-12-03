package com.example.streaming_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "`user`")
public class User
{
    @Id
    @Column(name = "user_id")
    private String userId;

    private String email;
    private String name;

    @Column(name = "street_name")
    private String streetName;

    private String city;
    private String state;
    private String zip;

    @Column(name = "phone_num")
    private String phoneNum;

    private String password;

    public User() {}

    // Setters
    public void setUserId(String userId) { this.userId = userId; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setStreetName(String streetName) { this.streetName = streetName; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setZip(String zip) { this.zip = zip; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }
    public void setPassword(String password) { this.password = password; }

    // Getters
    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getStreetName() { return streetName; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZip() { return zip; }
    public String getPhoneNum() { return phoneNum; }
    public String getPassword() { return password; }
}
