package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.Order;
import com.example.sigeve.SIGEVE.model.OrderDetail;
import com.example.sigeve.SIGEVE.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    public Page<Order> list(Pageable pageable) {
        return orderRepository.list(pageable);
    }

    public Order getById(String id) {
        return orderRepository.getById(id);
    }

    public Order create(Order order) {
        return orderRepository.create(order);
    }

    public Order update(String id, Order orderDetails) {
        return orderRepository.update(id, orderDetails);
    }

    @Transactional
    public boolean delete(String id) {
        try {
            List<OrderDetail> orderDetails = orderDetailService.getByOrderId(Integer.parseInt(id));

            for (OrderDetail detail : orderDetails) {
                String detailId = detail.getOrderId() + "-" + detail.getProductId();
                orderDetailService.delete(detailId);
            }

            return orderRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la orden con ID: " + id + ". " + e.getMessage());
        }
    }
}
