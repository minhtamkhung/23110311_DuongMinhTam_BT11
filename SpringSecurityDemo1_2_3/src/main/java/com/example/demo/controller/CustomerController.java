package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Customer;

import java.util.List;

@RestController
public class CustomerController {

    // Danh sách khách hàng gán cứng trong code
    private final List<Customer> customers = List.of(
            Customer.builder().id("001").name("Nguyễn Hữu Trung")
                    .email("trungnhsptk@gmail.com").build(),
            Customer.builder().id("002").name("Hữu Trung")
                    .email("trunghuu@gmail.com").build()
    );

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is Guest");
    }

    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Customer>> getCustomerList() {
        List<Customer> list = this.customers;
        return ResponseEntity.ok(list);
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        List<Customer> customers = this.customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .toList();
        return ResponseEntity.ok(customers.get(0));
    }
}
