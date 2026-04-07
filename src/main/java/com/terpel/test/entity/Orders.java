package com.terpel.test.entity;

import com.terpel.test.entity.enums.OrderStatus;
import com.terpel.test.entity.enums.OrderType;

import java.time.LocalDate;
import java.util.UUID;

public class Orders {
    private UUID id;
    private String stationId;
    private OrderType type;
    private String description;
    private OrderStatus status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
