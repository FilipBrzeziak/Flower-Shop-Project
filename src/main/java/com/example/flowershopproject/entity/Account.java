package com.example.flowershopproject.entity;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = -2054386655979281969L;
    @Id
    @Column(name = "User_Name", length = 20, nullable = false)
    private String userName;
    @Column(name = "Encrypted_Password", length = 128, nullable = false)
    private String encryptedPassword;
    @Column(name = "Email", length = 128, nullable = false)
    private String email;
    @Column(name = "Phone", length = 128, nullable = false)
    private String phone;
    @Column(name = "First_Name", length = 128, nullable = false)
    private String firstName;
    @Column(name = "Last_Name", length = 128, nullable = false)
    private String lastName;
    @Column(name = "User_Address", length = 128, nullable = false)
    private String userAddress;
    @Column(name = "User_City", length = 128, nullable = false)
    private String userCity;
    @Column(name = "Active", length = 1, nullable = false)
    private boolean active;
    @Column(name = "User_Role", length = 20, nullable = false)
    private String userRole;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userCity='" + userCity + '\'' +
                ", active=" + active +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
