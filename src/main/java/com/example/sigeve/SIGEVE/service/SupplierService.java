package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.Supplier;
import com.example.sigeve.SIGEVE.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Page<Supplier> list(Pageable pageable) {
        return supplierRepository.list(pageable);
    }

    public Supplier getById(String id) {
        return supplierRepository.getById(id);
    }

    public Supplier create(Supplier supplier) {
        return supplierRepository.create(supplier);
    }

    public Supplier update(String id, Supplier supplierDetails) {
        return supplierRepository.update(id, supplierDetails);
    }

    public boolean delete(String id) {
        return supplierRepository.delete(id);
    }
}
