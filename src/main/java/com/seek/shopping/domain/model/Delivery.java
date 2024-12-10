package com.seek.shopping.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Delivery extends BaseDomainModel {

    private Long id;
    @Setter
    private Order order;
    private Address address;
    private DeliveryStatus status;

    public static Delivery of(Address address, DeliveryStatus deliveryStatus) {
        return Delivery.builder()
            .address(address)
            .status(deliveryStatus)
            .build();
    }

    public static Delivery create(Address address) {
        return Delivery.builder()
            .address(address)
            .status(DeliveryStatus.READY)
            .build();
    }
}
