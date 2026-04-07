package com.terpel.test.services.impl;

import com.terpel.test.dto.request.CreateOrderRequest;
import com.terpel.test.dto.response.CreateOrderResponse;
import com.terpel.test.dto.response.FilterResponse;
import com.terpel.test.entity.Orders;
import com.terpel.test.entity.enums.OrderStatus;
import com.terpel.test.exceptions.OrderNotFoundException;
import com.terpel.test.mapper.OrderMapper;
import com.terpel.test.repository.OrderRepository;
import com.terpel.test.repository.OrderSpecification;
import com.terpel.test.services.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderServiceImpl(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CreateOrderResponse create(CreateOrderRequest request) {
        log.info("Creating order para stationId={} type={}", request.stationId(), request.type());

        Orders entity = mapper.toEntity(request);
        Orders saved  = repository.save(entity);

        log.info("Order created with id={}", saved.getId());
        return mapper.toResponse(saved);
    }

    @Override
    public CreateOrderResponse findById(UUID id) {
        log.info("Searching order with id={}", id);
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public FilterResponse<CreateOrderResponse> findByFilters(String stationId, OrderStatus status, Pageable pageable) {
        log.info("Searching orders with filters stationId={} status={} page={}",
                stationId, status, pageable.getPageNumber());

        Specification<Orders> spec = OrderSpecification.withFilters(stationId, status);
        Page<CreateOrderResponse> page = repository.findAll(spec, pageable)
                .map(mapper::toResponse);

        if (page.isEmpty()) {
            throw new OrderNotFoundException(stationId, status != null ? status.name() : "N/A");
        }

        return FilterResponse.from(page);
    }

    @Override
    @Transactional
    public CreateOrderResponse updateStatus(UUID id, OrderStatus newStatus) {
        log.info("Updating order status id={} a {}", id, newStatus);

        Orders entity = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        entity.transitionTo(newStatus);

        Orders saved = repository.save(entity);
        log.info("Order with id id={} was update with status status={}", id, saved.getStatus());
        return mapper.toResponse(saved);
    }
}
