package com.example.sigeve.SIGEVE.model;

import jakarta.persistence.*;

@Entity
@SqlResultSetMapping(
        name = "ProductosMasVendidosMapping",
        classes = @ConstructorResult(
                targetClass = ProductosMasVendidos.class,
                columns = {
                        @ColumnResult(name = "ProductName", type = String.class),
                        @ColumnResult(name = "TotalUnitsSold", type = Integer.class)
                }
        )
)
public class ProductosMasVendidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Integer totalUnitsSold;

    // Constructor usado por el ResultSetMapping
    public ProductosMasVendidos(String productName, Integer totalUnitsSold) {
        this.productName = productName;
        this.totalUnitsSold = totalUnitsSold;
    }

    public ProductosMasVendidos() {} // Constructor vacío obligatorio por JPA

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getTotalUnitsSold() { return totalUnitsSold; }
    public void setTotalUnitsSold(Integer totalUnitsSold) { this.totalUnitsSold = totalUnitsSold; }
}
