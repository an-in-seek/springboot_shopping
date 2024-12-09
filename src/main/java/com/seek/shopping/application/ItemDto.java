package com.seek.shopping.application;

import com.seek.shopping.domain.model.Item;
import com.seek.shopping.domain.model.Money;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ItemDto(
    Long id,
    String name,
    Money price,
    int stockQuantity,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static ItemDto from(Item item) {
        return ItemDto.builder()
            .id(item.getId())
            .name(item.getName())
            .price(item.getPrice())
            .stockQuantity(item.getStockQuantity())
            .createdAt(item.getCreatedAt())
            .updatedAt(item.getUpdatedAt())
            .build();
    }
}
