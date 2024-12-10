package com.seek.shopping.application;

import com.seek.shopping.domain.model.Delivery;
import com.seek.shopping.domain.model.DeliveryStatus;
import java.time.LocalDateTime;

public record DeliveryDto(
    Long id,
    AddressDto address,
    DeliveryStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static DeliveryDto from(Delivery delivery) {
        return new DeliveryDto(
            delivery.getId(),
            AddressDto.from(delivery.getAddress()),
            delivery.getStatus(),
            delivery.getCreatedAt(),
            delivery.getUpdatedAt()
        );
    }
}
