package com.terpel.test.services;

import com.terpel.test.dto.request.CreateOrderRequest;
import com.terpel.test.dto.response.CreateOrderResponse;
import com.terpel.test.dto.response.FilterResponse;
import com.terpel.test.entity.enums.OrderStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    @Override
    public CreateOrderResponse create(CreateOrderRequest request) {
        return null;
    }

    @Override
    public CreateOrderResponse findById(UUID id) {
        return null;
    }

    @Override
    public FilterResponse<CreateOrderResponse> findByFilters(String stationId, OrderStatus status, Pageable pageable) {
        return null;
    }

    @Override
    public CreateOrderResponse updateStatus(UUID id, OrderStatus newStatus) {
        return null;
    }
}
