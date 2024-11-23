package com.menumestre.menumestre.domain.order;

import java.util.UUID;

public record OrderItemResponseDTO(UUID productId, String productName, int quantity, double price) {
}