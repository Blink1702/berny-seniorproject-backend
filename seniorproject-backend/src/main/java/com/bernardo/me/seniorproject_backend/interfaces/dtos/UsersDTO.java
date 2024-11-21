package com.bernardo.me.seniorproject_backend.interfaces.dtos;

public class UsersDTO {
    private String username;
    private String password;
    private String token;

    public UsersDTO() {
        token = "";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
