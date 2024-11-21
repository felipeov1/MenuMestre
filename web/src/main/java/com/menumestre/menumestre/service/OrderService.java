package com.menumestre.menumestre.service;

import com.menumestre.menumestre.domain.order.Order;
import com.menumestre.menumestre.domain.order.OrderRequestDTO;
import com.menumestre.menumestre.domain.order.OrderResponseDTO;
import com.menumestre.menumestre.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderRequestDTO data){

        Order newOrder = new Order();

        newOrder.setName(data.name());
        newOrder.setTableCode(data.tableCode());

        return orderRepository.save(newOrder);
    }

    public List<OrderResponseDTO> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orderPages = this.orderRepository.findAllEvents(pageable);
        return orderPages
                .map(order -> new OrderResponseDTO(
                        order.getId(),
                        order.getName(),
                        order.getTableCode(),
                        order.getOrderHour()
                )).stream().toList();
    }
}
