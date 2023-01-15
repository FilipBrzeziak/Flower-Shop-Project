package com.example.flowershopproject.model;

public class AccountInfo {
    private String userName;
    private boolean isActive;

    public AccountInfo(String userName, boolean isActive) {
        this.userName = userName;
        this.isActive = isActive;
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
