package com.terpel.test.repository;

import com.terpel.test.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID>, JpaSpecificationExecutor<Orders> {
}
