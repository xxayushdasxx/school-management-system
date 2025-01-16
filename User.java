package com.example.hellofx.model;

public class User {
    protected String name;
    protected String registrationNumber;
    protected String password;

    public User(String name, String registrationNumber, String password) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
