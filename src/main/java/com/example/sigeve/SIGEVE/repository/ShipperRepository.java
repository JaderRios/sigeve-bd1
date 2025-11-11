package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.Shipper;
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
public class ShipperRepository {
    @PersistenceContext
    private EntityManager em;
    public Page<Shipper> list(Pageable pageable) {
        Query query = em.createNativeQuery(
                "SELECT * FROM Shippers ORDER BY ShipperID " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", Shipper.class);
        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());
        List<Shipper> shipper = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Shippers");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(shipper, pageable, total);
    }

    public Shipper getById(String id) {
        Query query = em.createNativeQuery("SELECT * FROM Shippers WHERE ShipperID = :id", Shipper.class);
        query.setParameter("id", id);
        return (Shipper) query.getSingleResult();
    }

    @Transactional
    public Shipper create(Shipper shipper) {
        String sql = "INSERT INTO Shippers (CompanyName, Phone) VALUES (?, ?)";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, shipper.getCompanyName());
        query.setParameter(2, shipper.getPhone());
        query.executeUpdate();

        return shipper;
    }

    public Shipper update(String id, Shipper shipper) {
        Query query = em.createNativeQuery(
                "UPDATE Shippers SET CompanyName = :companyName, Phone = :phone WHERE ShipperID = :id");
        query.setParameter("companyName", shipper.getCompanyName());
        query.setParameter("phone", shipper.getPhone());
        query.setParameter("id", id);
        query.executeUpdate();
        return getById(id);
    }

    public boolean delete(String id) {
        Query query = em.createNativeQuery("DELETE FROM Shippers WHERE ShipperID = :id");
        query.setParameter("id", id);
        return query.executeUpdate() > 0;
    }
}
