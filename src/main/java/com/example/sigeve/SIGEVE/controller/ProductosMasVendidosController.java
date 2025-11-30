package com.example.sigeve.SIGEVE.controller;

import com.example.sigeve.SIGEVE.model.ProductosMasVendidos;
import com.example.sigeve.SIGEVE.service.ProductosMasVendidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ProductosMasVendidosController {

    @Autowired
    private ProductosMasVendidosService productosMasVendidosService;

    @GetMapping("/productos-mas-vendidos")
    public ResponseEntity<List<ProductosMasVendidos>> getProductosMasVendidos() {
        List<ProductosMasVendidos> productos = productosMasVendidosService.obtenerProductosMasVendidos();
        return ResponseEntity.ok(productos);
    }
}
