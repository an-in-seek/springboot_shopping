package com.seek.shopping.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseDomainModel {

    private Long id;

    private Item item;

    @Setter
    private Order order;

    private Money orderPrice;

    private int count;

    private Money amounts;

    public static OrderItem createOrderItem(Item item, Money orderPrice, int count) {
        OrderItem orderItem = OrderItem.builder()
            .item(item)
            .orderPrice(orderPrice)
            .count(count)
            .amounts(calculateAmount(orderPrice, count))
            .build();
        item.removeStock(count);
        return orderItem;
    }

    public void cancel() {
        getItem().addStock(count);
    }

    public static Money calculateAmount(Money orderPrice, int count) {
        return orderPrice.multiply(count);
    }
}
