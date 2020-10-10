package com.example.tubes.Model;

public class UserHelper {

        String name, username, email, number, password;

    public UserHelper(String name, String username, String email, String number) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.number = number;
    }

    public UserHelper() {

    }

    public UserHelper(String name, String username, String email, String number, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.number = number;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
