package com.example.sigeve.SIGEVE.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "VISTA_VENTAS_POR_CLIENTE_REGION")
public class VistaVentasClienteRegion {

        @Id
        @Column(name = "cliente")
        private String cliente;

        @Column(name = "contacto")
        private String contacto;

        @Column(name = "ciudad")
        private String ciudad;

        @Column(name = "region")
        private String region;

        @Column(name = "pais")
        private String pais;

        @Column(name = "totalPedidos")
        private Integer totalPedidos;

        @Column(name = "totalVendido")
        private BigDecimal totalVendido;

        @Column(name = "promedioLinea")
        private BigDecimal promedioLinea;

        public String getCliente() {
            return cliente;
        }

        public void setCliente(String cliente) {
            this.cliente = cliente;
        }

        public String getContacto() {
            return contacto;
        }

        public void setContacto(String contacto) {
            this.contacto = contacto;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getPais() {
            return pais;
        }

        public void setPais(String pais) {
            this.pais = pais;
        }

        public Integer getTotalPedidos() {
            return totalPedidos;
        }

        public void setTotalPedidos(Integer totalPedidos) {
            this.totalPedidos = totalPedidos;
        }

        public BigDecimal getTotalVendido() {
            return totalVendido;
        }

        public void setTotalVendido(BigDecimal totalVendido) {
            this.totalVendido = totalVendido;
        }

        public BigDecimal getPromedioLinea() {
            return promedioLinea;
        }

        public void setPromedioLinea(BigDecimal promedioLinea) {
            this.promedioLinea = promedioLinea;
        }
}
