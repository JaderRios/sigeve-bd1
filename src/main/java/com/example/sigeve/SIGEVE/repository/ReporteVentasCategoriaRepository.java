package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.ReporteVentasCategoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ReporteVentasCategoriaRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<ReporteVentasCategoria> generarReporte() {
        String sql = 
            "SELECT " +
            "    C.CategoryName AS NombreCategoria, " +
            "    COUNT(DISTINCT O.OrderID) AS TotalPedidos, " +
            "    SUM(OD.Quantity) AS TotalUnidadesVendidas, " +
            "    AVG(OD.UnitPrice) AS PrecioPromedioVenta " +
            "FROM Categories C " +
            "JOIN Products P ON C.CategoryID = P.CategoryID " +
            "JOIN \"Order Details\" OD ON P.ProductID = OD.ProductID " +
            "JOIN Orders O ON OD.OrderID = O.OrderID " +
            "GROUP BY C.CategoryName " +
            "ORDER BY TotalUnidadesVendidas DESC";

        Query query = em.createNativeQuery(sql, ReporteVentasCategoria.class);
        return query.getResultList();
    }
}