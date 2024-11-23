package com.menumestre.menumestre.service;

import com.menumestre.menumestre.domain.order.*;
import com.menumestre.menumestre.domain.product.Product;
import com.menumestre.menumestre.repositories.OrderRepository;
import com.menumestre.menumestre.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public Order createOrder(OrderRequestDTO data) {
        Order newOrder = new Order();

        newOrder.setName(data.name());
        newOrder.setTableCode(data.tableCode());

        if (data.observation() != null && !data.observation().isEmpty()) {
            newOrder.setObservation(data.observation());
        }

        List<OrderItem> orderItems = new ArrayList<>();
        if (data.items() != null && !data.items().isEmpty()) {
            for (OrderItemRequestDTO itemData : data.items()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(newOrder);

                Product product = productService.getProductById(itemData.productId());

                orderItem.setProduct(product);
                orderItem.setQuantity(itemData.quantity());
                orderItem.setPrice(itemData.price());

                orderItems.add(orderItem);
            }
        }

        newOrder.setItems(orderItems);

        return orderRepository.save(newOrder);
    }

    public List<OrderResponseDTO> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Order> orderPages = this.orderRepository.findAllOrdersWithItems(pageable);

        return orderPages
                .map(order -> new OrderResponseDTO(
                        order.getId(),
                        order.getName(),
                        order.getTableCode(),
                        order.getObservation(),
                        order.getOrderHour(),
                        order.getItems().stream()
                                .map(orderItem -> new OrderItemResponseDTO(
                                        orderItem.getProduct().getId(),
                                        orderItem.getProduct().getName(),
                                        orderItem.getQuantity(),
                                        orderItem.getPrice()
                                )).toList()
                )).stream().toList();
    }
}


