package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.Money;
import com.seek.shopping.domain.OrderItem;
import com.seek.shopping.infrastructure.persistence.entity.OrderItemEntity;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemMapper {

    public static OrderItem toDomainModel(OrderItemEntity entity) {
        return OrderItem.builder()
            .id(entity.getId())
            .item(ItemMapper.toDomainModel(entity.getItem()))
            .order(OrderMapper.toDomainModel(entity.getOrder()))
            .orderPrice(Money.from(entity.getOrderPrice()))
            .count(entity.getCount())
            .build();
    }

    public static List<OrderItem> toDomainModel(List<OrderItemEntity> entities) {
        return entities.stream().map(OrderItemMapper::toDomainModel).toList();
    }

    public static OrderItemEntity toEntity(OrderItem domainModel) {
        return OrderItemEntity.builder()
            .id(domainModel.getId())
            .item(ItemMapper.toEntity(domainModel.getItem()))
            .order(OrderMapper.toEntity(domainModel.getOrder()))
            .orderPrice(domainModel.getOrderPrice().value())
            .count(domainModel.getCount())
            .build();
    }

    public static List<OrderItemEntity> toEntity(List<OrderItem> entities) {
        return entities.stream().map(OrderItemMapper::toEntity).toList();
    }
}
