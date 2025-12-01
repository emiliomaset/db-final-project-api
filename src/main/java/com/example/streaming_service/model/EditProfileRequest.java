package com.example.streaming_service.model;


public class EditProfileRequest {

    private String name;
    private String streetName;
    private String city;
    private String state;
    private String zip;
    private String phoneNum;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStreetName() { return streetName; }
    public void setStreetName(String streetName) { this.streetName = streetName; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }
}
