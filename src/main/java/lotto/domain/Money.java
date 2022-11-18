package lotto.domain;

import static lotto.domain.ErrorMessage.LESS_THEN_MINIMUM_AMOUNT;
import static lotto.domain.ErrorMessage.NOT_DIVISIBLE_WITH_THOUSAND;

public class Money {

    private static final int MIN_AMOUNT = 1_000;

    private final int amount;

    public Money(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(final int amount) {
        validateIsLessThanMinimum(amount);
        validateUnit(amount);
    }

    private void validateIsLessThanMinimum(final int amount) {
        if (isLessThanMinimum(amount)) {
            throw new IllegalArgumentException(
                LESS_THEN_MINIMUM_AMOUNT.getMessage()
            );
        }
    }

    private boolean isLessThanMinimum(final int amount) {
        return amount < MIN_AMOUNT;
    }

    private void validateUnit(final int amount) {
        if (!isDivisibleWithThousand(amount)) {
            throw new IllegalArgumentException(
                NOT_DIVISIBLE_WITH_THOUSAND.getMessage()
            );
        }
    }

    private boolean isDivisibleWithThousand(final int amount) {
        return amount != 0 && amount % MIN_AMOUNT == 0;
    }
}
