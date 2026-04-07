package com.terpel.test.dto.response;

import com.terpel.test.entity.enums.OrderStatus;
import com.terpel.test.entity.enums.OrderType;

import java.time.LocalDate;
import java.util.UUID;

public record CreateOrderResponse(
        UUID id,
        String stationId,
        OrderType type,
        String description,
        OrderStatus status,
        LocalDate createdAt,
        LocalDate updatedAt
) {
}
