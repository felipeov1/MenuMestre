package com.menumestre.menumestre.domain.order;

import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        String name,
        int tableCode,
        String observation,
        java.time.LocalDateTime orderHour,
        java.util.List<OrderItemResponseDTO> list) {
}
