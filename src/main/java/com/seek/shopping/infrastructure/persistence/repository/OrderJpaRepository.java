package com.seek.shopping.infrastructure.persistence.repository;

import com.seek.shopping.domain.model.OrderStatus;
import com.seek.shopping.infrastructure.persistence.entity.OrderEntity;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    Collection<OrderEntity> findOrdersByMemberId(Long memberId);

    Collection<OrderEntity> findOrdersByOrderStatus(OrderStatus orderStatus);

}
