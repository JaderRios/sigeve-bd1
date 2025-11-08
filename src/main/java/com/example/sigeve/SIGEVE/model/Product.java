package com.example.sigeve.SIGEVE.model;

import jakarta.persistence.*;

@Entity
@Table(name="Products")
public class Product {

    @Id
    @Column(name = "ProductID")
    private String id;

    @Column(name = "ProductName")
    private  String productName;

    @Column(name = "SupplierID")
    private  String supplierId;

    @Column(name = "CategoryID")
    private  String categoryId;

    @Column(name = "QuantityPerUnit")
    private  String quantityPerUnit;

    @Column(name = "UnitPrice")
    private  String unitPrice;

    @Column(name = "UnitsInStock")
    private  String unitsInStock;

    @Column(name = "UnitsOnOrder")
    private  String unitsOnOrder;

    @Column(name = "ReorderLevel")
    private  String reorderLevel;

    @Column(name = "Discontinued")
    private  String discontinued;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(String unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(String unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public String getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(String reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

}
