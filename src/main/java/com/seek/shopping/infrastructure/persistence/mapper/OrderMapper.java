package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.Money;
import com.seek.shopping.domain.Order;
import com.seek.shopping.infrastructure.persistence.entity.OrderEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper {

    public static Order toDomainModel(OrderEntity entity) {
        return Order.builder()
            .id(entity.getId())
            .member(MemberMapper.toDomainModel(entity.getMember()))
            .delivery(DeliveryMapper.toDomainModel(entity.getDelivery()))
            .orderItems(OrderItemMapper.toDomainModel(entity.getOrderItems()))
            .orderStatus(entity.getOrderStatus())
            .totalAmounts(Money.from(entity.getTotalAmounts()))
            .orderDate(entity.getOrderDate())
            .build();
    }

    public static OrderEntity toEntity(Order domainModel) {
        return OrderEntity.builder()
            .id(domainModel.getId())
            .member(MemberMapper.toEntity(domainModel.getMember()))
            .delivery(DeliveryMapper.toEntity(domainModel.getDelivery()))
            .orderItems(OrderItemMapper.toEntity(domainModel.getOrderItems()))
            .orderStatus(domainModel.getOrderStatus())
            .totalAmounts(domainModel.getTotalAmounts().value())
            .orderDate(domainModel.getOrderDate())
            .build();
    }
}
