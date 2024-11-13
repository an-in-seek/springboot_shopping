package com.seek.shopping.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseDomainModel {

    private Long id;
    private String city;
    private String street;
    private String zipcode;
}
