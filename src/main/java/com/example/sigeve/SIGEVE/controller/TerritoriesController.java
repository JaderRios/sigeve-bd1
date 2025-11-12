package com.example.sigeve.SIGEVE.controller;

import com.example.sigeve.SIGEVE.model.Territories;
import com.example.sigeve.SIGEVE.service.TerritoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/territories")
public class TerritoriesController {

    @Autowired
    private TerritoriesService territoriesService;

    @GetMapping
    public Page<Territories> list(Pageable pageable) {
        return territoriesService.list(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Territories> getById(@PathVariable String id) {
        Territories territory = territoriesService.getById(id);
        return territory != null ? ResponseEntity.ok(territory) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Territories> create(@RequestBody Territories territory) {
        Territories created = territoriesService.create(territory);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Territories> update(@PathVariable String id, @RequestBody Territories territory) {
        try {
            Territories updated = territoriesService.update(id, territory);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = territoriesService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
