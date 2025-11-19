package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.PAHistorialPedidos;
import com.example.sigeve.SIGEVE.repository.PARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PAService {

    @Autowired
    private PARepository paRepository;

    public List<PAHistorialPedidos> getHistorial(String customerId) {
        return paRepository.historialPedidosPorCliente(customerId);
    }
}
