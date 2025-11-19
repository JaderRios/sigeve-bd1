package com.example.sigeve.SIGEVE.controller;

import com.example.sigeve.SIGEVE.model.OrderDetail;
import com.example.sigeve.SIGEVE.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public Page<OrderDetail> list(Pageable pageable) {
        return orderDetailService.list(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getById(@PathVariable String id) {
        OrderDetail orderDetail = orderDetailService.getById(id);
        return orderDetail != null ? ResponseEntity.ok(orderDetail) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OrderDetail> create(@RequestBody OrderDetail orderDetail) {
        try {
            OrderDetail created = orderDetailService.create(orderDetail);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> update(@PathVariable String id, @RequestBody OrderDetail orderDetail) {
        try {
            OrderDetail updated = orderDetailService.update(id, orderDetail);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = orderDetailService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetail>> getByOrderId(@PathVariable int orderId) {
        try {
            List<OrderDetail> orderDetails = orderDetailService.getByOrderId(orderId);
            return ResponseEntity.ok(orderDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/order/{orderId}/product/{productId}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable int orderId, @PathVariable int productId) {
        try {
            String id = orderId + "-" + productId;
            boolean deleted = orderDetailService.delete(id);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
