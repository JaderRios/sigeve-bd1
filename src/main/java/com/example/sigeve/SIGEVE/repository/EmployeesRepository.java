package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EmployeesRepository {

    @PersistenceContext
    private EntityManager em;

    // ============================
    // LISTAR EMPLEADOS (PAGINADO)
    // ============================
    public Page<Employees> list(Pageable pageable) {

        Query query = em.createNativeQuery(
                "SELECT * FROM Employees ORDER BY EmployeeID " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY",
                Employees.class
        );

        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());

        List<Employees> list = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Employees");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(list, pageable, total);
    }

    // ============================
    // OBTENER POR ID
    // ============================
    public Employees getById(Integer id) {
        Query query = em.createNativeQuery(
                "SELECT * FROM Employees WHERE EmployeeID = :id",
                Employees.class
        );
        query.setParameter("id", id);
        return (Employees) query.getSingleResult();
    }

    // ============================
    // CREAR EMPLEADO
    // ============================
    @Transactional
    public Employees create(Employees emp) {

        String sql = "INSERT INTO Employees " +
                "(LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, " +
                "PostalCode, Country, HomePhone, Extension, Notes, ReportsTo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Query query = em.createNativeQuery(sql);

        query.setParameter(1, emp.getLastName());
        query.setParameter(2, emp.getFirstName());
        query.setParameter(3, emp.getTitle());
        query.setParameter(4, emp.getTitleOfCourtesy());
        query.setParameter(5, emp.getBirthDate());
        query.setParameter(6, emp.getHireDate());
        query.setParameter(7, emp.getAddress());
        query.setParameter(8, emp.getCity());
        query.setParameter(9, emp.getRegion());
        query.setParameter(10, emp.getPostalCode());
        query.setParameter(11, emp.getCountry());
        query.setParameter(12, emp.getHomePhone());
        query.setParameter(13, emp.getExtension());
        query.setParameter(14, emp.getNotes());
        query.setParameter(15, emp.getReportsTo());

        query.executeUpdate();
        return emp;
    }

    // ============================
    // ACTUALIZAR EMPLEADO
    // ============================
    @Transactional
    public Employees update(Integer id, Employees emp) {

        String sql = "UPDATE Employees SET " +
                "LastName = :lastName, " +
                "FirstName = :firstName, " +
                "Title = :title, " +
                "TitleOfCourtesy = :courtesy, " +
                "BirthDate = :birthDate, " +
                "HireDate = :hireDate, " +
                "Address = :address, " +
                "City = :city, " +
                "Region = :region, " +
                "PostalCode = :postalCode, " +
                "Country = :country, " +
                "HomePhone = :phone, " +
                "Extension = :ext, " +
                "Notes = :notes, " +
                "ReportsTo = :reportsTo " +
                "WHERE EmployeeID = :id";

        Query query = em.createNativeQuery(sql);

        query.setParameter("lastName", emp.getLastName());
        query.setParameter("firstName", emp.getFirstName());
        query.setParameter("title", emp.getTitle());
        query.setParameter("courtesy", emp.getTitleOfCourtesy());
        query.setParameter("birthDate", emp.getBirthDate());
        query.setParameter("hireDate", emp.getHireDate());
        query.setParameter("address", emp.getAddress());
        query.setParameter("city", emp.getCity());
        query.setParameter("region", emp.getRegion());
        query.setParameter("postalCode", emp.getPostalCode());
        query.setParameter("country", emp.getCountry());
        query.setParameter("phone", emp.getHomePhone());
        query.setParameter("ext", emp.getExtension());
        query.setParameter("notes", emp.getNotes());
        query.setParameter("reportsTo", emp.getReportsTo());
        query.setParameter("id", id);

        query.executeUpdate();
        return getById(id);
    }

    // ============================
    // ELIMINAR EMPLEADO
    // ============================
    public boolean delete(Integer id) {
        Query query = em.createNativeQuery(
                "DELETE FROM Employees WHERE EmployeeID = :id"
        );
        query.setParameter("id", id);
        return query.executeUpdate() > 0;
    }

    // ============================
    // CONTAR EMPLEADOS
    // ============================
    public Long count() {
        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Employees");
        return ((Number) countQuery.getSingleResult()).longValue();
    }
}
