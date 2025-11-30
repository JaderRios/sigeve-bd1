package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.Employees;
import com.example.sigeve.SIGEVE.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepository employeesRepository;

    // ============================
    // VALIDACIONES
    // ============================
    private void validateEmployee(Employees emp) {

        if (emp.getFirstName() == null || emp.getFirstName().trim().isEmpty())
            throw new IllegalArgumentException("El nombre es obligatorio.");

        if (emp.getLastName() == null || emp.getLastName().trim().isEmpty())
            throw new IllegalArgumentException("El apellido es obligatorio.");

        // --- LIMITES EXACTOS SEGÚN NORTHWIND ---
        validateLength(emp.getFirstName(), 10, "FirstName");
        validateLength(emp.getLastName(), 20, "LastName");
        validateLength(emp.getTitle(), 30, "Title");
        validateLength(emp.getTitleOfCourtesy(), 25, "TitleOfCourtesy");
        validateLength(emp.getAddress(), 60, "Address");
        validateLength(emp.getCity(), 15, "City");
        validateLength(emp.getRegion(), 15, "Region");
        validateLength(emp.getPostalCode(), 10, "PostalCode");
        validateLength(emp.getCountry(), 15, "Country");
        validateLength(emp.getHomePhone(), 24, "HomePhone");
        validateLength(emp.getExtension(), 4, "Extension");
        validateLength(emp.getPhotoPath(), 255, "PhotoPath");

        // Limpieza
        emp.setFirstName(emp.getFirstName().trim());
        emp.setLastName(emp.getLastName().trim());
    }

    private void validateLength(String value, int limit, String field) {
        if (value != null && value.length() > limit) {
            throw new IllegalArgumentException(
                    "El campo '" + field + "' excede el tamaño máximo permitido (" + limit + ")."
            );
        }
    }

    // ============================
    // LISTAR EMPLEADOS PAGINADO
    // ============================
    public Page<Employees> list(Pageable pageable) {
        return employeesRepository.list(pageable);
    }

    // ============================
    // OBTENER POR ID
    // ============================
    public Employees getById(Integer id) {
        return employeesRepository.getById(id);
    }

    // ============================
    // CREAR EMPLEADO
    // ============================
    public Employees create(Employees emp) {

        validateEmployee(emp); // ⬅ validación antes de guardar

        return employeesRepository.create(emp);
    }

    // ============================
    // ACTUALIZAR EMPLEADO
    // ============================
    public Employees update(Integer id, Employees emp) {

        validateEmployee(emp);  // ⬅ validación antes de actualizar

        return employeesRepository.update(id, emp);
    }

    // ============================
    // ELIMINAR EMPLEADO
    // ============================
    public boolean delete(Integer id) {
        return employeesRepository.delete(id);
    }
}
