package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.VentasMensuales;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class VentasMensualesRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<VentasMensuales> obtenerVentasMensuales() {

        String sql =
                "SELECT YEAR(o.OrderDate) AS year, " +
                        "       MONTH(o.OrderDate) AS month, " +
                        "       SUM(od.UnitPrice * od.Quantity * (1 - od.Discount)) AS totalSales " +
                        "FROM Orders o " +
                        "JOIN [Order Details] od ON o.OrderID = od.OrderID " +
                        "GROUP BY YEAR(o.OrderDate), MONTH(o.OrderDate) " +
                        "ORDER BY year, month";

        Query query = em.createNativeQuery(sql, "VentasMensualesMapping");
        return query.getResultList();
    }
}
