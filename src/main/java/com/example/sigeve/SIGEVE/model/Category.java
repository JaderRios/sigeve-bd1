package com.example.sigeve.SIGEVE.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @Column(name = "CategoryID")
    private String id;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "Description")
    private String description;

    @Lob
    @Column(name = "Picture")
    private byte[] picture; // Cambiar de String a byte[]

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
