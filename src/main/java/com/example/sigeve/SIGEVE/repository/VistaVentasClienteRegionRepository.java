package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.VistaVentasClienteRegion;
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
public class VistaVentasClienteRegionRepository {

    @PersistenceContext
    private EntityManager em;

    public Page<VistaVentasClienteRegion> list(Pageable pageable) {
        Query query = em.createNativeQuery(
                "SELECT * FROM VISTA_VENTAS_POR_CLIENTE_REGION ORDER BY cliente " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", VistaVentasClienteRegion.class);
        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());

        List<VistaVentasClienteRegion> ventas = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM VISTA_VENTAS_POR_CLIENTE_REGION");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(ventas, pageable, total);
    }

    public VistaVentasClienteRegion getByCliente(String cliente) {
        Query query = em.createNativeQuery(
                "SELECT * FROM VISTA_VENTAS_POR_CLIENTE_REGION WHERE cliente = :cliente",
                VistaVentasClienteRegion.class);
        query.setParameter("cliente", cliente);
        return (VistaVentasClienteRegion) query.getSingleResult();
    }

    public List<VistaVentasClienteRegion> listAll() {
        Query query = em.createNativeQuery(
                "SELECT * FROM VISTA_VENTAS_POR_CLIENTE_REGION " +
                        "ORDER BY pais, region, totalVendido DESC, cliente",
                VistaVentasClienteRegion.class
        );
        return query.getResultList();
    }
}
