package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.PAHistorialPedidos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PARepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<PAHistorialPedidos> historialPedidosPorCliente(String customerId) {

        List<PAHistorialPedidos> lista = new ArrayList<>();

        try {
            StoredProcedureQuery query = entityManager
                    .createStoredProcedureQuery("PA_HISTORIAL_PEDIDOS_CLIENTE");

            query.registerStoredProcedureParameter("CustomerID", String.class, jakarta.persistence.ParameterMode.IN);
            query.setParameter("CustomerID", customerId);

            List<Object[]> results = query.getResultList();

            for (Object[] row : results) {
                PAHistorialPedidos dto = new PAHistorialPedidos();

                dto.setOrderID((Integer) row[0]);
                dto.setOrderDate(row[1] != null ? ((java.sql.Timestamp) row[1]).toLocalDateTime() : null);
                dto.setRequiredDate(row[2] != null ? ((java.sql.Timestamp) row[2]).toLocalDateTime() : null);
                dto.setShippedDate(row[3] != null ? ((java.sql.Timestamp) row[3]).toLocalDateTime() : null);
                dto.setShipVia((Integer) row[4]);
                dto.setFreight((java.math.BigDecimal) row[5]);
                dto.setShipName((String) row[6]);
                dto.setShipAddress((String) row[7]);
                dto.setShipCity((String) row[8]);
                dto.setShipRegion((String) row[9]);
                dto.setShipPostalCode((String) row[10]);
                dto.setShipCountry((String) row[11]);

                lista.add(dto);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando PA_HISTORIAL_PEDIDOS_CLIENTE: " + e.getMessage(), e);
        }

        return lista;
    }
}
