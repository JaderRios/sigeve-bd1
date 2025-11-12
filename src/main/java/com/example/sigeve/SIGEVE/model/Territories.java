package com.example.sigeve.SIGEVE.model;

import jakarta.persistence.*;

@Entity
@Table (name = "Territories")
public class Territories {

    @Id
    @Column(name = "TerritoryID")
    private String id;

    @Column(name = "TerritoryDescription")
    private String territoryDescription;

    @Column(name = "RegionID")
    private Integer regionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}
