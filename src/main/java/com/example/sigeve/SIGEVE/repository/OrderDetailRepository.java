package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.OrderDetail;
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
public class OrderDetailRepository {

    @PersistenceContext
    private EntityManager em;

    public Page<OrderDetail> list(Pageable pageable) {
        Query query = em.createNativeQuery(
                "SELECT * FROM [Order Details] ORDER BY OrderID, ProductID " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", OrderDetail.class);
        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());
        List<OrderDetail> orderDetails = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM [Order Details]");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(orderDetails, pageable, total);
    }

    public OrderDetail getById(String id) {
        // El ID viene como "orderId-productId"
        String[] ids = id.split("-");
        if (ids.length != 2) {
            return null;
        }

        Query query = em.createNativeQuery(
                "SELECT * FROM [Order Details] WHERE OrderID = :orderId AND ProductID = :productId",
                OrderDetail.class);
        query.setParameter("orderId", Integer.parseInt(ids[0]));
        query.setParameter("productId", Integer.parseInt(ids[1]));

        try {
            return (OrderDetail) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public OrderDetail create(OrderDetail orderDetail) {
        String sql = "INSERT INTO [Order Details] (" +
                "OrderID, ProductID, UnitPrice, Quantity, Discount " +
                ") VALUES (?, ?, ?, ?, ?)";

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, orderDetail.getOrderId());
        query.setParameter(2, orderDetail.getProductId());
        query.setParameter(3, orderDetail.getUnitPrice());
        query.setParameter(4, orderDetail.getQuantity());
        query.setParameter(5, orderDetail.getDiscount());
        query.executeUpdate();

        return orderDetail;
    }

    public OrderDetail update(String id, OrderDetail orderDetail) {
        // El ID viene como "orderId-productId"
        String[] ids = id.split("-");
        if (ids.length != 2) {
            throw new RuntimeException("ID inválido");
        }

        Query query = em.createNativeQuery(
                "UPDATE [Order Details] SET UnitPrice = :unitPrice, " +
                        "Quantity = :quantity, Discount = :discount " +
                        "WHERE OrderID = :orderId AND ProductID = :productId");
        query.setParameter("unitPrice", orderDetail.getUnitPrice());
        query.setParameter("quantity", orderDetail.getQuantity());
        query.setParameter("discount", orderDetail.getDiscount());
        query.setParameter("orderId", Integer.parseInt(ids[0]));
        query.setParameter("productId", Integer.parseInt(ids[1]));

        int updated = query.executeUpdate();
        if (updated == 0) {
            throw new RuntimeException("OrderDetail no encontrado");
        }

        return getById(id);
    }

    public Long count() {
        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM [Order Details]");
        return ((Number) countQuery.getSingleResult()).longValue();
    }

    public boolean delete(String id) {
        // El ID viene como "orderId-productId"
        String[] ids = id.split("-");
        if (ids.length != 2) {
            return false;
        }

        Query query = em.createNativeQuery(
                "DELETE FROM [Order Details] WHERE OrderID = :orderId AND ProductID = :productId");
        query.setParameter("orderId", Integer.parseInt(ids[0]));
        query.setParameter("productId", Integer.parseInt(ids[1]));

        return query.executeUpdate() > 0;
    }
}