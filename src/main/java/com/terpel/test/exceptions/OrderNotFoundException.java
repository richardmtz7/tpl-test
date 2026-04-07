package com.terpel.test.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(UUID id) {
        super("Order not found with id " + id);
    }

    public OrderNotFoundException(String stationId, String status) {
        super(String.format(
                "Orders not found with stationId='%s' y status='%s'",
                stationId, status
        ));
    }
}
