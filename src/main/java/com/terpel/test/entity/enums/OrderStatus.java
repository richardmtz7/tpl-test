package com.terpel.test.entity.enums;

import com.terpel.test.exceptions.InvalidStatusException;

import java.util.EnumSet;
import java.util.Set;

public enum OrderStatus {
    CREATED {
        @Override
        public Set<OrderStatus> allowedTransitions() {
            return EnumSet.of(IN_PROGRESS, CANCELLED);
        }
    },
    IN_PROGRESS {
        @Override
        public Set<OrderStatus> allowedTransitions() {
            return EnumSet.of(DONE, CANCELLED);
        }
    },
    DONE {
        @Override
        public Set<OrderStatus> allowedTransitions() {
            return EnumSet.noneOf(OrderStatus.class);
        }
    },
    CANCELLED {
        @Override
        public Set<OrderStatus> allowedTransitions() {
            return EnumSet.noneOf(OrderStatus.class);
        }
    };

    public abstract Set<OrderStatus> allowedTransitions();

    public void validateTransitionTo(OrderStatus next) {
        if (!allowedTransitions().contains(next)) {
            throw new InvalidStatusException(
                    String.format("Trx not allowed: %s → %s", this.name(), next.name())
            );
        }
    }
}
