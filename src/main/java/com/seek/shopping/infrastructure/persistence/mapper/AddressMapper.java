package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.Address;
import com.seek.shopping.infrastructure.persistence.entity.AddressEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMapper {

    public static Address toDomainModel(AddressEntity entity) {
        return Address.builder()
            .id(entity.getId())
            .city(entity.getCity())
            .street(entity.getStreet())
            .zipcode(entity.getZipcode())
            .build();
    }

    public static AddressEntity toEntity(Address domainModel) {
        return AddressEntity.builder()
            .id(domainModel.getId())
            .city(domainModel.getCity())
            .street(domainModel.getStreet())
            .zipcode(domainModel.getZipcode())
            .build();
    }
}
