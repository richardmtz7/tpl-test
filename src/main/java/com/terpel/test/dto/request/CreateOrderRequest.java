package com.terpel.test.dto.request;

import com.terpel.test.entity.enums.OrderStatus;
import com.terpel.test.entity.enums.OrderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
        @NotBlank
        String stationId,

        @NotNull
        OrderType type,

        @NotNull
        OrderStatus status,

        String description
) {
}
