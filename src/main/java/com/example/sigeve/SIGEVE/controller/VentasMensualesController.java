package com.example.sigeve.SIGEVE.controller;

import com.example.sigeve.SIGEVE.model.VentasMensuales;
import com.example.sigeve.SIGEVE.service.VentasMensualesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class VentasMensualesController {

    @Autowired
    private VentasMensualesService ventasMensualesService;

    @GetMapping("/ventas-mensuales")
    public ResponseEntity<List<VentasMensuales>> getVentasMensuales() {
        List<VentasMensuales> ventas = ventasMensualesService.obtenerReporteMensual();
        return ResponseEntity.ok(ventas);
    }
}
