package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.Address;
import com.seek.shopping.infrastructure.persistence.entity.AddressEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMapper {

    public static Address toDomainModel(AddressEntity entity) {
        if (entity == null) {
            return null;
        }
        return Address.builder()
            .id(entity.getId())
            .city(entity.getCity())
            .street(entity.getStreet())
            .zipcode(entity.getZipcode())
            .build();
    }

    public static AddressEntity toEntity(Address address) {
        if (address == null) {
            return null;
        }
        return AddressEntity.builder()
            .id(address.getId())
            .city(address.getCity())
            .street(address.getStreet())
            .zipcode(address.getZipcode())
            .build();
    }
}
