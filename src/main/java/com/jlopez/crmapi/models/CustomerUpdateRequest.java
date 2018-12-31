package com.jlopez.crmapi.models;

import javax.validation.constraints.NotBlank;

public class CustomerUpdateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String surName;

    @NotBlank
    private String email;

    public CustomerUpdateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
