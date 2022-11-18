package lotto.domain;

import static lotto.domain.ErrorMessage.LESS_THEN_MINIMUM_AMOUNT;
import static lotto.domain.ErrorMessage.NOT_DIVISIBLE_WITH_THOUSAND;
import static lotto.domain.LottoAttributes.LOTTO_PRICE;

public class Money {

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
        return amount < LOTTO_PRICE;
    }

    private void validateUnit(final int amount) {
        if (!isDivisibleWithThousand(amount)) {
            throw new IllegalArgumentException(
                NOT_DIVISIBLE_WITH_THOUSAND.getMessage()
            );
        }
    }

    private boolean isDivisibleWithThousand(final int amount) {
        return amount != 0 && amount % LOTTO_PRICE == 0;
    }

    public int getAmount() {
        return amount;
    }
}
