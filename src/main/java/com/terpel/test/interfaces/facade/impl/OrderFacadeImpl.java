package com.terpel.test.interfaces.facade.impl;

import com.terpel.test.dto.request.CreateOrderRequest;
import com.terpel.test.dto.response.CreateOrderResponse;
import com.terpel.test.dto.response.FilterResponse;
import com.terpel.test.entity.enums.OrderStatus;
import com.terpel.test.interfaces.facade.OrderFacade;
import com.terpel.test.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {
    private final OrderService service;

    @Override
    public CreateOrderResponse create(CreateOrderRequest request) {
        log.info("FACADE create | correlationId={}", MDC.get("correlationId"));
        CreateOrderResponse response = service.create(request);
        log.info("FACADE create OK | id={}", response.id());
        return response;
    }

    @Override
    public CreateOrderResponse findById(UUID id) {
        log.info("FACADE findById | id={} correlationId={}", id, MDC.get("correlationId"));
        return service.findById(id);
    }

    @Override
    public FilterResponse<CreateOrderResponse> findByFilter(String stationId, OrderStatus status, Pageable pageable) {
        log.info("FACADE findByFilters | stationId={} status={} correlationId={}",
                stationId, status, MDC.get("correlationId"));
        return service.findByFilters(stationId, status, pageable);
    }

    @Override
    public CreateOrderResponse updateStatus(UUID id, OrderStatus status) {
        log.info("FACADE updateStatus | id={} newStatus={} correlationId={}",
                id, status, MDC.get("correlationId"));
        CreateOrderResponse response = service.updateStatus(id, status);
        log.info("FACADE updateStatus OK | id={} status={}", id, response.status());
        return response;
    }
}
