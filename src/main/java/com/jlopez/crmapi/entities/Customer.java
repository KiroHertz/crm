package com.jlopez.crmapi.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne()
    @JoinColumn(columnDefinition = "long", name = "created_by", nullable = false)
    private User createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne()
    @JoinColumn(columnDefinition = "long", name = "updated_by")
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedByUser) {
        this.updatedBy = updatedByUser;
    }

    public void setCreatedBy(Long createdByUserId) {
        User createdByUser = new User();
        createdByUser.setId(createdByUserId);
        this.createdBy = createdByUser;
    }

    public void setModifiedBy(Long modifiedByUserId) {
        User modifiedByUser = new User();
        modifiedByUser.setId(modifiedByUserId);

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
}