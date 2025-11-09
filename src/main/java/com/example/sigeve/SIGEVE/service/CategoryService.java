package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.Category;
import com.example.sigeve.SIGEVE.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> list(Pageable pageable) {
        return categoryRepository.list(pageable);
    }

    public Category getById(String id) {
        return categoryRepository.getById(id);
    }

    public Category create(Category category) {
        category.setId(generateCategoryId());
        return categoryRepository.create(category);
    }

    public Category update(String id, Category categoryDetails) {
        return categoryRepository.update(id, categoryDetails);
    }

    public boolean delete(String id) {
        return categoryRepository.delete(id);
    }

    private String generateCategoryId() {
        long count = categoryRepository.count() + 1;
        return String.format("O%04d", count);
    }
}

