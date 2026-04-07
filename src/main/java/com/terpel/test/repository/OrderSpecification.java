package com.terpel.test.repository;

import com.terpel.test.entity.Orders;
import com.terpel.test.entity.enums.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {
    private OrderSpecification() {}

    public static Specification<Orders> hasStationId(String stationId) {
        return (root, query, cb) ->
                stationId == null ? cb.conjunction()
                        : cb.equal(root.get("stationId"), stationId);
    }

    public static Specification<Orders> hasStatus(OrderStatus status) {
        return (root, query, cb) ->
                status == null ? cb.conjunction()
                        : cb.equal(root.get("status"), status);
    }

    public static Specification<Orders> withFilters(String stationId, OrderStatus status) {
        return Specification
                .allOf(hasStationId(stationId))
                .and(hasStatus(status));
    }
}
