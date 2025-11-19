package com.example.sigeve.SIGEVE.model;

import jakarta.persistence.*;

@Entity
public class ReporteVentasCategoria {

    @Id
    @Column(name = "NombreCategoria")
    private String nombreCategoria;

    @Column(name = "TotalPedidos")
    private Integer totalPedidos;

    @Column(name = "TotalUnidadesVendidas")
    private Integer totalUnidadesVendidas;

    @Column(name = "PrecioPromedioVenta")
    private Double precioPromedioVenta;

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Integer getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(Integer totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    public Integer getTotalUnidadesVendidas() {
        return totalUnidadesVendidas;
    }

    public void setTotalUnidadesVendidas(Integer totalUnidadesVendidas) {
        this.totalUnidadesVendidas = totalUnidadesVendidas;
    }

    public Double getPrecioPromedioVenta() {
        return precioPromedioVenta;
    }

    public void setPrecioPromedioVenta(Double precioPromedioVenta) {
        this.precioPromedioVenta = precioPromedioVenta;
    }
}