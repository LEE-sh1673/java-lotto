package lotto.model;

import java.math.BigDecimal;

import lotto.exception.ErrorType;

import static java.math.RoundingMode.HALF_EVEN;

public class LottoMoney {

    private static final int LOTTO_PRICE = 1_000;

    private final BigDecimal amount;

    public LottoMoney(final int amount) {
        validate(amount);
        this.amount = BigDecimal.valueOf(amount);
    }

    private static void validate(final int money) {
        if (!isDividedBy1000(money)) {
            throw new IllegalArgumentException(
                ErrorType.MONEY_CANT_DIVIDED_BY_1000.getMessage()
            );
        }
    }

    private static boolean isDividedBy1000(final int money) {
        return money != 0 && money % LOTTO_PRICE == 0;
    }

    public static LottoMoney of(final int amount) {
        return new LottoMoney(amount);
    }

    public int getNumberOfLotto() {
        return amount
            .divide(BigDecimal.valueOf(LOTTO_PRICE), 0, HALF_EVEN)
            .intValue();
    }

    public boolean equalsZero() {
        return amount.equals(BigDecimal.ZERO);
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
