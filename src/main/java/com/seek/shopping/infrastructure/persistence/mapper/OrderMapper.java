package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.Money;
import com.seek.shopping.domain.Order;
import com.seek.shopping.infrastructure.persistence.entity.OrderEntity;
import com.seek.shopping.infrastructure.persistence.entity.OrderItemEntity;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper {

    public static Order toDomainModel(OrderEntity entity) {
        if (entity == null) {
            return null;
        }
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

    public static OrderEntity toEntity(Order order) {
        if (order == null) {
            return null;
        }
        OrderEntity orderEntity = OrderEntity.builder()
            .id(order.getId())
            .member(MemberMapper.toEntity(order.getMember()))
            .delivery(DeliveryMapper.toEntity(order.getDelivery()))
            .orderStatus(order.getOrderStatus())
            .totalAmounts(order.getTotalAmounts().value())
            .orderDate(order.getOrderDate())
            .build();
        List<OrderItemEntity> orderItemEntities = OrderItemMapper.toEntity(order.getOrderItems());
        orderItemEntities.forEach(orderItem -> orderItem.setOrder(orderEntity));
        return orderEntity;
    }
}
