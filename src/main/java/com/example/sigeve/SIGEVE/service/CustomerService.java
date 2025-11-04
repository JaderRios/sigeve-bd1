package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.Customer;
import com.example.sigeve.SIGEVE.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Page<Customer> list(Pageable pageable) {
        return customerRepository.list(pageable);
    }

    public Customer getById(String id) {
        return customerRepository.getById(id);
    }

    public Customer create(Customer customer) {
        customer.setId(generateCustomerId());
        return customerRepository.create(customer);
    }

    public Customer update(String id, Customer customerDetails) {
        return customerRepository.update(id, customerDetails);
    }

    public boolean delete(String id) {
        return customerRepository.delete(id);
    }

    private String generateCustomerId() {
        long count = customerRepository.count() + 1;
        return String.format("C%04d", count);
    }
}
