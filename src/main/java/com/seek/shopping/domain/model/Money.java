package com.seek.shopping.domain.model;

import java.math.BigDecimal;

public record Money(
    BigDecimal value
) {

    public static Money from(BigDecimal value) {
        return new Money(value);
    }

    public static Money from(int value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public Money add(Money other) {
        return Money.from(this.value.add(other.value));
    }

    public Money subtract(Money other) {
        return Money.from(this.value.subtract(other.value));
    }

    public Money multiply(int number) {
        return Money.from(value.multiply(BigDecimal.valueOf(number)));
    }

    public int compareTo(Money other) {
        return this.value.compareTo(other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money money)) {
            return false;
        }
        return this.value.compareTo(money.value) == 0; // 값이 동일하면 true
    }

    @Override
    public int hashCode() {
        return value.stripTrailingZeros().hashCode(); // 동일한 값은 동일한 해시코드
    }
}
