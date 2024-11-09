package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.Item;
import com.seek.shopping.domain.Money;
import com.seek.shopping.infrastructure.persistence.entity.ItemEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemMapper {

    public static Item toDomainModel(ItemEntity entity) {
        if (entity == null) {
            return null;
        }
        return Item.builder()
            .id(entity.getId())
            .name(entity.getName())
            .price(Money.from(entity.getPrice()))
            .stockQuantity(entity.getStockQuantity())
            .build();
    }

    public static ItemEntity toEntity(Item item) {
        if (item == null) {
            return null;
        }
        return ItemEntity.builder()
            .id(item.getId())
            .name(item.getName())
            .price(item.getPrice().value())
            .stockQuantity(item.getStockQuantity())
            .build();
    }
}
