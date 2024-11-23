package com.menumestre.menumestre.domain.order;

import java.util.List;

public record OrderRequestDTO(
        String name,
        int tableCode,
        String observation,
        List<OrderItemRequestDTO> items) {
}
