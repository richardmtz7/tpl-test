package com.terpel.test.controller;

import com.terpel.test.dto.request.CreateOrderRequest;
import com.terpel.test.dto.request.UpdateStatusRequest;
import com.terpel.test.dto.response.CreateOrderResponse;
import com.terpel.test.dto.response.FilterResponse;
import com.terpel.test.entity.enums.OrderStatus;
import com.terpel.test.interfaces.facade.OrderFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/service-orders")
@RequiredArgsConstructor
@Tag(name = "Service Orders", description = "Station services management")
public class OrderController {
    private final OrderFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new order")
    public CreateOrderResponse create(
            @Valid @RequestBody CreateOrderRequest request) {
        return facade.create(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    public CreateOrderResponse findById(
            @Parameter(description = "Order id") @PathVariable UUID id) {
        return facade.findById(id);
    }

    @GetMapping
    @Operation(summary = "Get orders by filters")
    public FilterResponse<CreateOrderResponse> findByFilters(
            @Parameter(description = "Station ID")
            @RequestParam(required = false) String stationId,

            @Parameter(description = "Order status")
            @RequestParam(required = false) OrderStatus status,

            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return facade.findByFilter(stationId, status, pageable);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update order status")
    public ResponseEntity<CreateOrderResponse> updateStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateStatusRequest request) {
        return ResponseEntity.ok(facade.updateStatus(id, request.status()));
    }
}
