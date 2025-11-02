package com.example.streaming_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User
{
    @Id
    private String user_id; //Primary key

    private String email;
    private String name;
    private String street_name;
    private String city;
    private String state;
    private String zip;
    private String phone_num;
    private String password;

    public User() {}

    //Setters
    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStreet_name(String street_name)
    {
        this.street_name = street_name;
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

    public void setPhone_num(String phone_num)
    {
        this.phone_num = phone_num;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    //Getters
    public String getUser_id()
    {
        return user_id;
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

    public String getStreet_name()
    {
        return street_name;
    }

    public String getState()
    {
        return state;
    }

    public String getZip()
    {
        return zip;
    }

    public String getPhone_num()
    {
        return phone_num;
    }

    public String getPassword()
    {
        return password;
    }
}
