package com.example.sigeve.SIGEVE.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Shippers")
public class Shipper {
    @Id
    @Column(name = "ShipperID")
    private String id;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "Phone")
    private String phone;

    public String getId() { return id;}
    public void setId(String id) {this.id = id;}
    public String getCompanyName() {return companyName;}
    public void setCompanyName(String companyName) {this.companyName = companyName;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
}
