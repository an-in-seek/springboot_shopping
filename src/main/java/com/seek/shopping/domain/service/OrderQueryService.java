package com.seek.shopping.domain.service;

import com.seek.shopping.domain.model.Order;
import com.seek.shopping.domain.model.OrderStatus;
import com.seek.shopping.infrastructure.persistence.mapper.OrderMapper;
import com.seek.shopping.infrastructure.persistence.repository.OrderJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQueryService {

    private final OrderJpaRepository orderJpaRepository;

    public Optional<Order> getOrderById(Long orderId) {
        return orderJpaRepository.findById(orderId).map(OrderMapper::toDomainModel);
    }

    public List<Order> getOrdersByMemberId(Long memberId) {
        return orderJpaRepository.findOrdersByMemberId(memberId)
            .stream()
            .map(OrderMapper::toDomainModel)
            .toList();
    }

    public List<Order> getOrdersByOrderStatus(OrderStatus orderStatus) {
        return orderJpaRepository.findOrdersByOrderStatus(orderStatus)
            .stream()
            .map(OrderMapper::toDomainModel)
            .toList();
    }
}
