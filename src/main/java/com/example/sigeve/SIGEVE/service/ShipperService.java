package com.example.sigeve.SIGEVE.service;


import com.example.sigeve.SIGEVE.model.Shipper;
import com.example.sigeve.SIGEVE.model.Shipper;
import com.example.sigeve.SIGEVE.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class ShipperService {

    @Autowired
    private ShipperRepository shipperRepository;

    public Page<Shipper> list(Pageable pageable) {
        return shipperRepository.list(pageable);
    }

    public Shipper getById(String id) {
        return shipperRepository.getById(id);
    }

    public Shipper create(Shipper shipper) {
        shipper.setId(generateShipperId());
        return shipperRepository.create(shipper);
    }

    public Shipper update(String id, Shipper shipperDetails) {
        return shipperRepository.update(id, shipperDetails);
    }

    public boolean delete(String id) {
        return shipperRepository.delete(id);
    }

    private String generateShipperId() {
        long count = shipperRepository.count() + 1;
        return String.format("O%04d", count);
    }
}
