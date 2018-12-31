package com.jlopez.crmapi.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerCreationRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String surName;

    @NotNull
    @NotBlank
    private String email;

    public CustomerCreationRequest() {
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
