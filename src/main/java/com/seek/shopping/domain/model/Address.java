package com.seek.shopping.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Address extends BaseDomainModel {

    private Long id;
    private String city;
    private String street;
    private String zipcode;
}
