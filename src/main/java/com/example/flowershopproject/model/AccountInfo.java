package com.example.flowershopproject.model;

public class AccountInfo {
    private String userName;
    private boolean isActive;
    private String email;
    private String phoneNumber;
    private String name;
    private String lastName;
    private String address;
    private String city;

    public AccountInfo(String userName, boolean isActive, String email, String phoneNumber, String name,
                       String lastName, String address, String city) {
        this.userName = userName;
        this.isActive = isActive;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
