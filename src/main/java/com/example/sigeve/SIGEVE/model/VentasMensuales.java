package com.example.sigeve.SIGEVE.model;

import jakarta.persistence.*;

@Entity
@SqlResultSetMapping(
        name = "VentasMensualesMapping",
        classes = @ConstructorResult(
                targetClass = VentasMensuales.class,
                columns = {
                        @ColumnResult(name = "year", type = Integer.class),
                        @ColumnResult(name = "month", type = Integer.class),
                        @ColumnResult(name = "totalSales", type = Double.class)
                }
        )
)
public class VentasMensuales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;
    private Integer month;
    private Double totalSales;

    // Constructor usado por el ResultSetMapping
    public VentasMensuales(Integer year, Integer month, Double totalSales) {
        this.year = year;
        this.month = month;
        this.totalSales = totalSales;
    }

    public VentasMensuales() {} // Constructor vacío obligatorio por JPA

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }

    public Double getTotalSales() { return totalSales; }
    public void setTotalSales(Double totalSales) { this.totalSales = totalSales; }
}
