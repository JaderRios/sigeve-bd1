package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.ProductosMasVendidos;
import com.example.sigeve.SIGEVE.repository.ProductosMasVendidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosMasVendidosService {

    @Autowired
    private ProductosMasVendidosRepository repository;

    public List<ProductosMasVendidos> obtenerProductosMasVendidos() {
        return repository.obtenerProductosMasVendidos();
    }
}
