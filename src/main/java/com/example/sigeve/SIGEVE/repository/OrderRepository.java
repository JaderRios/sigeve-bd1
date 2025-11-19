package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.Order;
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
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public Page<Order> list(Pageable pageable) {
        Query query = em.createNativeQuery(
                "SELECT * FROM Orders ORDER BY OrderID " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", Order.class);
        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());
        List<Order> orders = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Orders");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(orders, pageable, total);
    }

    public Order getById(String id) {
        Query query = em.createNativeQuery("SELECT * FROM Orders WHERE OrderID = :id", Order.class);
        query.setParameter("id", id);
        return (Order) query.getSingleResult();
    }

    public Order create(Order order) {
        String sql = "INSERT INTO Orders (" +
                "CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, " +
                "ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Query query = em.createNativeQuery(sql);

        query.setParameter(1, order.getCustomerId());
        query.setParameter(2, order.getEmployeeId());
        query.setParameter(3, order.getOrderDate());
        query.setParameter(4, order.getRequiredDate());
        query.setParameter(5, order.getShippedDate());
        query.setParameter(6, order.getShipVia());
        query.setParameter(7, order.getFreight());
        query.setParameter(8, order.getShipName());
        query.setParameter(9, order.getShipAddress());
        query.setParameter(10, order.getShipCity());
        query.setParameter(11, order.getShipRegion());
        query.setParameter(12, order.getShipPostalCode());
        query.setParameter(13, order.getShipCountry());

        query.executeUpdate();

        return order;
    }


    public Order update(String id, Order order) {
        Query query = em.createNativeQuery(
                "UPDATE Orders SET OrderDate = :orderDate, RequiredDate = :requiredDate, ShippedDate = :shippedDate, " +
                        "ShipVia = :shipVia, Freight = :freight, ShipName = :shipName, ShipAddress = :shipAddress, " +
                        "ShipCity = :shipCity, ShipRegion = :shipRegion, ShipPostalCode = :shipPostalCode, " +
                        "ShipCountry = :shipCountry WHERE OrderID = :id");
        query.setParameter("orderDate", order.getOrderDate());
        query.setParameter("requiredDate", order.getRequiredDate());
        query.setParameter("shippedDate", order.getShippedDate());
        query.setParameter("shipVia", order.getShipVia());
        query.setParameter("freight", order.getFreight());
        query.setParameter("shipName", order.getShipName());
        query.setParameter("shipAddress", order.getShipAddress());
        query.setParameter("shipCity", order.getShipCity());
        query.setParameter("shipRegion", order.getShipRegion());
        query.setParameter("shipPostalCode", order.getShipPostalCode());
        query.setParameter("shipCountry", order.getShipCountry());
        query.setParameter("id", id);
        query.executeUpdate();
        return getById(id);
    }

    public boolean delete(String id) {



        Query query = em.createNativeQuery("DELETE FROM Orders WHERE OrderID = :id");
        query.setParameter("id", id);
        return query.executeUpdate() > 0;
    }
}