package com.example.sigeve.SIGEVE.controller;

import com.example.sigeve.SIGEVE.model.PAHistorialPedidos;
import com.example.sigeve.SIGEVE.service.PAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedimientos")
public class PAController {

    @Autowired
    private PAService paService;

    @GetMapping("historial-cliente/{id}")
    public ResponseEntity<List<PAHistorialPedidos>> getById(@PathVariable String id) {
        List<PAHistorialPedidos> historial = paService.getHistorial(id);
        return historial != null ? ResponseEntity.ok(historial) : ResponseEntity.notFound().build();
    }
}