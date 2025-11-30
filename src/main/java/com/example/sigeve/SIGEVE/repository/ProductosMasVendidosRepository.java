package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.ProductosMasVendidos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductosMasVendidosRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<ProductosMasVendidos> obtenerProductosMasVendidos() {

        String sql =
                "SELECT p.ProductName, " +
                        "       SUM(od.Quantity) AS TotalUnitsSold " +
                        "FROM Products p " +
                        "JOIN [Order Details] od ON p.ProductID = od.ProductID " +
                        "GROUP BY p.ProductName " +
                        "ORDER BY TotalUnitsSold DESC";

        Query query = em.createNativeQuery(sql, "ProductosMasVendidosMapping");
        return query.getResultList();
    }
}
