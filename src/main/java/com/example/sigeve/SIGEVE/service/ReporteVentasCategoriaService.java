package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.ReporteVentasCategoria;
import com.example.sigeve.SIGEVE.repository.ReporteVentasCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReporteVentasCategoriaService {

    @Autowired
    private ReporteVentasCategoriaRepository repository;

    public List<ReporteVentasCategoria> obtenerReporte() {
        return repository.generarReporte();
    }
}