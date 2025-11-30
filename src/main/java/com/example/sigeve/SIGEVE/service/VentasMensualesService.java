package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.VentasMensuales;
import com.example.sigeve.SIGEVE.repository.VentasMensualesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasMensualesService {

    @Autowired
    private VentasMensualesRepository repository;

    public List<VentasMensuales> obtenerReporteMensual() {
        return repository.obtenerVentasMensuales();
    }
}
