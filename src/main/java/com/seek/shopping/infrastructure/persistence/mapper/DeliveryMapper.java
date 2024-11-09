package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.Delivery;
import com.seek.shopping.infrastructure.persistence.entity.DeliveryEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryMapper {

    public static Delivery toDomainModel(DeliveryEntity entity) {
        return Delivery.builder()
            .id(entity.getId())
            .address(AddressMapper.toDomainModel(entity.getAddress()))
            .status(entity.getStatus())
            .order(OrderMapper.toDomainModel(entity.getOrder()))
            .build();
    }

    public static DeliveryEntity toEntity(Delivery domainModel) {
        return DeliveryEntity.builder()
            .id(domainModel.getId())
            .address(AddressMapper.toEntity(domainModel.getAddress()))
            .status(domainModel.getStatus())
            .order(OrderMapper.toEntity(domainModel.getOrder()))
            .build();
    }
}
