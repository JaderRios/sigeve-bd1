package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.VistaDetallePedidos;
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
public class VistaDetallePedidoRepository {

    @PersistenceContext
    private EntityManager em;

    // Metodo para listar todos los pedidos con paginación
    public Page<VistaDetallePedidos> list(Pageable pageable) {
        Query query = em.createNativeQuery(
                "SELECT * FROM VISTA_DETALLE_PEDIDOS ORDER BY idPedido " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", VistaDetallePedidos.class);
        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());

        List<VistaDetallePedidos> pedidos = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM VISTA_DETALLE_PEDIDOS");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(pedidos, pageable, total);
    }
    // Metodo para obtener un pedido por su ID
    public VistaDetallePedidos getById(Integer id) {
        Query query = em.createNativeQuery(
                "SELECT * FROM VISTA_DETALLE_PEDIDOS WHERE idPedido = :id", VistaDetallePedidos.class);
        query.setParameter("id", id);
        return (VistaDetallePedidos) query.getSingleResult();
    }

    // Metodo para listar todos los registros de la vista sin paginación
    public List<VistaDetallePedidos> listAll() {
        Query query = em.createNativeQuery("SELECT * FROM VISTA_DETALLE_PEDIDOS", VistaDetallePedidos.class);
        return query.getResultList();
    }
}
