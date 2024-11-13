package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.model.Delivery;
import com.seek.shopping.infrastructure.persistence.entity.DeliveryEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryMapper {

    public static Delivery toDomainModel(DeliveryEntity entity) {
        if (entity == null) {
            return null;
        }
        return Delivery.builder()
            .id(entity.getId())
            .address(AddressMapper.toDomainModel(entity.getAddress()))
            .status(entity.getStatus())
            .build();
    }

    public static DeliveryEntity toEntity(Delivery delivery) {
        if (delivery == null) {
            return null;
        }
        return DeliveryEntity.builder()
            .id(delivery.getId())
            .address(AddressMapper.toEntity(delivery.getAddress()))
            .status(delivery.getStatus())
            .build();
    }
}
