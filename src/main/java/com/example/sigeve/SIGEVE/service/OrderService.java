package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.Order;
import com.example.sigeve.SIGEVE.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Page<Order> list(Pageable pageable) {
        return orderRepository.list(pageable);
    }

    public Order getById(String id) {
        return orderRepository.getById(id);
    }

    public Order create(Order order) {
        order.setId(generateOrderId());
        return orderRepository.create(order);
    }

    public Order update(String id, Order orderDetails) {
        return orderRepository.update(id, orderDetails);
    }

    public boolean delete(String id) {
        return orderRepository.delete(id);
    }

    private String generateOrderId() {
        long count = orderRepository.count() + 1;
        return String.format("O%04d", count);
    }
}
