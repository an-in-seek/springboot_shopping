package com.seek.shopping.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseDomainModel {

    private Long id;

    private Member member;

    @Default
    private List<OrderItem> orderItems = new ArrayList<>();

    private Delivery delivery;

    @Setter
    private OrderStatus orderStatus;

    @Setter
    private Money totalAmounts;

    private LocalDateTime orderDate;

    public static Order create(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = Order.builder()
            .member(member)
            .delivery(delivery)
            .orderStatus(OrderStatus.PAYMENT_WAITING)
            .totalAmounts(getTotalAmounts(List.of(orderItems)))
            .orderDate(LocalDateTime.now())
            .build();
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        verifyAtLeastOneOrMoreOrderItems(orderItems);
        this.orderItems = orderItems;
        this.setTotalAmounts(getTotalAmounts(orderItems));
    }

    public void addOrderItem(OrderItem orderItem) {
        verifyAtLeastOneOrMoreOrderItems(List.of(orderItem));
        this.orderItems.add(orderItem);
        this.setTotalAmounts(getTotalAmounts(orderItems));
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        if (!isShippingChangeable()) {
            throw new IllegalStateException(this.orderStatus + " 상태는 배송 정보를 변경할 수 없습니다.");
        }
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    private boolean isShippingChangeable() {
        return OrderStatus.PAYMENT_WAITING.equals(this.orderStatus) || OrderStatus.PREPARING.equals(this.orderStatus);
    }

    public void cancel() {
        if (this.delivery.getStatus() == DeliveryStatus.DELIVERED) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setOrderStatus(OrderStatus.CANCELLED);
        for (OrderItem orderItem : this.orderItems) {
            orderItem.cancel();
        }
    }

    private static void verifyAtLeastOneOrMoreOrderItems(List<OrderItem> orderItems) {
        if (CollectionUtils.isEmpty(orderItems)) {
            throw new IllegalStateException("주문 아이템 정보가 없습니다.");
        }
    }

    public static Money getTotalAmounts(List<OrderItem> orderItems) {
        return orderItems.stream()
            .map(orderItem -> OrderItem.calculateAmount(orderItem.getOrderPrice(), orderItem.getCount()))
            .reduce(Money.from(0), Money::add);
    }
}
