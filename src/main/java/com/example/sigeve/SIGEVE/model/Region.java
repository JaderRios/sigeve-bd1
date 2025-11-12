package com.example.sigeve.SIGEVE.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Region")
public class Region {
    @Id
    @Column(name = "RegionID")
    private String id;

    @Column (name  = "RegionDescription")
    private String regionDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }
}

