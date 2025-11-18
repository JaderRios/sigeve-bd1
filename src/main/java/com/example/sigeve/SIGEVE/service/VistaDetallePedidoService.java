package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.VistaDetallePedidos;
import com.example.sigeve.SIGEVE.repository.VistaDetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VistaDetallePedidoService {

    @Autowired
    private VistaDetallePedidoRepository repository;

    public List<VistaDetallePedidos> getAll() {
        return repository.listAll();
    }
}
