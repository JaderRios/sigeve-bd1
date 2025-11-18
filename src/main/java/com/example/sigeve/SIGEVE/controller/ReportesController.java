package com.example.sigeve.SIGEVE.controller;
import com.example.sigeve.SIGEVE.model.VistaDetallePedidos;
import com.example.sigeve.SIGEVE.model.VistaVentasClienteRegion;
import com.example.sigeve.SIGEVE.service.VistaDetallePedidoService;
import com.example.sigeve.SIGEVE.service.VistaVentasClienteRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {

    @Autowired
    private VistaDetallePedidoService detallePedidosService;

    @Autowired
    private VistaVentasClienteRegionService ventasClienteRegionService;

    // Endpoint 1: Detalle de Pedidos
    @GetMapping("/detalle-pedidos")
    public ResponseEntity<List<VistaDetallePedidos>> getDetallePedidos() {
        List<VistaDetallePedidos> data = detallePedidosService.getAll();
        return ResponseEntity.ok(data);
    }

    // Endpoint 2: Ventas por Cliente y Región
    @GetMapping("/ventas-cliente-region")
    public ResponseEntity<List<VistaVentasClienteRegion>> getVentasClienteRegion() {
        List<VistaVentasClienteRegion> data = ventasClienteRegionService.getAll();
        return ResponseEntity.ok(data);
    }
}
