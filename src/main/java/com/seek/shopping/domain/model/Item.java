package com.seek.shopping.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Item extends BaseDomainModel {

    private Long id;
    private String name;
    private Money price;
    private int stockQuantity;

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new IllegalStateException("남아있는 재고가 없습니다.");
        }
        this.stockQuantity = restStock;
    }
}
