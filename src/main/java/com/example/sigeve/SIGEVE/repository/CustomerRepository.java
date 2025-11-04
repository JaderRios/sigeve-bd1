package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.Customer;
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
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public Page<Customer> list(Pageable pageable) {
        Query query = em.createNativeQuery(
                "SELECT * FROM Customers ORDER BY CustomerID " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", Customer.class);
        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());
        List<Customer> customers = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Customers");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(customers, pageable, total);
    }

    public Customer getById(String id) {
        Query query = em.createNativeQuery("SELECT * FROM Customers WHERE CustomerID = :id", Customer.class);
        query.setParameter("id", id);
        return (Customer) query.getSingleResult();
    }

    public Customer create(Customer customer) {
        em.persist(customer);
        return customer;
    }

    public Customer update(String id, Customer customer) {
        Query query = em.createNativeQuery(
                "UPDATE Customers SET CompanyName = :companyName, ContactName = :contactName, ContactTitle = :contactTitle, " +
                        "Address = :address, City = :city, Region = :region, PostalCode = :postalCode, Country = :country, " +
                        "Phone = :phone, Fax = :fax WHERE CustomerID = :id");
        query.setParameter("companyName", customer.getCompanyName());
        query.setParameter("contactName", customer.getContactName());
        query.setParameter("contactTitle", customer.getContactTitle());
        query.setParameter("address", customer.getAddress());
        query.setParameter("city", customer.getCity());
        query.setParameter("region", customer.getRegion());
        query.setParameter("postalCode", customer.getPostalCode());
        query.setParameter("country", customer.getCountry());
        query.setParameter("phone", customer.getPhone());
        query.setParameter("fax", customer.getFax());
        query.setParameter("id", id);
        query.executeUpdate();
        return getById(id);
    }

    public boolean delete(String id) {
        Query query = em.createNativeQuery("DELETE FROM Customers WHERE CustomerID = :id");
        query.setParameter("id", id);
        return query.executeUpdate() > 0;
    }

    public Long count() {
        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Customers");
        return ((Number) countQuery.getSingleResult()).longValue();
    }
}
