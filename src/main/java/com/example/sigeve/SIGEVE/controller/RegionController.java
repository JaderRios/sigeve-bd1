package com.example.sigeve.SIGEVE.controller;

import com.example.sigeve.SIGEVE.model.Region;
import com.example.sigeve.SIGEVE.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public Page<Region> list(Pageable pageable) {
        return regionService.list(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getById(@PathVariable String id) {
        Region region = regionService.getById(id);
        return region != null ? ResponseEntity.ok(region) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Region> create(@RequestBody Region region) {
        Region created = regionService.create(region);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> update(@PathVariable String id, @RequestBody Region region) {
        try {
            Region updated = regionService.update(id, region);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = regionService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
