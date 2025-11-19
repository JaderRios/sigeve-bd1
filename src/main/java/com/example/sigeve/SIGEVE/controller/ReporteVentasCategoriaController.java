package com.example.sigeve.SIGEVE.controller;

import com.example.sigeve.SIGEVE.model.ReporteVentasCategoria;
import com.example.sigeve.SIGEVE.service.ReporteVentasCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteVentasCategoriaController {

    @Autowired
    private ReporteVentasCategoriaService ventasCategoriaService;

    @GetMapping("/ventas-por-categoria")
    public ResponseEntity<List<ReporteVentasCategoria>> getVentasPorCategoria() {
        return ResponseEntity.ok(ventasCategoriaService.obtenerReporte());
    }
}