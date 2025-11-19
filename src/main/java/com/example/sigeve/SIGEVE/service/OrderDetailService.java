package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.OrderDetail;
import com.example.sigeve.SIGEVE.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Page<OrderDetail> list(Pageable pageable) {
        return orderDetailRepository.list(pageable);
    }

    public OrderDetail getById(String id) {
        return orderDetailRepository.getById(id);
    }

    public OrderDetail create(OrderDetail orderDetail) {
        // Validar que orderId y productId estén presentes
        if (orderDetail.getOrderId() == 0 || orderDetail.getProductId() == 0) {
            throw new RuntimeException("OrderID y ProductID son requeridos");
        }
        return orderDetailRepository.create(orderDetail);
    }

    public OrderDetail update(String id, OrderDetail orderDetail) {
        return orderDetailRepository.update(id, orderDetail);
    }

    public boolean delete(String id) {
        return orderDetailRepository.delete(id);
    }
}