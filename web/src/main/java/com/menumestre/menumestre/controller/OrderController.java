package com.menumestre.menumestre.controller;

import com.menumestre.menumestre.domain.order.Order;
import com.menumestre.menumestre.domain.order.OrderRequestDTO;
import com.menumestre.menumestre.domain.order.OrderResponseDTO;
import com.menumestre.menumestre.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/menumestre")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/create")
    public ResponseEntity<Order> create(@RequestBody OrderRequestDTO orderRequestDTO) {

        if (orderRequestDTO.tableCode() == 0) {
            return ResponseEntity.badRequest().body(null);
        }

        Order newOrder = this.orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<OrderResponseDTO> orders = this.orderService.getAllOrders(page, size);
        return ResponseEntity.ok(orders);
    }
}