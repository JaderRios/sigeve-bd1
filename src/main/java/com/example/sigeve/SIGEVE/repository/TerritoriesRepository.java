package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.Territories;
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
public class TerritoriesRepository {

    @PersistenceContext
    private EntityManager em;

    public Page<Territories> list(Pageable pageable) {
        Query query = em.createNativeQuery(
                "SELECT * FROM Territories ORDER BY TerritoryID " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", Territories.class);
        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());
        List<Territories> territories = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Territories");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(territories, pageable, total);
    }

    public Territories getById(String id) {
        Query query = em.createNativeQuery("SELECT * FROM Territories WHERE TerritoryID = :id", Territories.class);
        query.setParameter("id", id);
        return (Territories) query.getSingleResult();
    }

    public Territories create(Territories territory) {
        em.persist(territory);
        return territory;
    }

    public Territories update(String id, Territories territory) {
        Query query = em.createNativeQuery(
                "UPDATE Territories SET TerritoryDescription = :territoryDescription, RegionID = :regionId " +
                        "WHERE TerritoryID = :id");
        query.setParameter("territoryDescription", territory.getTerritoryDescription());
        query.setParameter("regionId", territory.getRegionId());
        query.setParameter("id", id);
        query.executeUpdate();
        return getById(id);
    }

    public boolean delete(String id) {
        Query query = em.createNativeQuery("DELETE FROM Territories WHERE TerritoryID = :id");
        query.setParameter("id", id);
        return query.executeUpdate() > 0;
    }

    public Long count() {
        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Territories");
        return ((Number) countQuery.getSingleResult()).longValue();
    }
}
