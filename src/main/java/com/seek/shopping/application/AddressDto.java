package com.seek.shopping.application;

import com.seek.shopping.domain.model.Address;
import java.time.LocalDateTime;

public record AddressDto(
    Long id,
    String city,
    String street,
    String zipcode,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static AddressDto from(Address address) {
        return new AddressDto(
            address.getId(),
            address.getCity(),
            address.getStreet(),
            address.getZipcode(),
            address.getCreatedAt(),
            address.getUpdatedAt()
        );
    }
}
