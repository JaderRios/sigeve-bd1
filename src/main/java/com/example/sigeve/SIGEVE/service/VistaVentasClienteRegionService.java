package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.VistaVentasClienteRegion;
import com.example.sigeve.SIGEVE.repository.VistaVentasClienteRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VistaVentasClienteRegionService {

    @Autowired
    private VistaVentasClienteRegionRepository repository;

    public List<VistaVentasClienteRegion> getAll() {
        return repository.listAll();
    }
}
