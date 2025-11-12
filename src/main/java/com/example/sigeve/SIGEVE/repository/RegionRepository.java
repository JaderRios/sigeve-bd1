package com.example.sigeve.SIGEVE.repository;

import com.example.sigeve.SIGEVE.model.Region;
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
public class RegionRepository {
    @PersistenceContext
    private EntityManager em;

    public Page<Region> list(Pageable pageable) {
        Query query = em.createNativeQuery(
                "SELECT * FROM Region ORDER BY RegionID " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY",
                Region.class);
        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());
        List<Region> regions = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Region");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(regions, pageable, total);
    }

    public Region getById(String id) {
        Query query = em.createNativeQuery("SELECT * FROM Region WHERE RegionID = :id", Region.class);
        query.setParameter("id", id);
        List<Region> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public Region create(Region region) {
        em.persist(region);
        return region;
    }

    public Region update(String id, Region region) {
        Query query = em.createNativeQuery(
                "UPDATE Region SET RegionDescription = :regionDescription WHERE RegionID = :id");
        query.setParameter("regionDescription", region.getRegionDescription());
        query.setParameter("id", id);
        query.executeUpdate();
        return getById(id);
    }

    public boolean delete(String id) {
        Query query = em.createNativeQuery("DELETE FROM Region WHERE RegionID = :id");
        query.setParameter("id", id);
        return query.executeUpdate() > 0;
    }

    public Long count() {
        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Region");
        return ((Number) countQuery.getSingleResult()).longValue();
    }
}
