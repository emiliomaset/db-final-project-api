package com.example.streaming_service.model;

import jakarta.persistence.*;

@Entity
public class User
{
    @Id
    private String userId; //Primary key

    private String email;
    private String name;
    private String streetName;
    private String city;
    private String state;
    private String zip;
    private String phoneNum;
    private String password;
    private boolean isAdmin;
    public User() {}

    //Setters
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
public void setAdmin(boolean admin) {
        this.isAdmin = admin;
}
    //Getters
    public String getUserId()
    {
        return userId;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getCity()
    {
        return city;
    }

    public String getStreetName()
    {
        return streetName;
    }

    public String getState()
    {
        return state;
    }

    public String getZip()
    {
        return zip;
    }

    public String getPhoneNum()
    {
        return phoneNum;
    }

    public String getPassword()
    {
        return password;
    }
    public boolean isAdmin(){
        return isAdmin;
    }
}
