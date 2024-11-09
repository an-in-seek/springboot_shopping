package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.Item;
import com.seek.shopping.domain.Money;
import com.seek.shopping.infrastructure.persistence.entity.ItemEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemMapper {

    public static Item toDomainModel(ItemEntity entity) {
        return Item.builder()
            .id(entity.getId())
            .name(entity.getName())
            .price(Money.from(entity.getPrice()))
            .stockQuantity(entity.getStockQuantity())
            .build();
    }

    public static ItemEntity toEntity(Item domainModel) {
        return ItemEntity.builder()
            .id(domainModel.getId())
            .name(domainModel.getName())
            .price(domainModel.getPrice().value())
            .stockQuantity(domainModel.getStockQuantity())
            .build();
    }
}
