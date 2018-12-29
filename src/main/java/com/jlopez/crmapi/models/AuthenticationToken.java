package com.jlopez.crmapi.models;

public class AuthenticationToken {

    private String token;

    public AuthenticationToken() {

    }

    public AuthenticationToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
