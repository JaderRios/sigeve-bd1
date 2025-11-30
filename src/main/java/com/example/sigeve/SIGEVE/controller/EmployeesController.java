package com.example.sigeve.SIGEVE.controller;

import com.example.sigeve.SIGEVE.model.Employees;
import com.example.sigeve.SIGEVE.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")

public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    // ===============================
    // LISTAR CON PAGINACIÓN
    // ===============================
    @GetMapping
    public ResponseEntity<Page<Employees>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employees> result = employeesService.list(pageable);
        return ResponseEntity.ok(result);
    }

    // ===============================
    // OBTENER POR ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            Employees emp = employeesService.getById(id);
            return ResponseEntity.ok(emp);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Empleado no encontrado");
        }
    }

    // ===============================
    // CREAR EMPLEADO
    // ===============================
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employees emp) {
        try {
            Employees created = employeesService.create(emp);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error al crear empleado");
        }
    }

    // ===============================
    // ACTUALIZAR EMPLEADO
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Employees emp) {
        try {
            Employees updated = employeesService.update(id, emp);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error al actualizar empleado");
        }
    }

    // ===============================
    // ELIMINAR EMPLEADO
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            boolean deleted = employeesService.delete(id);
            if (deleted) {
                return ResponseEntity.ok("Empleado eliminado");
            } else {
                return ResponseEntity.status(404).body("Empleado no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error al eliminar empleado");
        }
    }
}
