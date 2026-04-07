package com.terpel.test.repository;

import com.terpel.test.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<UUID, Orders> {
}
