package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.Product;
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
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public Page<Product> list(Pageable pageable) {
        Query query = em.createNativeQuery(
                "SELECT * FROM Products ORDER BY ProductID " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", Product.class);
        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());
        List<Product> products = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Products");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(products, pageable, total);
    }

    public Product getById(String id) {
        Query query = em.createNativeQuery("SELECT * FROM Products WHERE ProductID = :id", Product.class);
        query.setParameter("id", id);
        return (Product) query.getSingleResult();
    }

    public Product create(Product product) {
        em.persist(product);
        return product;
    }

    public Product update(String id, Product product) {
        Query query = em.createNativeQuery(
                "UPDATE Products SET ProductName = :productName, SupplierID = :supplierId, CategoryID = :categoryId, QuantityPerUnit = :quantityPerUnit, UnitPrice = :unitPrice, " +
                        "UnitsInStock = :unitsInStock, UnitsOnOrder = :unitsOnOrder, ReorderLevel = :reorderLevel," +
                        "Discontinued = :discontinued  WHERE ProductID = :id");
        query.setParameter("productName", product.getProductName());
        query.setParameter("supplierId", product.getSupplierId());
        query.setParameter("categoryId", product.getCategoryId());
        query.setParameter("quantityPerUnit", product.getQuantityPerUnit());
        query.setParameter("unitPrice", product.getUnitPrice());
        query.setParameter("unitsInStock", product.getUnitsInStock());
        query.setParameter("unitsOnOrder", product.getUnitsOnOrder());
        query.setParameter("reorderLevel", product.getReorderLevel());
        query.setParameter("discontinued", product.getDiscontinued());
        query.setParameter("id", id);
        query.executeUpdate();
        return getById(id);
    }

    public boolean delete(String id) {
        Query query = em.createNativeQuery("DELETE FROM Products WHERE ProductID = :id");
        query.setParameter("id", id);
        return query.executeUpdate() > 0;
    }

    public Long count() {
        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Products");
        return ((Number) countQuery.getSingleResult()).longValue();
    }

}
