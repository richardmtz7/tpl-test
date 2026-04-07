package com.terpel.test.dto.request;

import com.terpel.test.entity.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequest(
        @NotNull
        OrderStatus status
) {
}
