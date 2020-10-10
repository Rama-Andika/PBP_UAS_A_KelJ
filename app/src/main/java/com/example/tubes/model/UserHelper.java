package com.example.tubes.model;

public class UserHelper {

    String name, username, number, email, password;

    public UserHelper() {

    }

    public UserHelper(String name, String username, String number, String email, String password) {
        this.name = name;
        this.username = username;
        this.number = number;
        this.email = email;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
