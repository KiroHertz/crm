package com.jlopez.crmapi.entities;

import com.jlopez.crmapi.models.CustomerCreationRequest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @NotNull(message = "Name field is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Surname field is required")
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull(message = "Email field is required")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne()
    @JoinColumn(name = "created_by", columnDefinition = "long", nullable = false)
    private User createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne()
    @JoinColumn(name = "updated_by", columnDefinition = "long")
    private User updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdByUserId) {
        User createdByUser = new User();
        createdByUser.setId(createdByUserId);

        this.createdBy = createdByUser;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedByUserId) {
        User modifiedByUser = new User();
        modifiedByUser.setId(updatedByUserId);

        this.updatedBy = modifiedByUser;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    void preInsert() {

        this.updatedAt = new Date();

        if (this.createdAt == null) {
            this.createdAt = this.updatedAt;
        }

    }

    public static Customer fromCreationRequest(CustomerCreationRequest creationRequest) {
        Customer customer = new Customer();
        customer.setName(creationRequest.getName());
        customer.setSurname(creationRequest.getSurName());
        customer.setEmail(creationRequest.getEmail());
        return customer;
    }
}