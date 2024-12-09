package com.seek.shopping.application;

import com.seek.shopping.domain.model.Money;
import com.seek.shopping.domain.model.OrderItem;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record OrderItemDto(
    Long id,
    ItemDto item,
    Money orderPrice,
    int count,
    Money amounts,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static OrderItemDto from(OrderItem orderItem) {
        return OrderItemDto.builder()
            .id(orderItem.getId())
            .item(ItemDto.from(orderItem.getItem()))
            .orderPrice(orderItem.getOrderPrice())
            .count(orderItem.getCount())
            .amounts(orderItem.getAmounts())
            .createdAt(orderItem.getCreatedAt())
            .updatedAt(orderItem.getUpdatedAt())
            .build();
    }

    public static List<OrderItemDto> from(List<OrderItem> orderItems) {
        return orderItems.stream().map(OrderItemDto::from).toList();
    }
}