package com.seek.shopping.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
