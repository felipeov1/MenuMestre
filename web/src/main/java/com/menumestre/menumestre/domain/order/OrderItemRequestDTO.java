package com.menumestre.menumestre.domain.order;

import java.util.UUID;

public record OrderItemRequestDTO(
        UUID productId,
        int quantity,
        double price) {
}