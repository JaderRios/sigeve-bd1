package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.Product;
import com.example.sigeve.SIGEVE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> list(Pageable pageable) {
        return productRepository.list(pageable);
    }

    public Product getById(String id) {
        return productRepository.getById(id);
    }

    public Product create(Product product) {
        return productRepository.create(product);
    }

    public Product update(String id, Product productDetails) {
        return productRepository.update(id, productDetails);
    }

    public boolean delete(String id) {
        return productRepository.delete(id);
    }

}
