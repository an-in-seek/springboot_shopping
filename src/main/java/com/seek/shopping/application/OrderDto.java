package com.seek.shopping.application;

import com.seek.shopping.domain.model.Member;
import com.seek.shopping.domain.model.Money;
import com.seek.shopping.domain.model.Order;
import com.seek.shopping.domain.model.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record OrderDto(
    Long id,
    Member member,
    List<OrderItemDto> orderItems,
    DeliveryDto delivery,
    OrderStatus orderStatus,
    Money totalAmounts,
    LocalDateTime orderDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static OrderDto from(Order order) {
        return OrderDto.builder()
            .id(order.getId())
            .member(order.getMember())
            .orderItems(OrderItemDto.from(order.getOrderItems()))
            .delivery(DeliveryDto.from(order.getDelivery()))
            .orderStatus(order.getOrderStatus())
            .totalAmounts(order.getTotalAmounts())
            .orderDate(order.getOrderDate())
            .createdAt(order.getCreatedAt())
            .updatedAt(order.getUpdatedAt())
            .build();
    }
}
