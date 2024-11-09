package com.seek.shopping.infrastructure.persistence.repository;

import com.seek.shopping.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

}
