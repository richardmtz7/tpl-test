package com.terpel.test.services;

import com.terpel.test.dto.request.CreateOrderRequest;
import com.terpel.test.dto.response.CreateOrderResponse;
import com.terpel.test.dto.response.FilterResponse;
import com.terpel.test.entity.enums.OrderStatus;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {
    CreateOrderResponse create(CreateOrderRequest request);

    CreateOrderResponse findById(UUID id);

    FilterResponse<CreateOrderResponse> findByFilters(String stationId, OrderStatus status, Pageable pageable);

    CreateOrderResponse updateStatus(UUID id, OrderStatus newStatus);
}
