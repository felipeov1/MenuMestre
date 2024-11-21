package com.menumestre.menumestre.domain.order;

import org.joda.time.DateTime;

import java.util.UUID;

public record OrderResponseDTO(UUID id, String name, int tableCode, DateTime orderHour) {
}
