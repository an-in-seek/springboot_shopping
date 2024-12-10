package com.seek.shopping.domain.model;

import lombok.Getter;

@Getter
public enum DeliveryStatus {
    READY("배송 준비"),
    DISPATCHED("배송 출발"),
    IN_TRANSIT("배송 중"),
    DELIVERED("배송 완료"),
    CANCELLED("배송 취소");

    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }

}