package com.example.sigeve.SIGEVE.controller;

import com.example.sigeve.SIGEVE.model.Shipper;
import com.example.sigeve.SIGEVE.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shippers")

public class ShipperController {

    @Autowired
    private ShipperService shipperService;

    @GetMapping
    public Page<Shipper> list(Pageable pageable) {
        return shipperService.list(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipper> getById(@PathVariable String id) {
        Shipper shipper = shipperService.getById(id);
        return shipper != null ? ResponseEntity.ok(shipper) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Shipper> create(@RequestBody Shipper shipper) {
        Shipper created = shipperService.create(shipper);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipper> update(@PathVariable String id, @RequestBody Shipper shipper) {
        try {
            Shipper updated = shipperService.update(id, shipper);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = shipperService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}