package com.terpel.test.entity;

import com.terpel.test.entity.enums.OrderStatus;
import com.terpel.test.entity.enums.OrderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "tpl", indexes = {
        @Index(name = "idx_station_id", columnList = "stationId"),
        @Index(name = "idx_status",     columnList = "status")
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @UuidGenerator
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String stationId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType type;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    public void transitionTo(OrderStatus newStatus) {
        this.status.validateTransitionTo(newStatus);
        this.status    = newStatus;
        this.updatedAt = LocalDate.now();
    }

    @PrePersist
    void onCreate() {
        LocalDate now  = LocalDate.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
