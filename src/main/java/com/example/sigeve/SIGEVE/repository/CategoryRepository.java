package com.example.sigeve.SIGEVE.repository;


import com.example.sigeve.SIGEVE.model.Category;
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
public class CategoryRepository {
    @PersistenceContext
    private EntityManager em;
    public Page<Category> list(Pageable pageable) {
        Query query = em.createNativeQuery(
                "SELECT * FROM Categories ORDER BY CategoryID " +
                        "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", Category.class);
        query.setParameter("offset", pageable.getOffset());
        query.setParameter("limit", pageable.getPageSize());
        List<Category> category = query.getResultList();

        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Categories");
        Long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(category, pageable, total);
    }

    public Category getById(String id) {
        Query query = em.createNativeQuery("SELECT * FROM Categories WHERE CategoryID = :id", Category.class);
        query.setParameter("id", id);
        return (Category) query.getSingleResult();
    }

    @Transactional
    public Category create(Category category) {

            String sql = "INSERT INTO Categories (CategoryName, Description, Picture) VALUES (?, ?, ?)";
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, category.getCategoryName());
            query.setParameter(2, category.getDescription());
            query.setParameter(3, category.getPicture());
            query.executeUpdate();
            return category;
    }


    public Category update(String id, Category category) {
        Query query = em.createNativeQuery(
                "UPDATE Categories SET CategoryName = :categoryName, Description = :description, Picture = :picture WHERE CategoryID = :id");
        query.setParameter("categoryName", category.getCategoryName());
        query.setParameter("description", category.getDescription());
        query.setParameter("picture", category.getPicture());
        query.setParameter("id", id);
        query.executeUpdate();
        return getById(id);
    }

    public boolean delete(String id) {
        Query query = em.createNativeQuery("DELETE FROM Categories WHERE CategoryID = :id");
        query.setParameter("id", id);
        return query.executeUpdate() > 0;
    }

    public Long count() {
        Query countQuery = em.createNativeQuery("SELECT COUNT(*) FROM Categories");
        return ((Number) countQuery.getSingleResult()).longValue();
    }
}