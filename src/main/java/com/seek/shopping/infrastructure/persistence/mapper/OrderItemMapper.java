package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.model.Money;
import com.seek.shopping.domain.model.OrderItem;
import com.seek.shopping.infrastructure.persistence.entity.OrderItemEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemMapper {

    public static OrderItem toDomainModel(OrderItemEntity entity) {
        if (entity == null) {
            return null;
        }
        return OrderItem.builder()
            .id(entity.getId())
            .item(ItemMapper.toDomainModel(entity.getItem()))
            .orderPrice(Money.from(entity.getOrderPrice()))
            .count(entity.getCount())
            .amounts(Money.from(entity.getAmounts()))
            .build();
    }

    public static List<OrderItem> toDomainModel(List<OrderItemEntity> entities) {
        return entities.stream().map(OrderItemMapper::toDomainModel).toList();
    }

    public static OrderItemEntity toEntity(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        return OrderItemEntity.builder()
            .id(orderItem.getId())
            .item(ItemMapper.toEntity(orderItem.getItem()))
            .orderPrice(orderItem.getOrderPrice().value())
            .count(orderItem.getCount())
            .amounts(orderItem.getAmounts().value())
            .build();
    }

    public static List<OrderItemEntity> toEntity(List<OrderItem> orderItems) {
        return orderItems.stream()
            .map(OrderItemMapper::toEntity)
            .collect(Collectors.toList());
    }
}
