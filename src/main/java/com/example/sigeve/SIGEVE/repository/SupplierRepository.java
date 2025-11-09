package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.Supplier;
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
public class SupplierRepository {

    @PersistenceContext
    private EntityManager em;

    public Page<Supplier> list(Pageable pageable) {
        Query query = em.createNativeQuery(
                "SELECT * FROM Suppliers ORDER BY SupplierID " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", Supplier.class);
        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());
        List<Supplier> suppliers = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Suppliers");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(suppliers, pageable, total);
    }

    public Supplier getById(String id) {
        Query query = em.createNativeQuery("SELECT * FROM Suppliers WHERE SupplierID = :id", Supplier.class);
        query.setParameter("id", id);
        return (Supplier) query.getSingleResult();
    }

    public Supplier create(Supplier supplier) {
        em.persist(supplier);
        return supplier;
    }

    public Supplier update(String id, Supplier supplier) {
        Query query = em.createNativeQuery(
                "UPDATE Suppliers SET CompanyName = :companyName, ContactName = :contactName, ContactTitle = :contactTitle, Address = :address, City = :city, " +
                        "Region = :region, PostalCode = :postalCode, Country = :country," +
                        "Phone = :phone, Fax = :fax, HomePage = :homePage  WHERE supplierID = :id");
        query.setParameter("companyName", supplier.getCompanyName());
        query.setParameter("contactName", supplier.getContactName());
        query.setParameter("contactTitle", supplier.getContactTitle());
        query.setParameter("address", supplier.getAddress());
        query.setParameter("city", supplier.getCity());
        query.setParameter("region", supplier.getRegion());
        query.setParameter("postalCode", supplier.getPostalCode());
        query.setParameter("country", supplier.getCountry());
        query.setParameter("phone", supplier.getPhone());
        query.setParameter("fax", supplier.getFax());
        query.setParameter("homePage", supplier.getHomePage());
        query.setParameter("id", id);
        query.executeUpdate();
        return getById(id);
    }

    public boolean delete(String id) {
        Query query = em.createNativeQuery("DELETE FROM Suppliers WHERE SupplierID = :id");
        query.setParameter("id", id);
        return query.executeUpdate() > 0;
    }

    public Long count() {
        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Suppliers");
        return ((Number) countQuery.getSingleResult()).longValue();
    }

}
